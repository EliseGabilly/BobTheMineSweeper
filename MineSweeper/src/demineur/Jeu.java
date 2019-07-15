package demineur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Jeu {

	// permet de choirir la taille de grille du jeu
	public int tailleX;
	public int tailleY;
	// variable de jeu
	public float densite;

	// varialbe pour savoir si la partie est en court ou non
	public Boolean gameOn = true;

	public Tableau grid;

	public Jeu() {
		this.tailleX = 15;
		this.tailleY = 10;
		this.densite = (float)0.23;
		// creation de la grille qui sert au jeu
		grid = new Tableau(this.tailleX, this.tailleY, this.densite);
	} // public Jeu()

	public Jeu(int tailleX, int tailleY, float densite) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.densite = densite;
		// creation de la grille qui sert au jeu
		grid = new Tableau(this.tailleX, this.tailleY, this.densite);
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