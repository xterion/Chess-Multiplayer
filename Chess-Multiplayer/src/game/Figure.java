package game;

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
	
	public Figure(int x, int y, TYPE type, COLOR color){
		this.x = x;
		this.y = y;
		this.type = type;
		this.color = color;
	}
	
	//draw the figure
	public abstract void render();
	
	//update the logic
	public abstract void update();
	
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
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public COLOR getColor(){
		return this.color;
	}
	
	protected boolean canMove(int x, int y){
		return !Board.field[x][y].getColor().equals(color);
	}
}