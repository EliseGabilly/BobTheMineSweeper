package application;

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

public class PageOption {

	//page pour de prochaine fonctions
	public static Scene makeSceneOption(Stage pStage) {

        //creer une scene layout vertical
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        
        //mise en page de la scene
        vBox.setStyle("-fx-background-color: #C0C0F0;");
        vBox.setPadding(new Insets(50, 20, 10, 20));
        vBox.setSpacing(20);
        
        //titre
		Label titre = new Label("Option");
	    titre.setFont(new Font("Script", 20));
		vBox.getChildren().add(titre);

		//boutton musique
		Button btnMsc = new Button("Musique");
		btnMsc.setStyle("-fx-background-color: #8E9ABD;");
		btnMsc.setMaxWidth(300);
		btnMsc.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
		    }
		});
		vBox.getChildren().add(btnMsc);
		
		//Bouton couleur 
		Button btnCol = new Button("Couleur");
		btnCol.setStyle("-fx-background-color: #8E9ABD;");
		btnCol.setMaxWidth(300);
		btnCol.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
		    }
		});
		vBox.getChildren().add(btnCol);   
		
		//boutton menu
		Button btnMenu = new Button("Retour au menu");
		btnMenu.setStyle("-fx-background-color: #8E9ABD;");
		btnMenu.setMaxWidth(300);
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	pStage.setScene(PageAccueil.makeSceneAccueil(pStage));
		    }
		});
		vBox.getChildren().add(btnMenu);     

		Scene s = new Scene(vBox, Main.lX, Main.lY);
		return s;
        
    }//public void start(Stage primaryStage)
    
}
