package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Figure {

	public enum COLOR{
		WHITE, BLACK;
	}
	
	public enum TYPE{
		BAUER, TURM, PFERD, LAEUFER, DAME, KOENIG;
	}
	
	protected final COLOR color;
	protected final TYPE type;
	protected int x;
	protected int y;
	protected boolean isSelected = false;
	
	protected Image img;
	
	public Figure(int x, int y, TYPE type, COLOR color){
		this.x = x;
		this.y = y;
		this.type = type;
		this.color = color;
		
		try {
			img = new Image("res/" + this.type.toString().toLowerCase() + "_" + this.color.toString().toLowerCase() + ".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	//draw the figure
	public  void render(Graphics g){
		//check type and color then draw
		img.draw(Game.SCREEN_WIDTH/2 - 4*Board.FIELDSIZE + x*Board.FIELDSIZE, Game.SCREEN_HEIGHT/2 - 4*Board.FIELDSIZE + y*Board.FIELDSIZE, Board.FIELDSIZE, Board.FIELDSIZE);
	}
	
	//update the logic
	public void update(){
        
	}
	
	//on click on figure check the valid positions where the figure can move to
	public abstract void checkFields();
	
	//remove the figure from the field
	public void remove(){
	}

	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setSelected(boolean selected){
		this.isSelected = selected;
		
		if(selected){
			checkFields();
		}
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public COLOR getColor(){
		return this.color;
	}
	
	public TYPE getType(){
		return type;
	}
	
	protected boolean checkColor(int x, int y){
		boolean notSame = true;
		
		if(Board.field[x][y] != null){
			notSame = !Board.field[x][y].getColor().equals(color);
		}
		return notSame;
	}
}