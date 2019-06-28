package thegame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class EnemyPane extends Pane {

    private ImageView ship = new ImageView("models/boss.gif");
    private int a = 1;
    int lives = 500;
    double z;//used to get the location of layouty    
    
    public EnemyPane() {
    }

    public EnemyPane(double x, double y, double shipWidth, double shipHeight, double refWidth) {

        getChildren().add(ship);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setWidth(shipWidth);
        this.setWidth(shipHeight);
        this.ship.setFitWidth(shipWidth);
        this.ship.setFitHeight(shipHeight);
        EventHandler<ActionEvent> ev = e -> {
            if (super.getLayoutX() + this.ship.getFitHeight() > refWidth) {
                a = -1;
            } else if (super.getLayoutX() < 0) {
                a = 1;
            }
            super.setLayoutX(super.getLayoutX() + (1 * a));
             z=super.getLayoutX();
            super.setLayoutY(100*Math.sin(z/100)+100);
        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5), ev));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public boolean isHit(PlayerWeaponPane wpn){
       if(wpn.getBoundsInParent().intersects(this.getBoundsInParent()))  return true;
       return false;
    }
}
