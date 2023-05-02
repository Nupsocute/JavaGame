package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("02.png"));
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("2D game");
		window.setIconImage(icon.getImage());
		GamePanel gamePanel = new GamePanel();
		Sound sound = new Sound();
		window.add(gamePanel);
		window.pack();
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.startGameThread();
		sound.startSound();
		gamePanel.setupGame();
	}

}
