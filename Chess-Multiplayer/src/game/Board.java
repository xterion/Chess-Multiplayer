package game;

import game.Figure.COLOR;

public class Board {

	public static Figure field[][] = new Figure[7][7];
	public static boolean moves[][] = new boolean[7][7];
	
	//init a new chess-board
	public void init(){
		//BLACK
		field[0][0] = new Turm(0,0,COLOR.BLACK);
		field[1][0] = new Pferd(1,0,COLOR.BLACK);
		field[2][0] = new Laeufer(2,0,COLOR.BLACK);
		field[3][0] = new Dame(3,0,COLOR.BLACK);
		field[4][0] = new Koenig(4,0,COLOR.BLACK);
		field[5][0] = new Laeufer(5,0,COLOR.BLACK);
		field[6][0] = new Pferd(6,0,COLOR.BLACK);
		field[7][0] = new Turm(7,0,COLOR.BLACK);
		
		for(int i = 0; i <= 7; i++){
			field[i][1] = new Bauer(i,1,COLOR.BLACK);
		}
		
		//WHITE
		field[0][7] = new Turm(0,0,COLOR.WHITE);
		field[1][7] = new Pferd(1,0,COLOR.WHITE);
		field[2][7] = new Laeufer(2,0,COLOR.WHITE);
		field[3][7] = new Dame(3,0,COLOR.WHITE);
		field[4][7] = new Koenig(4,0,COLOR.WHITE);
		field[5][7] = new Laeufer(5,0,COLOR.WHITE);
		field[6][7] = new Pferd(6,0,COLOR.WHITE);
		field[7][7] = new Turm(7,0,COLOR.WHITE);
		
		for(int i = 0; i <= 7; i++){
			field[i][6] = new Bauer(i,1,COLOR.WHITE);
		}
	}
	
	public void update(){
		
	}
	
	public void render(){
		//render chess-fields / possible moves
		//render figures
	}
	
	
}
