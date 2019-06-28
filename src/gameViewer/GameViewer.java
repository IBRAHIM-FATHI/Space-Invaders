package gameViewer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import thegame.TheGame;

/**
 *
 * @author Mohamed
 */
public class GameViewer {
    
    private Stage stage;
    private Pane spane;
     
    public static final String myfont = "src/models/hu.ttf";
    public static final String myfont2 = "src/models/fonts/Andro.ttf";
    public static final String background = "/models/123.gif";
    private Scene scene;
    private Pane pane;
    public Button btn0, btn1, btn2;
    private Label lbl0,lbl1;
    private final double height = 800;
    private final double width = 800;

       public GameViewer()  {
        
        stage = new Stage();
        pane = new AnchorPane();
       
        
        
        lbl1 = new Label("THE SPACE");
        lbl1.setStyle("-fx-text-fill:silver");
        lbl1.setLayoutX(width * 0.5 - 200);
        lbl1.setLayoutY(100);
        try {
            lbl1.setFont(Font.loadFont(new FileInputStream(new File(myfont2)), 100));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        btn0 = createButton("START", (width / 2.0)-250, height / 3.0, 50);
        btn0.setStyle("-fx-background-color:black;-fx-text-fill:red");
        
        
        btn1 = createButton("CREDITS", (width / 2.0)-250, height * 0.33 + 120, 35);
        btn1.setStyle("-fx-background-color:black;-fx-text-fill:red");
        
        btn2 = createButton("EXIT",(width / 2.0)-250,height * 0.33 +200,35);
        btn2.setStyle("-fx-background-color:black;-fx-text-fill:red");
        
        
        spane = new Pane();
        spane.setBackground(new Background(new BackgroundImage(new Image("models/galaxy.jpg", 250, 250, false, true),BackgroundRepeat.REPEAT
        ,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
        
        btn0.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
        
        
        
        btn1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.getChildren().removeAll(btn0, btn1,btn2);
                lbl0 = new Label();
                lbl0.setText(String.format("%s \n %s \n %s \n %s \n %s \n"," Mohammed Tarek","Mahmoud Nasser","Mohammed AbdEl-Badeea","Ragy Rafeek","Mahmoud Khaled" )); 
                lbl0.setStyle("-fx-text-fill:yellow");
                lbl0.setLayoutY(height * 0.33);
                lbl0.setLayoutX(100);
                try {
                    lbl0.setFont(Font.loadFont(new FileInputStream(new File(myfont)), 60));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GameViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
                pane.getChildren().add(lbl0);
                TheGame game = new TheGame();
                int x = game.yourscore;
                Label scorelbl = new Label();
                scorelbl.setLayoutX(width/2-50);
                scorelbl.setLayoutY(width/2); 
                scorelbl.prefWidth(300);
                scorelbl.prefHeight(300);
                scorelbl.setBackground(Background.EMPTY);
                scorelbl.setStyle("-fx-text-fill:red");
                try {
                    scorelbl.setFont(Font.loadFont(new FileInputStream(new File(myfont)), 50));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GameViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
                pane.getChildren().add(scorelbl);
                 Button btnback = new Button("BACK");
                btnback.setLayoutX(550);
                btnback.setLayoutY(height*0.7);
                btnback.setPrefSize(300,200);
                btnback.setStyle("-fx-text-fill:yellow");
                btnback.setBackground(Background.EMPTY);
                
                try {
                    btnback.setFont(Font.loadFont(new FileInputStream(new File(myfont)), 30));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GameViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
                pane.getChildren().add(btnback);
                btnback.setOnMousePressed(e -> {
                pane.getChildren().removeAll(lbl0,btnback,scorelbl);
                pane.getChildren().addAll(btn0, btn1,btn2);
                });
            }
        });
        
        
        btn2.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
                stage.close();
                
            }
            
        }
        );
        
        
        
        pane.getChildren().addAll(lbl1,btn0, btn1,btn2);
        
        scene = new Scene(pane, width, height);
        
        stage.setScene(scene);
        
        BackgroundImage myBI = new BackgroundImage(new Image(background, 800, 800, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background back = new Background(myBI);
        pane.setBackground(back);
        
        stage.setResizable(false);
        stage.show();
        
        

    }

    public Button createButton(String txt, double x, double y, double size) {
        Button bt = new Button(txt);
        //bt.setStyle("-fx-background-color:tarnsparent;-fx-border-color:red;-fx-font-color:green");
        bt.setLayoutX(x);
        bt.setLayoutY(y);
       try {
            bt.setFont(Font.loadFont(new FileInputStream(new File(myfont)), size));
        }catch (FileNotFoundException ex) {
            bt.setFont(Font.font("TIMESNEWROMAN"));
       }
        return bt;
    }
//    public Label createLabel(String txt, double size){
//        Label lbl=new Label("txt");
//        lbl.prefWidth(50);
//        lbl.prefHeight(30);
//        lbl.setMinHeight(30);
//        lbl.setMinWidth(50);
//         lbl.setStyle("-fx-background-color:tarnsparent;-fx-border-color:red;-fx-font-color:green");
//       try {
//          lbl.setFont(Font.loadFont(new FileInputStream(new File(myfont)), size));
//      }catch (FileNotFoundException ex) {
//          lbl.setFont(Font.font("TIMESNEWROMAN"));
//      }
//        return lbl;
//}
}

