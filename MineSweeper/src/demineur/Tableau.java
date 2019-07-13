package demineur;

import java.util.Random;

public class Tableau {

	int tailleX;
	int tailleY;
	float densite;
	int nbTtBombe;
	Case[][] tab;

	// Initialise un tableau de taille x y
	public Tableau(int tailleX, int tailleY, float densite) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		this.densite = densite;
		// initiallisation d'un tableau de x*y de case qui permet de jouer
		tab = new Case[tailleY][tailleX];
		for (int x = 0; x < tab.length; x++) {
			for (int y = 0; y < tab[0].length; y++) {
				tab[x][y] = new Case();
			}
		}
	} // public Tableau(int tailleX, int tailleY)

	public void FirstClic(int x, int y) {
		nbTtBombe = Math.round((tailleX * tailleY) * densite);
		PlaceBombes(x, y);
		CountBombe();
		AfficheVal();
	}

	// Verifi si la partie est perdu et/ou gagné
	public Boolean GamePlaying() {
		for (int x = 0; x < tab.length; x++) {
			for (int y = 0; y < tab[0].length; y++) {
				if (tab[x][y].getState().equals("-1")) {
					System.out.println("Une bombe explose");
					return false;
				}
			}
		}
		for (int x = 0; x < tab.length; x++) {
			for (int y = 0; y < tab[0].length; y++) {
				if (tab[x][y].getValue() != -1
						&& (tab[x][y].getState().equals(" ") || tab[x][y].getState().equals("F"))) {
					System.out.println("Game on");
					return true;
				}
			}
		}
		System.out.println("C'est la victoire !");
		return false;
	}

	// Selectionne une case
	public void Select(int x, int y) {
		if (tab[x][y].getState().equals("F")) {
			System.out.println("Cette case est protegée");
		} else if (tab[x][y].getState().equals(" ")) {
			tab[x][y].setState(String.valueOf(tab[x][y].getValue()));
			if (tab[x][y].getValue() == 0) {
				int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
				for (int[] uneDir : dir) {
					try {
						if (tab[x + uneDir[0]][y + uneDir[1]].getState().equals(" ")) {
							Select(x + uneDir[0], y + uneDir[1]);
						}
					} catch (Exception e) {
					}
				}
			} else if (tab[x][y].getValue() == -1) {
				// perdu
			}
		} else {
			// case deja revelée

			int count = 0;
			int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

			for (int[] uneDir : dir) {
				try {
					if (tab[x + uneDir[0]][y + uneDir[1]].getState().equals("F")) {
						count++;
					}
				} catch (Exception e) {
				}
			}
			System.out.println(count);
			if (tab[x][y].getValue() == count) {
				for (int[] uneDir : dir) {
					try {
						if (tab[x + uneDir[0]][y + uneDir[1]].getState().equals(" ")) {
							Select(x + uneDir[0], y + uneDir[1]);
						}
					} catch (Exception e) {
					}
				}
			}
		}

		// return true
	} // public void Select(int x, int y)

	// Place un drapeau pour indiquer une bombe
	public void PoseFlag(int x, int y) {
		if (tab[x][y].getState().equals(" ")) {
			tab[x][y].setState("F");
		} else if (tab[x][y].getState().equals("F")) {
			tab[x][y].setState(" ");
		}
	} // public void PoseFlag(int x, int y)

	// Permet d'afficher les valeur du tableau (debug)
	public void AfficheVal() {
		System.out.print("     ");
		for (int y = 0; y < tab[0].length; y++) {
			System.out.printf("%4s", y);
		}
		System.out.println(" ");
		for (int x = 0; x < tab.length; x++) {
			System.out.printf("%3s -> ", x);
			for (int y = 0; y < tab[0].length; y++) {
				System.out.printf("|%3s", tab[x][y].getValue());

			}
			System.out.println("|");
		}
		System.out.println(" ");
	} // public void Affiche()

	public String UneCaseState(int x, int y) {
		return tab[x][y].getState();
	} // public void Affiche()

	// placer les bombes
	private void PlaceBombes(int x, int y) {
		int count = nbTtBombe;
		Random rand = new Random();
		int[][] dir = { { 0, 0 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
				{ 1, 1 } };
		while (count > 0) {
			int rY = rand.nextInt(tailleX);
			int rX = rand.nextInt(tailleY);
			// verifi qu'il n'y a pas deja une bombe ici
			if (tab[rX][rY].getValue() == 0) {
				// permet d'avoir les coin s'ecurisé
				if (!((rX == 0 && rY == 0) || (rX == 0 && rY == tailleX - 1) || (rX == tailleY - 1 && rY == tailleX - 1)
						|| (rX == tailleY - 1 && rY == 0))) {
					// permet d'avoir le premier clic securisé
					Boolean premierC = true;
					for (int[] uneDir : dir) {
						try {
							if (x + uneDir[0] == rX && y + uneDir[1] == rY) {
								premierC=false;
							}
						} catch (Exception e) {
						}
					}
					if (premierC) {
						tab[rX][rY].setValue(-1);
						count--;
					}
				}
			}

		}
	}

	// maj value de case en fonction des voisins
	private void CountBombe() {
		// dir
		int count;
		int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
		for (int x = 0; x < tab.length; x++) {
			for (int y = 0; y < tab[0].length; y++) {
				if (tab[x][y].getValue() == 0) {
					count = 0;
					for (int[] uneDir : dir) {
						try {
							if (tab[x + uneDir[0]][y + uneDir[1]].getValue() == -1) {
								count++;
							}
						} catch (Exception e) {
						}
					}
					tab[x][y].setValue(count);
				}
			}
		}
	}

	//
	private void CountTtBombe() {
		int count;
		for (int x = 0; x < tab.length; x++) {
			for (int y = 0; y < tab[0].length; y++) {
				if (tab[x][y].getValue() == 0) {

				}
			}
		}

	}

}