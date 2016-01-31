package application;
	
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;

public class LiveMap extends Application {
	private Map map = new Map();
	/** 
	 * @param x the x-axis of the map
	 * @param y the y-axis of the map
	 * @param r the angle of rotation (optional)
	 */
	public void setCoordinate(int x,int y, int r){
	
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("LiveMap3D X700 FX");
		Group root = new Group();
		
		Canvas canvas = new Canvas(814,400);
		map.update(canvas.getGraphicsContext2D());
		
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
		primaryStage.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = (int) event.getX();
				int y = (int) event.getY();
				map.handleClick(x, y);
			}
		});
		
		AnimationTimer timer = new AnimationTimer(){

			@Override
			public void handle(long arg0){
				map.update(canvas.getGraphicsContext2D());
			}
		};
		timer.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
