package demineur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Jeu {

	// permet de choirir la taille de grille du jeu
	public int tailleX;
	public int tailleY;

	// varialbe pour savoir si la partie est en court ou non
	public Boolean gameOn = true;
	Scanner scan = new Scanner(System.in);

	public Tableau grid;

	public Jeu(int tailleX, int tailleY) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;

		// creation de la grille qui sert au jeu
		grid = new Tableau(tailleX, tailleY, (float)0.1);

	} // public Jeu()

	public void Clic(int x, int y, Boolean actionSelect) {
		if (grid.nbTtBombe==0) {
			grid.FirstClic(y, x);
		}
		if (actionSelect) {
			grid.Select(y, x);
		} else {
			grid.PoseFlag(y, x);
		}
		gameOn = grid.GamePlaying();		
	}

}