package application;

import demineur.Jeu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PageAccueil {

	public static Scene makeSceneAccueil(Stage pStage) {

        //creer une scene layout vertical
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        
        //mise en page de la scene
        vBox.setStyle("-fx-background-color: #C0C0F0;");
        vBox.setPadding(new Insets(50, 20, 10, 20));
        vBox.setSpacing(20);
        
        //titre
		Label titre = new Label("Bob Le Demineur");
	    titre.setFont(new Font("Script", 20));
		vBox.getChildren().add(titre);

		//boutton play
		Button btnPlay = new Button("Play");
		btnPlay.setStyle("-fx-background-color: #8E9ABD;");
		btnPlay.setMaxWidth(300);
		btnPlay.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Jeu unJeu = new Jeu();
		    	pStage.setScene(PageJeu.makeSceneJeu(pStage, unJeu));
		    }
		});
		vBox.getChildren().add(btnPlay);

		//boutton play
		Button btnCustom = new Button("Play custom");
		btnCustom.setStyle("-fx-background-color: #8E9ABD;");
		btnCustom.setMaxWidth(300);
		btnCustom.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	pStage.setScene(PageCustom.makeSceneCustom(pStage));
		    }
		});
		vBox.getChildren().add(btnCustom);
		
		//boutton bob play (lance l'ia)
		Button btnBobPlay = new Button("Bob Play");
		btnBobPlay.setStyle("-fx-background-color: #8E9ABD;");
		btnBobPlay.setMaxWidth(300);
		btnBobPlay.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	//pStage.setScene(PageJeu.makeSceneJeu(pStage));
		    }
		});
		vBox.getChildren().add(btnBobPlay);  
		
		//boutton option
		Button btnOpt = new Button("Option");
		btnOpt.setStyle("-fx-background-color: #8E9ABD;");
		btnOpt.setMaxWidth(300);
		btnOpt.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	pStage.setScene(PageOption.makeSceneOption(pStage));
		    }
		});
		vBox.getChildren().add(btnOpt);
		
		//boutton quitter
		Button btnQuit = new Button("Quiter");
		btnQuit.setStyle("-fx-background-color: #8E9ABD;");
		btnQuit.setMaxWidth(300);
		btnQuit.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	pStage.close();
		    }
		});
		vBox.getChildren().add(btnQuit);  

		Scene s = new Scene(vBox, Main.lX, Main.lY);
		return s;
        
    }//public void start(Stage primaryStage)
    
}
