/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thegame;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 *
 * @author Mohamed
 */
public class Explosion extends Pane {
    private ImageView boomboom = new ImageView("models/explosion.gif") ;
    public Explosion(double x,double y){
        super.setLayoutX(x);
        super.setLayoutY(y);
          getChildren().add(boomboom);
    }
}
