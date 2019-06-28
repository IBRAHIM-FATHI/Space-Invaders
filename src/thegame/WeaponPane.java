package thegame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class WeaponPane extends Pane  {
  private ImageView weaponPic = new ImageView("models/PNG/Lasers/laserRed08.png");  
  private int a= 1 ;
  public WeaponPane(){
  }
  public WeaponPane(double x,double y,double weaponWidth,double weaponHeight,double refHeight,int direction){
      getChildren().add(weaponPic);
      this.a = direction;
      super.setLayoutX(x);
      super.setLayoutY(y);
      super.setWidth(weaponWidth);
      super.setHeight(weaponHeight);
      this.weaponPic.setFitWidth(weaponWidth);
      this.weaponPic.setFitHeight(weaponHeight);
      EventHandler<ActionEvent> ev = e -> {
            if(a==1) {if(this.getLayoutY() > refHeight) {  super.setLayoutX(x);    super.setLayoutY(y);  }}
            else if(a==-1) {if(this.getLayoutY() < 0) {    super.setLayoutX(x);    super.setLayoutY(y);  }}
            super.setLayoutY(super.getLayoutY() + 1 * a);
        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1), ev));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
  }
}
