package Controllers;

import Models.DrawingArea;
import Models.Forms.*;
import Models.Forms.Rectangle;
import Models.LayerCaretaker;
import Models.Lines.Pencil;
import Models.Lines.StraightLine;
import Models.Managers.DrawingManager;
import Models.Managers.LayerCaretakersManager;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    public ColorPicker fillColorPicker;

    @FXML
    public ColorPicker strokeColorPicker;

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public DrawingArea drawingArea;

    @FXML
    public ListView layersListView;

    @FXML
    public Button undoButton;

    @FXML
    public Button redoButton;

    @FXML
    public Button addLayerButton;

    @FXML
    public Button removeLayerButton;

    @FXML
    public TextField lineWidthTextField;

    @FXML
    public ImageView rectangleShapeImageView;

    @FXML
    public ImageView triangleShapeImageView;

    @FXML
    public ImageView ellipseShapeImageView;

    @FXML
    public ImageView straightLineShapeImageView;

    @FXML
    public ImageView pencilLineShapeImageView;

    private LayerCaretakersManager layerCaretakersManager;
    private DrawingManager drawingManager;

    private GraphicsContext gc;

    private boolean regularFigureSet = false;

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        layerCaretakersManager = new LayerCaretakersManager();
        drawingManager = new DrawingManager();

        setUpLayersList();

        gc = drawingArea.getGraphicsContext2D();

        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> {
            drawingArea.setWidth(newValue.doubleValue());
        });

        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> {
            drawingArea.setHeight(newValue.doubleValue());
        });

        fillColorPicker.setValue(Color.BLUE);
        strokeColorPicker.setValue(Color.WHITE);

        layersListView.getSelectionModel().select(0);

        setUpKeyAccelerators();
    }

    private void setUpKeyAccelerators() {
        Platform.runLater(() -> {
            undoButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN), () -> {
                undoButton.fire();
            });
            redoButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.Y, KeyCombination.SHORTCUT_DOWN), () -> {
                redoButton.fire();
            });

            drawingArea.getScene().setOnKeyPressed((event) -> {
                    switch (event.getCode()) {
                        case SHIFT:
                            regularFigureSet = true;
                            break;
                    }
            });

            drawingArea.getScene().setOnKeyReleased( (event) -> {
                switch (event.getCode()) {
                    case SHIFT:
                        regularFigureSet = false;
                        break;
                }
            });
        });
    }

    private void setUpLayersList() {

        layersListView.setCellFactory(new Callback<ListView<LayerCaretaker>, ListCell<LayerCaretaker>>(){

            @Override
            public ListCell<LayerCaretaker> call(ListView<LayerCaretaker> p) {
                final ListCell<LayerCaretaker> cell=new ListCell<LayerCaretaker>(){
                    @Override
                    public void updateItem(LayerCaretaker item, boolean empty){
                        super.updateItem(item, empty);
                        if (item!=null){
                            setText("Layer " + String.valueOf(item.getId()));
                        }
                        else {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });

        layersListView.setItems(layerCaretakersManager.getLayerCaretakerList());
    }

    public void onCanvasMousePressed(MouseEvent mouseEvent) {
        Point2D drawingPoint = drawingArea.sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY());
        int currentLineWidth = Integer.parseInt(lineWidthTextField.getText()) == 0 ? 1000 : Integer.parseInt(lineWidthTextField.getText());

        drawingManager.onDrawingStart(drawingPoint, fillColorPicker.getValue(), strokeColorPicker.getValue(), currentLineWidth);
        drawingManager.onDrawingProceed(drawingPoint, gc, regularFigureSet);

        //mouseEvent.consume();
    }

    public void onCanvasMouseDragged(MouseEvent me) {
        drawingArea.resetDrawingArea(gc);
        Point2D drawingPoint = drawingArea.sceneToLocal(me.getSceneX(), me.getSceneY());

        layerCaretakersManager.redrawLayers(gc);
        drawingManager.onDrawingProceed(drawingPoint, gc, regularFigureSet);
    }

    public void onCanvasMouseReleased(MouseEvent me) throws Exception {
        layerCaretakersManager.addDrawableToCurrentLayer(drawingManager.selectedDrawable);
        layerCaretakersManager.redrawLayers(gc);

        redoButton.setDisable(true);
        undoButton.setDisable(false);
    }

    public void rectangleShapeClicked() { drawingManager.setSelectedDrawable(new Rectangle()); }

    public void triangleShapeClicked() { drawingManager.setSelectedDrawable(new Triangle()); }

    public void ellipseShapeClicked() { drawingManager.setSelectedDrawable(new Ellipse()); }

    public void straightLineShapeClicked() { drawingManager.setSelectedDrawable(new StraightLine()); }

    public void pencilLineShapeClicked() { drawingManager.setSelectedDrawable(new Pencil()); }

    public void fillColorPickerChanged() { gc.setFill(fillColorPicker.getValue()); }

    public void strokeColorPickerChanged() {
        gc.setStroke(strokeColorPicker.getValue());
    }

    public void undoButtonClicked() {
        drawingArea.resetDrawingArea(gc);
        layerCaretakersManager.getWorkingLayerCaretaker().restorePreviousState(gc);
        layerCaretakersManager.redrawLayers(gc);

        undoButton.setDisable(layerCaretakersManager.isSavedStatesEmpty());
        redoButton.setDisable(false);
    }

    public void redoButtonClicked() {
        layerCaretakersManager.getWorkingLayerCaretaker().restoreNextState(gc);
        layerCaretakersManager.redrawLayers(gc);

        undoButton.setDisable(false);
        redoButton.setDisable(layerCaretakersManager.isSavedStatesListHeadEqualToPointer());
    }

    public void addLayerButtonClicked() {
        layerCaretakersManager.addLayerCaretaker();
        layersListView.getSelectionModel().select(layerCaretakersManager.getLayerCaretakersHead());

        undoButton.setDisable(true);
        redoButton.setDisable(true);
        removeLayerButton.setDisable(false);
    }

    @FXML
    public void removeLayerButtonClicked(ActionEvent actionEvent) {
        drawingArea.resetDrawingArea(gc);
        layerCaretakersManager.removeLayerCaretaker(layersListView.getSelectionModel().getSelectedIndex());
        layerCaretakersManager.redrawLayers(gc);
        layersListView.getSelectionModel().select(0);
        layerCaretakersManager.setWorkingLayerCaretaker(layersListView.getSelectionModel().getSelectedIndex());
        if(layerCaretakersManager.getLayerCaretakerList().size() < 2) removeLayerButton.setDisable(true);
    }

    public void layersListViewClicked(MouseEvent mouseEvent) {
        layerCaretakersManager.setWorkingLayerCaretaker(layersListView.getSelectionModel().getSelectedIndex());

        undoButton.setDisable(layerCaretakersManager.isSavedStatesEmpty());
        redoButton.setDisable(layerCaretakersManager.isSavedStatesListHeadEqualToPointer());
    }

    public void openFileMenuItemClicked(ActionEvent actionEvent) {
    }

    public void saveAsImageFileMenuItemClicked(ActionEvent actionEvent) {
        WritableImage pic = drawingArea.snapshot(new SnapshotParameters(), null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file", "*.jpg"));
        fileChooser.setTitle("Zapisz plik");
        File file = fileChooser.showSaveDialog(anchorPane.getScene().getWindow());
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(pic,
                        null), "png", file);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeFileMenuItemClicked(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void setStageAndItsMeasures(Stage stage) {
        this.stage = stage;
        this.stage.minHeightProperty().set(573);
        this.stage.minWidthProperty().set(600);
    }
}
