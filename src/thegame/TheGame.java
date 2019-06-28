 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thegame;

import gameViewer.GameViewer;
import static gameViewer.GameViewer.myfont2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Mohamed
 */
public class TheGame extends Application {
    
    public  double width = 809  , height = 500 ;
    public static final String myfont = "src/models/hu.ttf";
    public static final String myfont2 = "src/models/fonts/Andro.ttf";
    
    public Image exp = new Image("models/explosion.gif");
    public PlayerWeaponPane w7,w8;
    public Pane bigpane = new Pane();
    public Pane losepane = new Pane();
    public Pane winpane = new Pane();
    public Timeline animation;
    public ImageView boom;
    public int trigger=0,count=0;
    public Explosion explosion;
    public int score;
    public int yourscore;
    public Label Scorelbl = new Label();
 
  
    @Override
    
    public void start(Stage primaryStage) {
        PlayerPane p0 = new PlayerPane(100,100,150,150);
        Scene scene = new Scene(bigpane,width,height);
        Scene losescene = new Scene(losepane,width,height);
        Scene winscene = new Scene(winpane,width,height);
        // the LOSE SCENE 
         Label loselbl = new Label (" GAME OVER ");
             loselbl.setLayoutX(150);
             loselbl.setLayoutY(300); 
             loselbl.prefHeight(300);
             loselbl.prefWidth(300); 
             loselbl.setStyle("-fx-text-fill:red");
             try {
              loselbl.setFont(Font.loadFont(new FileInputStream(new File(myfont2)), 200));
             } catch (FileNotFoundException ex) {
              Logger.getLogger(GameViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        BackgroundImage myBI = new BackgroundImage(new Image("models/galaxy.jpg", width, height, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background back = new Background(myBI);
        losepane.setBackground(back);
        losepane.getChildren().add(loselbl);
        // THE WIN SCENE 
        Label winlbl = new Label (" YOU WIN  ");
             winlbl.setLayoutX(200);
             winlbl.setLayoutY(300); 
             winlbl.prefHeight(300);
             winlbl.prefWidth(300); 
             winlbl.setStyle("-fx-text-fill:red");
             try {
              winlbl.setFont(Font.loadFont(new FileInputStream(new File(myfont2)), 250));
             } catch (FileNotFoundException ex) {
              Logger.getLogger(GameViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        winpane.setBackground(back);
        winpane.getChildren().add(winlbl);
        
        // moving my ship with the mouse
        scene.setOnMouseMoved(e -> {
            
            p0.setLayoutX(e.getX());
            p0.setLayoutY(e.getY());
        });
       
       
        
        EnemyPane m0 = new EnemyPane(200,height * 0.1,150,150,width);
        EnemyPane m2 = new EnemyPane(400,height * 0.1,150,150,width);
        EnemyPane m3 = new EnemyPane(600,height * 0.1,150,150,width);
        
        //        EnemyPane m1 = new EnemyPane(600,height * 0.1,150,150,width);

        WeaponPane w0 = new WeaponPane(m0.getLayoutX(),m0.getLayoutY()  ,30,30,height,1);
        WeaponPane w2 = new WeaponPane(m0.getLayoutX(),m0.getLayoutY()  ,30,30,height,1);
        WeaponPane w3 = new WeaponPane(m0.getLayoutX(),m0.getLayoutY()  ,30,30,height,1);
        
        
         // moving my ship with the keyboard
       
        PlayerWeaponPane w1 = new PlayerWeaponPane(p0.getLayoutX(),p0.getLayoutY(),30,30,height,-1);
        
       scene.setOnKeyPressed(e->{
           
                  if( e.getCode()==  KeyCode.UP  ){
                     p0.setLayoutY(p0.getLayoutY()-20);  }
                  if( e.getCode()==  KeyCode.DOWN ){
                     p0.setLayoutY(p0.getLayoutY()+20);  } 
                  if( e.getCode()==  KeyCode.RIGHT  ){
                     p0.setLayoutX(p0.getLayoutX()+20); }
                  if( e.getCode()==  KeyCode.LEFT  ){
                     p0.setLayoutX(p0.getLayoutX()-20); }
                  if( (e.getCode()==  KeyCode.SPACE )&&bigpane.getChildren().contains(p0)){
                  w8=new PlayerWeaponPane(p0.getLayoutX() + p0.getWidth()/2-20,p0.getLayoutY()-p0.getHeight()/2,40,100,height,-1);
              bigpane.getChildren().set(0, w8);
              
               }
       });
       
        scene.setOnMousePressed(e -> {
            if(bigpane.getChildren().contains(p0)){
           w7 = new PlayerWeaponPane(p0.getLayoutX() + p0.getWidth()/2-20,p0.getLayoutY()-p0.getHeight()/2,40,100,height,-1);
           bigpane.getChildren().set(0, w7);
            }
        });
         
        bigpane.getChildren().addAll(w1,m0,w0,p0,m3,w3,m2,w2);
        Scorelbl.setLayoutX(20);
        Scorelbl.setLayoutY(20);
        bigpane.getChildren().add(Scorelbl);
        try {
            Scorelbl.setFont(Font.loadFont(new FileInputStream(new File(myfont2)), 30));
            Scorelbl.setStyle("-fx-text-fill:red");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        EventHandler<ActionEvent> ev = e -> {
          Scorelbl.setText("Your Score : "+Integer.toString(score)); 
          count++;  
          w0.setLayoutX(m0.getLayoutX() + m0.getWidth() / 2 );
          w3.setLayoutX(m3.getLayoutX() + m3.getWidth() / 2 );
          w2.setLayoutX(m2.getLayoutX() + m2.getWidth() / 2 );
          // hitting the first monster
        if(m0.isHit((PlayerWeaponPane)bigpane.getChildren().get(0))) {
        m0.lives--;
        if(m0.lives==0)
        {   
            count = 0;
            trigger = 1;
            score +=100;
            explosion = new Explosion(bigpane.getChildren().get(0).getLayoutX(),m0.getLayoutY());
            bigpane.getChildren().add(explosion);
            bigpane.getChildren().removeAll(w0,m0);
        }
        }
        // hitting the second monster
        if(m2.isHit((PlayerWeaponPane)bigpane.getChildren().get(0))) {
        m2.lives--;
        if(m2.lives==0)
        {   
            count = 0;
            trigger = 1;
            score +=100;
            explosion = new Explosion(bigpane.getChildren().get(0).getLayoutX(),m2.getLayoutY());
            bigpane.getChildren().add(explosion);
            bigpane.getChildren().removeAll(w2,m2);
           
        }
        }
      // hitting the third monsrer
        else if(m3.isHit((PlayerWeaponPane)bigpane.getChildren().get(0))) {
           m3.lives--;
        if(m3.lives==0){
        count = 0;
        trigger = 1;
        score +=100;
        explosion = new Explosion(bigpane.getChildren().get(0).getLayoutX(),m3.getLayoutY());
        bigpane.getChildren().add(explosion);
        bigpane.getChildren().removeAll(w3,m3);
        bigpane.getChildren().add(explosion);
        }
        }
        // hitting my ship from the monster
        else if(p0.isHit(w0,w2,w3)){
            p0.lives--;
            if(p0.lives==0){
             count = 0;
        trigger = 1;
        explosion = new Explosion(p0.getLayoutX(),p0.getLayoutY());
        bigpane.getChildren().add(explosion);
        bigpane.getChildren().removeAll(p0); 
        
             primaryStage.setScene(losescene);
       
            }
        }
        // if my ship crashed the first monster
       else if(m0.getBoundsInParent().intersects(p0.getBoundsInParent()) && bigpane.getChildren().contains(p0)&&bigpane.getChildren().contains(m0) ){
              count = 0;
        trigger = 1;
        explosion = new Explosion(p0.getLayoutX(),p0.getLayoutY());
        bigpane.getChildren().add(explosion);
        bigpane.getChildren().removeAll(p0,m0,w0);   
            primaryStage.setScene(losescene);
        }
        
        // if my ship crashed the second monster
       else  if(m2.getBoundsInParent().intersects(p0.getBoundsInParent()) &&bigpane.getChildren().contains(p0)&&bigpane.getChildren().contains(m2) ){
              count = 0;
        trigger = 1;
        explosion = new Explosion(p0.getLayoutX(),p0.getLayoutY());
        bigpane.getChildren().add(explosion);
        bigpane.getChildren().removeAll(p0,m2,w2);
        primaryStage.setScene(losescene);
                }
       // if my ship crashed the third monster
       else  if(m3.getBoundsInParent().intersects(p0.getBoundsInParent()) &&bigpane.getChildren().contains(p0)&&bigpane.getChildren().contains(m3) ){
              count = 0;
        trigger = 1;
        explosion = new Explosion(p0.getLayoutX(),p0.getLayoutY());
        bigpane.getChildren().add(explosion);
        bigpane.getChildren().removeAll(p0,m3,w3);
        primaryStage.setScene(losescene);
                }
       // related with the explosion
       else {
            if((trigger==1)&&(count>500)){
              bigpane.getChildren().remove(explosion);
              count = 0;
              trigger = 0;
            
          }  
        }
         //System.out.println(score);
          if (score == 300){
          primaryStage.setScene(winscene);
          }
               };

        animation = new Timeline(new KeyFrame(Duration.millis(1), ev));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
            
        GameViewer gvr = new GameViewer();
        
        gvr.btn0.setOnAction(e ->{
            
          primaryStage.setScene(scene);
          primaryStage.show();  
            
        });
        
        bigpane.setBackground(back);
        yourscore = score ;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
