package jacquesb.planets02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Orrery extends Canvas {
	Orrery(double w, double h) {
		super(w,h);
		GraphicsContext gc = this.getGraphicsContext2D();

		Image back = tryToReadResouresImage("equirectangularprojectionscale2.jpg");

		//ImageView imageView = new ImageView(back);
		double wi = back.getWidth();
		double hi = back.getHeight();
		System.out.println(""+wi+" "+hi);
		
    	double wim = w;
    	double him = h;
		System.out.println(""+wim+" "+him);
    	double htopleft = 0;
    	double wtopleft = 0;
        if (h/w > hi/wi) {
        	//System.out.println("porrait");
        	wim = w;
        	him = hi*w/wi;
        	htopleft = (h-him)/2;
        	wtopleft = 0;
        	
        } else {
        	//System.out.println("landscape");
        	him = h;
        	wim = wi*h/hi;
        	htopleft = 0;
        	wtopleft = (w-wim)/2;
        }

        //System.out.println("xtop "+wtopleft+"; ytop "+ htopleft+"; W "+ wim+"; H "+ him);
        gc.drawImage(back, wtopleft, htopleft, wim, him);
        gc.setFill(Color.BLUE);
        //gc.fillRect(75,75,100,100);
        
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.BOTTOM);
        gc.fillText("00:00:00.000", 100, 100);
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
