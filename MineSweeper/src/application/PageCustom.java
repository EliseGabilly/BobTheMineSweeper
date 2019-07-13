package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PageCustom {

	//page pour customiser un partie
	public static Scene makeSceneCustom(Stage pStage) {

        //creer une scene layout vertical
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        
        //mise en page de la scene
        vBox.setStyle("-fx-background-color: #C0C0F0;");
        vBox.setPadding(new Insets(50, 20, 10, 20));
        vBox.setSpacing(20);
        
        //titre
		Label titre = new Label("Customisation");
	    titre.setFont(new Font("Script", 20));
		vBox.getChildren().add(titre);

		//Taille du terrain
		Label lblTaille = new Label("Taille du terrain");
		vBox.getChildren().add(lblTaille);		
		Slider sliderTaille = new Slider(10, 50, 20);
		sliderTaille.setMaxWidth(300);
		vBox.getChildren().add(sliderTaille);
		
		//Densite bombe
		Label lblDns = new Label("Taille du terrain");
		vBox.getChildren().add(lblDns);		
		Slider sliderDns = new Slider(11, 23, 15);
		sliderDns.setMaxWidth(300);
		vBox.getChildren().add(sliderDns);
		
		//orientation du jeu
		Label lblOr = new Label("Orientation du terrain");
		vBox.getChildren().add(lblOr);	
		vBox.getChildren().add(makeG1());
		
		//AI
		Label lblJoueur = new Label("Choix du joueur");
		vBox.getChildren().add(lblJoueur);	
		vBox.getChildren().add(makeG2());
		
		//boutton menu
		Button btnMenu = new Button("Menu");
		btnMenu.setStyle("-fx-background-color: #8E9ABD;");
		btnMenu.setMaxWidth(300);
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	pStage.setScene(PageAccueil.makeSceneAccueil(pStage));
		    }
		});
		vBox.getChildren().add(btnMenu); 
		
		//boutton play
		Button btnPlay = new Button("Play");
		btnPlay.setStyle("-fx-background-color: #8E9ABD;");
		btnPlay.setMaxWidth(300);
		btnPlay.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	pStage.setScene(PageJeu.makeSceneJeu(pStage, 10, 7));
		    }
		});
		vBox.getChildren().add(btnPlay);     

		Scene s = new Scene(vBox, Main.lX, Main.lY);
		return s;
        
    }//public static Scene makeSceneCustom(Stage pStage) 
	
	private static HBox makeG2() {
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(20);
		
		final ToggleGroup groupJ = new ToggleGroup();
		RadioButton rbJ1 = new RadioButton("Joueur");
		rbJ1.setToggleGroup(groupJ);
		rbJ1.setSelected(true);
		rbJ1.setStyle("-fx-mark-highlight-color: #8E9ABD; -fx-mark-color: #8E9ABD;");
		RadioButton rbJ2 = new RadioButton("Bob");
		rbJ2.setToggleGroup(groupJ);		
		rbJ2.setStyle("-fx-mark-highlight-color: #8E9ABD; -fx-mark-color: #8E9ABD;");
		hBox.getChildren().addAll(rbJ1, rbJ2);
		
		return hBox;
	} //private static HBox makeG2()

	private static HBox makeG1() {
		HBox hBox = new HBox();  
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(20);
		
		final ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Paysage");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		rb1.setStyle("-fx-mark-highlight-color: #8E9ABD; -fx-mark-color: #8E9ABD;");
		RadioButton rb2 = new RadioButton("Portrait");
		rb2.setToggleGroup(group);		
		rb2.setStyle("-fx-mark-highlight-color: #8E9ABD; -fx-mark-color: #8E9ABD;");
		hBox.getChildren().addAll(rb1, rb2);
		
		return hBox;
	} // public static HBox makeG1()
}
