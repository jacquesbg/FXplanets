package jacquesb.planets02;

import java.util.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Clockwork {
    public SimpleIntegerProperty hour = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty minute = new SimpleIntegerProperty(0);
    public SimpleIntegerProperty second = new SimpleIntegerProperty(0);
    public Label Clabel = new Label();

	
	public Clockwork() {
		startTicking();
	}

	public void getTime() {
		Calendar calendar = Calendar.getInstance();
        hour.set(calendar.get(Calendar.HOUR));
        minute.set(calendar.get(Calendar.MINUTE));
        second.set(calendar.get(Calendar.SECOND));
        Clabel.setText(""+int2(hour.intValue())+":"+int2(minute.intValue())+":"+int2(second.intValue()));
	}
	String int2(int num) {
		return String.format("%02d", num);
	}
    /**
     * The Timeline feature makes a ticking clock a breeze. 
     * Simply put, we just want our getTime method to be called once a second. 
     * It will set the three mentioned properties which the clock will be bound to. 
     */
    private void startTicking() {
    	Timeline timeline = new Timeline(new KeyFrame(
    	        Duration.millis(500),
    	        ae -> getTime()));
    	timeline.setCycleCount(Animation.INDEFINITE);
    	timeline.play();
    }

}
