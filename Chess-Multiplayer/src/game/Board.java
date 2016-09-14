package game;

import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import game.Figure.COLOR;
import game.Figure.TYPE;
import network.NetworkRole;

public class Board {

	public static Figure field[][] = new Figure[8][8];
	public static boolean moves[][] = new boolean[8][8];
	public static final int FIELDSIZE = 64;
	private Figure selected;
	private String[] promoteOptions = { "Dame", "Turm", "Läufer", "Pferd" };

	// init a new chess-board
	public void init() {
		// BLACK
		field[0][0] = new Turm(0, 0, COLOR.BLACK);
		field[1][0] = new Pferd(1, 0, COLOR.BLACK);
		field[2][0] = new Laeufer(2, 0, COLOR.BLACK);
		field[3][0] = new Dame(3, 0, COLOR.BLACK);
		field[4][0] = new Koenig(4, 0, COLOR.BLACK);
		field[5][0] = new Laeufer(5, 0, COLOR.BLACK);
		field[6][0] = new Pferd(6, 0, COLOR.BLACK);
		field[7][0] = new Turm(7, 0, COLOR.BLACK);

		for (int i = 0; i <= 7; i++) {
			field[i][1] = new Bauer(i, 1, COLOR.BLACK);
		}

		// WHITE
		field[0][7] = new Turm(0, 7, COLOR.WHITE);
		field[1][7] = new Pferd(1, 7, COLOR.WHITE);
		field[2][7] = new Laeufer(2, 7, COLOR.WHITE);
		field[3][7] = new Dame(3, 7, COLOR.WHITE);
		field[4][7] = new Koenig(4, 7, COLOR.WHITE);
		field[5][7] = new Laeufer(5, 7, COLOR.WHITE);
		field[6][7] = new Pferd(6, 7, COLOR.WHITE);
		field[7][7] = new Turm(7, 7, COLOR.WHITE);

		for (int i = 0; i <= 7; i++) {
			field[i][6] = new Bauer(i, 6, COLOR.WHITE);
		}

	}

	public void update() {
		if (Game.getInput().isMousePressed(Game.getInput().MOUSE_LEFT_BUTTON) && Game.getNetworkInstance().isMyTurn()) {
			int mx = Game.getInput().getMouseX();
			int my = Game.getInput().getMouseY();

			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {
					int posX = Game.SCREEN_WIDTH / 2 - 4 * FIELDSIZE + j * FIELDSIZE;
					int posY = Game.SCREEN_HEIGHT / 2 - 4 * FIELDSIZE + i * FIELDSIZE;

					if (mx >= posX && mx <= posX + FIELDSIZE && my >= posY && my <= posY + FIELDSIZE) {
						// selecting or kicking a piece
						if (field[j][i] != null && !moves[j][i]) {
							unselect();
							//Host can move white and client black
							if ((Game.getNetworkInstance().getRole() == NetworkRole.HOST
									&& field[j][i].getColor() == COLOR.WHITE) || (Game.getNetworkInstance().getRole() == NetworkRole.CLIENT
									&& field[j][i].getColor() == COLOR.BLACK)) {
								field[j][i].setSelected(true);
								selected = field[j][i];
							}

						} else if (selected != null && moves[j][i]) {
							// moving a piece, deal with player turns and kicks
							Game.getNetworkInstance().makeTurn(selected.getX(), selected.getY(), j, i);
							move(selected.getX(), selected.getY(), j, i);
						}
					}
				}
			}

		}

		if (Game.getInput().isKeyPressed(Game.getInput().KEY_ESCAPE) && selected != null) {
			unselect();
		}
	}

	public void move(int fromX, int fromY, int toX, int toY) {
		selected = field[fromX][fromY];
		field[fromX][fromY] = null;
		field[toX][toY] = selected;
		selected.setPosition(toX, toY);
		if(canPromote()){
			int promotion = JOptionPane.showOptionDialog(null, "Wähle deine Beförderung",
			            "Beförderung", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null,
			            promoteOptions,
			            promoteOptions[1]);
			promote(selected, promotion);
		}
		unselect();
	}
	
	// let the pawn become a queen
	public boolean canPromote(){
		boolean canPromote = false;
		if(selected.getType().equals(TYPE.BAUER) && selected.getY() == 0 && selected.getColor().equals(COLOR.WHITE)){
			canPromote = true;
		}else if(selected.getType().equals(TYPE.BAUER) && selected.getY() == 7 && selected.getColor().equals(COLOR.BLACK)){
			canPromote = true;
		}
		return canPromote;
	}
	
	public void promote(Figure figure, int promotion){
		switch(promotion){
		case 0: selected = new Dame(figure.getX(), figure.getY(), figure.getColor());
			break;
		case 1: selected = new Turm(figure.getX(), figure.getY(), figure.getColor());
			break;
		case 2: selected = new Laeufer(figure.getX(), figure.getY(), figure.getColor());
			break;
		case 3: selected = new Pferd(figure.getX(), figure.getY(), figure.getColor());
			break;
		}
	}

	public void render(Graphics g) {
		// render chess-fields
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {

				if (moves[j][i]) {
					g.setColor(Color.yellow);
				} else if ((i * 7 + j) % 2 == 0) {
					g.setColor(Color.gray);
				} else {
					g.setColor(Color.orange);
				}
				g.fillRect(Game.SCREEN_WIDTH / 2 - 4 * FIELDSIZE + j * FIELDSIZE,
						Game.SCREEN_HEIGHT / 2 - 4 * FIELDSIZE + i * FIELDSIZE, FIELDSIZE, FIELDSIZE);

				if (field[j][i] != null)
					field[j][i].render(g);
				// render possible moves
				// render figures
			}
		}
	}

	public static boolean isOnBoard(int x, int y) {
		return x >= 0 && x <= 7 && y >= 0 && y <= 7;
	}

	private void unselect() {
		for (Figure[] figures : field) {
			for (Figure figure : figures) {
				if (figure != null)
					figure.setSelected(false);
			}
		}

		moves = new boolean[8][8];
		selected = null;
	}
}
