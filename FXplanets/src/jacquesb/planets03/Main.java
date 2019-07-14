package jacquesb.planets03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	Clockwork clockwork = new Clockwork();
	//double wis = 500;
	//double his = 300;

	//Orrery canvas = new Orrery(wis,his-50);
	//OrreryControl control;

	Text text1;
	public void tik() {
		text1.setText(clockwork.Clabel.getText());
	}
	@Override
	public void start(final Stage primaryStage) {

		//Label mouse = new Label("FXplanets - klik op het scherm");
		/**
		 * The Timeline feature makes a ticking clock a breeze. 
		 * Simply put, we just want our getTime method to be called once a second. 
		 * It will set the three mentioned properties which the clock will be bound to. 
		 */
		Timeline timeline = new Timeline(new KeyFrame(
				Duration.millis(500),
				ae -> tik()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();


		Pane canvas = new Pane();
		canvas.setStyle("-fx-background-color: black;");
		canvas.setPrefSize(200,200);
		Circle circle = new Circle(50,Color.BLUE);
		circle.relocate(20, 20);
		Rectangle rectangle = new Rectangle(100,100,Color.RED);
		rectangle.relocate(70,70);
		canvas.getChildren().addAll(circle,rectangle);


		//VBox vbox = new VBox(20); // vertical spacing = 20
		//vbox.getChildren().add(canvas);
		//vbox.getChildren().add(control.panel);
		//vbox.getChildren().add(clockwork.Clabel);
		//vbox.getChildren().add(mouse); 
		//vbox.getChildren().add(clockwork.Clabel); // VBox kan slecghts een keer hetzelfde element 'add'en, vaker 'add'en leidt tot runtime-fout
		//vbox.getChildren().add(viewPane);
		//VBox.setVgrow(viewPane, Priority.ALWAYS);
		//vbox.setAlignment(Pos.CENTER); // werkt wel op Labels, maar niet op zelfgemaakt ImageViewPane (Region)

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		double wis = primaryScreenBounds.getWidth()*0.9;
		double his = primaryScreenBounds.getHeight()*0.9;
		
		/*
		try {

			//Orrery orrery = new Orrery(wis, his);
			Image image = tryToReadResouresImage("equirectangularprojectionscale2.jpg.java");
			//double aspect = image.getHeight()/image.getWidth();

			ImageView imageView = new ImageView();
			imageView.setImage(image);
			//ImageViewPane viewPane = new ImageViewPane(imageView, aspect);

			imageView.setX(100);
			imageView.setY(200);

			//canvas.getChildren().add(viewPane);
			canvas.getChildren().add(imageView);


		} catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.show();
		}
	*/
		//Scene scene = new Scene(vbox, (int)wis, (int)his);
		//scene.setFill(null);

		//Circle circle = new Circle();
		//circle.setCenterX(100.0f);
		//circle.setCenterY(100.0f);
		//circle.setRadius(10.0f);

		//Group root = new Group(circle);
		//vbox.getChildren().add(root);

		text1 = new Text(clockwork.Clabel.getText());
		text1.setFill(Color.RED);
		text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 40));
		text1.setX(100);
		text1.setY(50);
		canvas.getChildren().add(text1);


		Scene scene = new Scene(canvas, (int)wis, (int)his);

		//Scene scene = new Scene(root, (int)wis, (int)his);

		Circle tch = new Circle(20,Color.BLACK);
		canvas.getChildren().add(tch);

		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				//System.out.println("mouse click detected! " + mouseEvent.getSource());
				//System.out.println(""+mouseEvent.getSceneX()+":"+mouseEvent.getSceneY());
				//mouse.setText("FXplanets: mouse click op coords " + mouseEvent.getSceneX()+":"+mouseEvent.getSceneY());
				tch.setFill(Color.GREEN);
				tch.setCenterX(mouseEvent.getSceneX());
				tch.setCenterY(mouseEvent.getSceneY());
				tch.setRadius(10.0f);
				text1.setText(clockwork.Clabel.getText());
			}
		});

		primaryStage.setTitle("FXplanets02");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
	private static Image tryToReadResouresImage(String text) throws FileNotFoundException {
		// First  create a failed to read image;
		//Label label = new Label("Faile to read file "+text);
		//label.setMinSize(125, 125);
		//label.setMaxSize(125, 125);
		//label.setPrefSize(125, 125);
		//label.setStyle("-fx-background-color: white; -fx-text-fill:black;");
		//label.setWrapText(true);
		//Scene scene = new Scene(new Group(label));
		//WritableImage wimg = new WritableImage(250, 250) ;
		//scene.snapshot(wimg);
		//Image img = wimg;
		Image img;
		FileInputStream input;
		// resource wordt niet gevonden in jar file
		// If it's wrapped in a containing jar you'll need to use the following approach instead:
		// new Image(<yourclassname>.class.getResourceAsStream("icon.png"))
		//
		// the image is read from the same location as the class files
		try {
			img = new Image(Main.class.getResourceAsStream(text));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, e.getMessage());
			alert.show();
			try {
				input = new FileInputStream(text);
				img = new Image(input);
			} catch (FileNotFoundException e1) {
				throw (e1);
				//
			}
		}
		return img ;
	}

}
