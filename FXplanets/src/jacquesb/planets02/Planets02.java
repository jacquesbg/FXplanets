package jacquesb.planets02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Planets02 extends Application {
	
	Clockwork clockwork = new Clockwork();
	//double wis = 500;
	//double his = 300;
			
    //Orrery canvas = new Orrery(wis,his-50);
	//OrreryControl control;
	
	@Override
    public void start(final Stage primaryStage) {
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double wis = primaryScreenBounds.getWidth()*0.9;
        double his = primaryScreenBounds.getHeight()*0.9;
		
		//Orrery orrery = new Orrery(wis, his);
		Image image = tryToReadResouresImage("equirectangularprojectionscale2.jpg");
		double aspect = image.getHeight()/image.getWidth();

	    ImageView imageView = new ImageView();
	    imageView.setImage(image);
		ImageViewPane viewPane = new ImageViewPane(imageView, aspect);

		
        VBox vbox = new VBox(2);
        //vbox.getChildren().add(canvas);
        //vbox.getChildren().add(control.panel);
        //vbox.getChildren().add(clockwork.Clabel);
        vbox.getChildren().add(clockwork.Clabel);
        vbox.getChildren().add(viewPane);
        VBox.setVgrow(viewPane, Priority.ALWAYS);
        
        Scene scene = new Scene(vbox, (int)wis, (int)his);
        //scene.setFill(null);
		
        primaryStage.setTitle("FXplanets02");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	private static Image tryToReadResouresImage(String text) {
		// First  create a failed to read image;
		Label label = new Label("Faile to read file "+text);
		label.setMinSize(125, 125);
		label.setMaxSize(125, 125);
		label.setPrefSize(125, 125);
		label.setStyle("-fx-background-color: white; -fx-text-fill:black;");
		label.setWrapText(true);
		Scene scene = new Scene(new Group(label));
		WritableImage wimg = new WritableImage(250, 250) ;
		scene.snapshot(wimg);
		Image img = wimg;
		FileInputStream input;
		try {
			input = new FileInputStream("resources"+File.separatorChar+text);
			img = new Image(input);
		} catch (FileNotFoundException e1) {
			// resource wordt niet gevonden in jar file
			// If it's wrapped in a containing jar you'll need to use the following approach instead:
			// new Image(<yourclassname>.class.getResourceAsStream("icon.png"))
			//
			// the image is read from the sam location as the class files
			try {
				img = new Image(Planets02.class.getResourceAsStream(text));
			} catch (Exception e) {
				//
			}
		}
		return img ;
	}

}
