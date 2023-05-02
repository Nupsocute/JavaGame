package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import database.ConnectDataBase;
import entity.Entity;
import entity.Player;
import tile.TileManager;
import ui.UI;

public class GamePanel extends JPanel implements Runnable{
	//screen
	final int originalTileSize = 16;
	public final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 24, maxScreenRow = 16;
	public final int screenWidth = tileSize * maxScreenCol, screenHeight = tileSize * maxScreenRow;
	int FPS = 60;
	public long currentFPS;

	//world
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = maxWorldCol * tileSize;
	public final int worldHeight = maxWorldRow * tileSize;
	
	//game state
	public int gameState;
	public final int tileState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int characterState = 3;
	public final int gameOverState = 4;
	public final int buyState = 5;
	public final int gameWin = 6;
	
	//key
	public KeyHandler keyHandler = new KeyHandler(this);
	public Player player = new Player(this, keyHandler);
	
	//entity
	public Entity obj[] = new Entity[100];
	public Entity monster[] = new Entity[100];
	public Entity npc[] = new Entity[100];
	public Entity entity = new Entity(this);
	ArrayList<Entity> entityList = new ArrayList<>();
	public AssetSetter assetSetter = new AssetSetter(this);
	public TileManager tileManager = new TileManager(this);
	public CollisionChecker colChecker = new CollisionChecker(this);
	int count = 0;
	
	//save load
	public ConnectDataBase cn = new ConnectDataBase(this);
	
	//ui
	public UI ui = new UI(this);
	
	//game thread
	Thread gameThread;
	
	//sound
	public Sound sound = new Sound();
	
	//icon
	ImageIcon icon = new ImageIcon(getClass().getResource("/objects/key.png"));
	
	
	public GamePanel() {
		this.setBackground(Color.CYAN);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	public void setupGame() {
		assetSetter.setObject();
		assetSetter.setMonster();
		assetSetter.setNPCs();
		gameState = tileState;
	}
	
	public void restart() {
		player.setDefaultValues();
		assetSetter.setObject();
		assetSetter.setMonster();
		assetSetter.setNPCs();
	}
	
	public void update() {
		if(gameState == playState) {
			player.update();
			for(int i=0; i<monster.length; i++) {
				if(monster[i] != null) {
					if(monster[i].alive == true && monster[i].dying == false) monster[i].update();
					if(monster[i].alive == false) {monster[i] = null; count++;}
				}
			}
			for(int i=0; i<npc.length; i++) if(npc[i] != null) npc[i].update();
			
		}
		else {
			//Do nothing
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(gameState==tileState) {
			ui.draw(g2);
		}
		else {
			tileManager.draw(g2);
			
			entityList.add(player);
			
			for(int i=0; i<obj.length; i++) if(obj[i] != null) entityList.add(obj[i]);
			for(int i=0; i<monster.length; i++) if(monster[i] != null) entityList.add(monster[i]);
			for(int i=0; i<npc.length; i++) if(npc[i] != null) entityList.add(npc[i]);
			
			Collections.sort(entityList, new Comparator<Entity>() {
				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}	
			});
			
			for(int i=0; i<entityList.size(); i++) entityList.get(i).draw(g2);
			
			entityList.clear();
			
			ui.draw(g2);
			g2.dispose();
		}
		
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS; //0.01(6) seconds
		double delta = 0, lastTime = System.nanoTime();
		long currentTime, timer = 0, drawCount = 0;
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/ drawInterval;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if(timer > 1000000000) {
				currentFPS = drawCount;
				timer = 0;
				drawCount = 0;
			}
		}
		
	}
	
}
