package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.stage.Stage;

public class Main extends Application {

	static int lX=1000;
	static int lY=600;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage pStage) {
	// creer un fenetre et y set le layout de la page d'accueil 
	pStage.setTitle("BOB le demineur");
	pStage.setScene(PageAccueil.makeSceneAccueil(pStage)); 

//	pStage.widthProperty().addListener((obs, oldVal, newVal) -> {
//		System.out.println(pStage.getHeight());
//		System.out.println((int)pStage.getHeight());
//		System.out.println(lX);
//	    lX=(int) pStage.getWidth();
//	});
//	pStage.heightProperty().addListener((obs, oldVal, newVal) -> {
//	    lY=(int) pStage.getHeight();
//	});

	pStage.show();
	}
    
}
