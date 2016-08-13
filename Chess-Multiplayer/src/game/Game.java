package game;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import network.NetworkInstance;
import network.NetworkManager;
import network.NetworkRole;

public class Game extends BasicGame {

	// DO NOT CHANGE RESOLUTION! if you do search for: //RESOLUTION
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	private static AppGameContainer app;
	private static Graphics graphics;
	private static Game game;
	private static Input input;

	public static Board board;

	private static NetworkInstance networkInstance;

	public static void main(String[] args) throws SlickException {

		networkInstance = NetworkManager.selectRole();

		game = new Game();
		app = new AppGameContainer(game);
		app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
		app.setShowFPS(false);
		app.setVSync(true);
		app.start();
	}

	public static void exit() {
		app.exit();
	}

	public Game() throws SlickException {
		super("Check");
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		board.render(graphics);
	}

	/**
	 * Loading and creating resources.
	 */
	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		graphics = gameContainer.getGraphics();
		input = gameContainer.getInput();

		board = new Board();
		board.init();
		
	}

	@Override
	public void update(GameContainer gameContainer, int arg1) throws SlickException {
			board.update();
			networkInstance.checkInput();
	}

	public static Input getInput() {
		return input;
	}

	public static Graphics getGraphics() {
		return graphics;
	}

	public static NetworkInstance getNetworkInstance() {
		return networkInstance;
	}

}
