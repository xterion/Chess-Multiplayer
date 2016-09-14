package game;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import game.Figure.COLOR;
import game.Figure.TYPE;
import network.NetworkRole;

public class Board {

	public static Figure field[][] = new Figure[8][8];
	public static boolean moves[][] = new boolean[8][8];
	public static final int FIELDSIZE = 64;
	public boolean timeout = false;
	private Figure selected;
	private final int time = 3000; // in hundertstel sekunden
	private int current = time;
	private boolean recovered;
	private boolean end = false;
	private String[] promoteOptions = { "Dame", "Turm", "Läufer", "Pferd" };
	private Timer timer = new Timer();
	private TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			if (current > 0) {
				current--;
			}
			if (current == 0) {
				timeout = true;
			}
		}
	};

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

		// Empty fields
		for (int i = 0; i <= 7; i++) {
			for (int j = 2; j <= 5; j++) {
				field[i][j] = null;
			}
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

		// reset moves in case of restart
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				moves[i][j] = false;
			}
		}
		startCountDown();

	}

	public void update() {
		if (Game.getInput().isMousePressed(Game.getInput().MOUSE_LEFT_BUTTON)
				&& Game.getNetworkInstance().isMyTurn()) {
			int mx = Game.getInput().getMouseX();
			int my = Game.getInput().getMouseY();

			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {
					int posX = Game.SCREEN_WIDTH / 2 - 4 * FIELDSIZE + j
							* FIELDSIZE;
					int posY = Game.SCREEN_HEIGHT / 2 - 4 * FIELDSIZE + i
							* FIELDSIZE;

					if (mx >= posX && mx <= posX + FIELDSIZE && my >= posY
							&& my <= posY + FIELDSIZE) {
						// selecting or kicking a piece
						if (field[j][i] != null && !moves[j][i]) {
							unselect();
							//Host can move white and client black
							if ((Game.getNetworkInstance().getRole() == NetworkRole.HOST && field[j][i]
									.getColor() == COLOR.WHITE)
									|| (Game.getNetworkInstance().getRole() == NetworkRole.CLIENT && field[j][i]
											.getColor() == COLOR.BLACK)) {
								field[j][i].setSelected(true);
								selected = field[j][i];
								if (end == true) {
									finish();
								}
							}

						} else if (selected != null && moves[j][i]) {
							// moving a piece, deal with player turns and kicks
							Game.getNetworkInstance().makeTurn(selected.getX(),
									selected.getY(), j, i);
							move(selected.getX(), selected.getY(), j, i);
						}
					}
				}
			}

		}

		if (Game.getInput().isKeyPressed(Game.getInput().KEY_ESCAPE)
				&& selected != null) {
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
		current = time;
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

		check();

		if (Game.getNetworkInstance().isMyTurn()) {
			g.setColor(Color.white);
			g.drawString("Du bist an der Reihe!", 20, 10);
			String text = String.valueOf(current / 100);
			if (timeout == true) {
				text = "Die Zeit \n\rist \n\rabgelaufen";
				recovered = false;
				end = true;
				finish();
			}
			g.drawString(text, 700, 10);
		} else {
			g.setColor(Color.white);
			g.drawString("Der Gegener ist an der Reihe!", 20, 10);
			String text = String.valueOf(current / 100);
			if (timeout == true) {
				text = "Du hast \n\rgewonnen \n\r:)";
				recovered = true;
				end = true;
				finish();
			}
			g.drawString(text, 700, 10);
		}

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {

				if (moves[j][i]) {
					g.setColor(Color.yellow);
				} else if ((i * 7 + j) % 2 == 0) {
					g.setColor(Color.gray);
				} else {
					g.setColor(Color.orange);
				}
				g.fillRect(Game.SCREEN_WIDTH / 2 - 4 * FIELDSIZE + j
						* FIELDSIZE, Game.SCREEN_HEIGHT / 2 - 4 * FIELDSIZE + i
						* FIELDSIZE, FIELDSIZE, FIELDSIZE);

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

	private void startCountDown() {
		timer.scheduleAtFixedRate(timerTask, 1000, 10);
	}

	private void check() {
		int kings = 0;
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				String s = String.valueOf(field[j][i]);
				if (s.contains("game.Koenig")) {
					kings++;
				}
			}
		}
		if (kings < 2) {
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {
					String s = String.valueOf(field[j][i]);
					if (s.contains("game.Koenig")) {
						if ((Game.getNetworkInstance().getRole() == NetworkRole.HOST)) {
							if (field[j][i].getColor() == COLOR.WHITE) {
								recovered = true;
								end = true;
								finish();
							} else {
								recovered = false;
								end = true;
								finish();
							}
						}
						if ((Game.getNetworkInstance().getRole() == NetworkRole.CLIENT)) {
							if (field[j][i].getColor() == COLOR.BLACK) {
								recovered = true;
								end = true;
								finish();
							} else {
								recovered = false;
								end = true;
								finish();
							}
						}
					}
				}
			}
		}
	}

	public void finish() {
		end = false;
		String text;
		String[] options = { "Neu", "Beenden" };
		if (recovered == true) {
			text = "Du hast gewonnen";
		} else {
			text = "Du hast verloren";
		}

		int n = JOptionPane.showOptionDialog(null, text, "Spiel beendet",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		if (n == 0) {
			try {
				Game.main(null);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		if (n == 1) {
			Game.exit();
		}
	}
}
