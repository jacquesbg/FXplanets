package jacquesb.planets02;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
*
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
           layoutInArea(imageView, 0, 0, wv, hv, 0, HPos.CENTER, VPos.CENTER);
       }
       super.layoutChildren();
   }

   static double aspect = -1;
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