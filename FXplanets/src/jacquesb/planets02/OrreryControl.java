package jacquesb.planets02;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;

public class OrreryControl extends Control {
	Orrery panel;
	OrreryControl() {
		double w = this.getHeight();
		double h = this.heightProperty().doubleValue();
		panel = new Orrery(w,h);
	}

}
