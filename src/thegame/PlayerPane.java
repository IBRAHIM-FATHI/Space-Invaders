
package thegame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayerPane extends Pane {
    private ImageView picture = new ImageView("models/bigger.gif");
    
    int lives = 5000;
    
    public PlayerPane(){
        
    }
    public PlayerPane(double x,double y,double pictureWidth,double pictureHeight){
        getChildren().add(picture);
       super.setLayoutX(x);
       super.setLayoutY(y);
        super.setWidth(pictureWidth);
        super.setHeight(pictureHeight);
        this.picture.setFitWidth(pictureWidth);
        this.picture.setFitHeight(pictureHeight);
        
        
    }
    public boolean isHit(WeaponPane wpn0,WeaponPane wpn1,WeaponPane wpn2 ){
        
        
        
         
       if(wpn0.getBoundsInParent().intersects(this.getBoundsInParent()) || wpn1.getBoundsInParent().intersects(this.getBoundsInParent()) || wpn2.getBoundsInParent().intersects(this.getBoundsInParent()))  return true;
       
       return false;
            
                
            
    }
    
}
