package jacquesb.planets01;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	Clockwork clockwork = new Clockwork();
	//double wis = 500;
	//double his = 300;
			
    //Orrery canvas = new Orrery(wis,his-50);
	//OrreryControl control;
	
	@Override
    public void start(final Stage primaryStage) {
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double wis = primaryScreenBounds.getWidth();
        double his = primaryScreenBounds.getHeight();
		
		//Label clock = clockwork.label;
        
		Group g = new Group();
		
		//control = new OrreryControl();
		Orrery orrery = new Orrery(wis, his);
		
		g.getChildren().add(orrery);
		g.getChildren().add(clockwork.Clabel);
        VBox vbox = new VBox(2);
        //vbox.getChildren().add(canvas);
        //vbox.getChildren().add(control.panel);
        //vbox.getChildren().add(clockwork.Clabel);
        vbox.getChildren().add(g);
        
        Scene scene = new Scene(vbox, (int)wis, (int)his);
        //scene.setFill(null);
		
        primaryStage.setTitle("FXplanets01");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
