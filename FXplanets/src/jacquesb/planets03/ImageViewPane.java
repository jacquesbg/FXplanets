package jacquesb.planets03;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
* Maakt een schaalbare ImageView voor gebruik in bijvoorbeeld VBox of HBox 
* @author akouznet
*/
class ImageViewPane extends Region {

   private ObjectProperty<ImageView> imageViewProperty = new SimpleObjectProperty<ImageView>();

   public ObjectProperty<ImageView> imageViewProperty() {
       return imageViewProperty;
   }

   public ImageView getImageView() {
       return imageViewProperty.get();
   }

   public void setImageView(ImageView imageView) {
       this.imageViewProperty.set(imageView);
   }

   public ImageViewPane() {
       this(new ImageView(),aspect);
   }

   @Override
   protected void layoutChildren() {
       ImageView imageView = imageViewProperty.get();
       if (imageView != null) {
    	   double wv = this.getWidth();
    	   double hv = this.getHeight();
    	   // bereken hoogte en breedte van het image zodat het precies past met behodu van aspect ratio
    	   double wi = 1;
    	   double hi = hv/wv;
    	   if (aspect >0) {
    		   hi = aspect;
    	   }
    	   if (hi/wi > hv/wv) {
    		   // portrait
    		   wv = wi*hv/hi;
    	   } else {
    		   // landscape
    		   hv = hi*wv/wi;
    	   }
           imageView.setFitWidth(wv);
           imageView.setFitHeight(hv);
           // bereken plaat svan de linkerbovenhoek van het image om het te centreren
           double wspace = this.getWidth()-wv;
           double hspace = this.getHeight()-hv;
           
           layoutInArea(imageView, wspace/2., hspace/2., wv, hv, 0, HPos.CENTER, VPos.CENTER);
           
       }
       super.layoutChildren();
   }

   static double aspect = -1;
   /**
    * Maak een schaalbare ImageView voor gebruik in bijvoorbeeld VBox of HBox
    * @param imageView	ImageView van een Image
    * @param aspect		gewenste aspect ration (hoogte/breedte) van het Image
    */
   public ImageViewPane(ImageView imageView, double aspect) {
	   ImageViewPane.aspect = aspect;
       imageViewProperty.addListener(new ChangeListener<ImageView>() {

           @Override
           public void changed(ObservableValue<? extends ImageView> arg0, ImageView oldIV, ImageView newIV) {
               if (oldIV != null) {
                   getChildren().remove(oldIV);
               }
               if (newIV != null) {
                   getChildren().add(newIV);
               }
           }
       });
       this.imageViewProperty.set(imageView);
   }
}