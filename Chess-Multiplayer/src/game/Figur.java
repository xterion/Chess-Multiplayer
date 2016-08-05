package game;

public class Figur {

	public enum COLOR{
		WHITE, BLACK;
	}
	
	private final COLOR color;
	private int x;
	private int y;
	
	public Figur(int x, int y, COLOR color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
}