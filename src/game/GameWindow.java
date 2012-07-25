package game;

import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	public GameWindow(Game game, boolean fullscreen) {
		this.game = game;
		this.game.start();
		add(game);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(game.getWidth(), game.getHeight());
		
		if(fullscreen) {
			Toolkit t = Toolkit.getDefaultToolkit();
			setSize(t.getScreenSize().width, t.getScreenSize().height);
			this.setUndecorated(true);
		}
		
		setResizable(false);
		setVisible(true);
	}
}
