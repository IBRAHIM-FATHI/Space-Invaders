package thegame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PlayerWeaponPane extends Pane  {
  private ImageView weaponPic = new ImageView("models/rocket.gif");  
  private int a= 1 ;
  
  public PlayerWeaponPane(){
  }
  
  public PlayerWeaponPane(double x,double y,double weaponWidth,double weaponHeight,double refHeight,int direction){
      
      getChildren().add(weaponPic);
      this.a = direction;
      super.setLayoutX(x);
      super.setLayoutY(y);
      super.setWidth(weaponWidth);
      super.setHeight(weaponHeight);
      this.weaponPic.setFitWidth(weaponWidth);
      this.weaponPic.setFitHeight(weaponHeight);
      EventHandler<ActionEvent> ev = e -> {
            super.setLayoutY(super.getLayoutY() + 1 * a);
            
        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1), ev));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
  }
}

    
    

