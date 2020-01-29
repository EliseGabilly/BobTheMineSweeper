package application;

import demineur.Jeu;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PageJeu {

	static Jeu monJeu;
	static Button[][] tabBtn;

	public static Scene makeSceneJeu(Stage pStage, Jeu unJeu) {

		monJeu = unJeu;

		// creer une scene avec top,right,center,left,bottom
		BorderPane border = new BorderPane();
		Scene s = new Scene(border, MainMineSweeper.lX, MainMineSweeper.lY);

		// remplit le top avec un layout horizontal titre et btn menu
		border.setTop(addTop(pStage));
		// recupere 15,60et25% de la taille de la fenetre
		int size15 = Math.toIntExact(Math.round(border.getWidth() * 0.15));
		int size60 = Math.toIntExact(Math.round(border.getWidth() * 0.60));
		int size25 = Math.toIntExact(Math.round(border.getWidth() * 0.25));
		// remplit la gauche avec un layout vertical timer, compte bombe
		border.setLeft(addLeft(size15));
		// remplit le centre avec une grille pour jouer
		border.setCenter(addGrid(size60));
		// remplit la droite avec img Bob, replay
		border.setRight(addRight(size25, pStage));

		return s;

	}// public void start(Stage primaryStage)

	public static HBox addTop(Stage pStage) {
		HBox hBox = new HBox();

		Button btnMenu = new Button("Menu");
		btnMenu.setStyle("-fx-background-color: #C0C0F0;");
		btnMenu.setMinWidth(100);
		btnMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pStage.setScene(PageAccueil.makeSceneAccueil(pStage));
			}
		});

		Label titre = new Label("Time to play with Bob");
		titre.setTextFill(Color.web("#FFFFFF"));
		titre.setFont(new Font("Script", 20));
		hBox.getChildren().add(btnMenu);
		hBox.getChildren().add(titre);
		hBox.setPadding(new Insets(20, 20, 20, 20));
		hBox.setSpacing(20);
		hBox.setStyle("-fx-background-color: #8E9ABD;");

		return hBox;
	}// public HBox addTop()

    // private class constant and some variables
    private static final Integer STARTTIME = 15;
    private static Timeline timeline;
    private static Label timerLabel = new Label();
    private static Integer timeSeconds = STARTTIME;
    
	public static VBox addLeft(int size) {
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10, 20, 10, 20));
		vBox.setSpacing(20);
		vBox.setMaxWidth(size);
		vBox.setStyle("-fx-background-color: #C0C0F0;");

		Label timer = new Label("Timer : ");
		timerLabel.setText(timeSeconds.toString());
		// Bind the timerLabel text property to the timeSeconds property
		//timerLabel.textProperty().bindBidirectional(timeSeconds.toString());
		timer.setWrapText(true);
		Label countBombe = new Label("Number of bombs found : ");
		countBombe.setWrapText(true);
		Label resteBombe = new Label("Bombs left : ");
		resteBombe.setWrapText(true);
		vBox.getChildren().add(timer);
		vBox.getChildren().add(timerLabel);
		vBox.getChildren().add(countBombe);
		vBox.getChildren().add(resteBombe);

		return vBox;
	}// public VBox addLeft(int size)
	
	 
	
	public static GridPane addGrid(int size) {
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setPadding(new Insets(10, 20, 10, 20));
		gridPane.setMaxWidth(size);
		// int s=Math.min(Math.toIntExact(Math.round(size/nbRow)),
		// Math.toIntExact(Math.round(gridPane.getHeight()/nbRow)));
		int s = Math.toIntExact(Math.round(size / monJeu.tailleX));
		tabBtn = new Button[monJeu.tailleY][monJeu.tailleX];

		for (int row = 0; row < monJeu.tailleY; row++) {
			for (int col = 0; col < monJeu.tailleX; col++) {
				Button btn = new Button(monJeu.grid.UneCaseState(row, col));
				tabBtn[row][col] = btn;
				gridPane.add(btn, col, row, 1, 1);
				final int r = row;
				final int c = col;
				btn.setPrefSize(s, s);
				btn.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("action en " + Integer.toString(c) + " " + Integer.toString(r));
						if (monJeu.gameOn) {
							if (event.getButton() == MouseButton.SECONDARY) {
								monJeu.Clic(c, r, false);
							} else {
								monJeu.Clic(c, r, true);
							}
							Refresh();
						} else {
							System.out.println("partie deja fini");
						}
					}
				});
			}
		}
		return gridPane;
	}// public GridPane addGrid(int size)

	public static VBox addRight(int size, Stage pStage) {
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10, 20, 10, 20));
		vBox.setSpacing(20);
		vBox.setMaxWidth(size);
		vBox.setStyle("-fx-background-color: #C0C0F0;");

		// Load an image in the background
		String imageUrl = "file:bob.png";
		Image image = new Image(imageUrl, 160, 160, false, true);
		// Create the ImageView
		ImageView imageView = new ImageView(image);
		// Add Children to the HBox
		vBox.getChildren().add(imageView);

		Button btnReplay = new Button("Replay");
		btnReplay.setStyle("-fx-background-color: #8E9ABD;");
		btnReplay.setMaxWidth(size);
		btnReplay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Jeu newJeu = new Jeu(monJeu.tailleX, monJeu.tailleY, monJeu.densite);
				pStage.setScene(PageJeu.makeSceneJeu(pStage, newJeu));
			}
		});
		vBox.getChildren().add(btnReplay);

		Button btnBack = new Button("Back to the menu");
		btnBack.setStyle("-fx-background-color: #8E9ABD;");
		btnBack.setMaxWidth(size);
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pStage.setScene(PageAccueil.makeSceneAccueil(pStage));
			}
		});
		vBox.getChildren().add(btnBack);

		return vBox;
	}// public VBox addRight(int size, Stage pStage)

	private static void Refresh() {
		for (int row = 0; row < monJeu.tailleY; row++) {
			for (int col = 0; col < monJeu.tailleX; col++) {
				tabBtn[row][col].setText(monJeu.grid.UneCaseState(row, col));
			}
		}
	} // private static void Refresh()

}
