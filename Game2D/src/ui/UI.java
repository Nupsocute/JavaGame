package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Menu;

public class UI {
	GamePanel gamePanel;
	Font Monogram, m5x7;
	public Menu[] menu;
	public int count=0, ct=0, select = 0 , counter = 0;
	public int menuScale=5, statusScale=3;
	public boolean checkCoin = true;
	
	public void getFont() {
		try {
			InputStream is;
			is = getClass().getResourceAsStream("/fonts/monogram.ttf");
			Monogram = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/fonts/m5x7.ttf");
			m5x7 = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
	}
	
	public void getGUI() {
		try {
			menu[0] = new Menu();
			menu[0].image = ImageIO.read(getClass().getResourceAsStream("/gui/menu.png"));
			menu[1] = new Menu();
			menu[1].image = ImageIO.read(getClass().getResourceAsStream("/gui/start_btn.png"));
			menu[2] = new Menu();
			menu[2].image = ImageIO.read(getClass().getResourceAsStream("/gui/opt_btn.png"));
			menu[3] = new Menu();
			menu[3].image = ImageIO.read(getClass().getResourceAsStream("/gui/exit_btn.png"));
			menu[4] = new Menu();
			menu[4].image = ImageIO.read(getClass().getResourceAsStream("/gui/back_btn.png"));
			menu[5] = new Menu();
			menu[5].image = ImageIO.read(getClass().getResourceAsStream("/gui/selected.png"));
			menu[6] = new Menu();
			menu[6].image = ImageIO.read(getClass().getResourceAsStream("/gui/hp_0.png"));
			menu[7] = new Menu();
			menu[7].image = ImageIO.read(getClass().getResourceAsStream("/gui/one_hp.png"));
			menu[8] = new Menu();
			menu[8].image = ImageIO.read(getClass().getResourceAsStream("/gui/mp.png"));
			menu[9] = new Menu();
			menu[9].image = ImageIO.read(getClass().getResourceAsStream("/gui/status.png"));
			menu[10] = new Menu();
			menu[10].image = ImageIO.read(getClass().getResourceAsStream("/gui/save_btn.png"));
			menu[11] = new Menu();
			menu[11].image = ImageIO.read(getClass().getResourceAsStream("/gui/load_btn.png"));
			menu[12] = new Menu();
			menu[12].image = ImageIO.read(getClass().getResourceAsStream("/gui/buy.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UI(GamePanel gp) {
		this.gamePanel = gp;
		this.getFont();
		menu = new Menu[20];
		getGUI();
	}
	
	public int getXCenterText(String txt, Graphics2D g2) {
		int length = (int)g2.getFontMetrics().getStringBounds(txt, g2).getWidth();
		int x = (gamePanel.screenWidth - length)/2;
		return x;
	}
	
	public int getStringLength(String txt, Graphics2D g2) {
		int length = (int)g2.getFontMetrics().getStringBounds(txt, g2).getWidth();
		return length;
	}
	
	public void drawTileScreen(Graphics2D g2) {
		g2.setColor(new Color(9, 132, 227));
		g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
		g2.setFont(Monogram.deriveFont(Font.BOLD, 96f));
		String name = "Unknown game";
		g2.setColor(new Color(38,43,68));
		g2.drawString(name, getXCenterText(name, g2)+4, 200+4);
		g2.setColor(Color.white);
		g2.drawString(name, getXCenterText(name, g2), 200);
		int x, y;
		x = (gamePanel.screenWidth-60*menuScale)/2;
		y = 300;
	
		g2.drawImage(menu[0].image, x, y, 60*menuScale, 80*menuScale, null);
		x+=15*5;
		y+=17*5;
		if(count==0) g2.drawImage(menu[5].image, x, y, 30*menuScale, 14*menuScale, null);
		g2.drawImage(menu[1].image, x, y, 30*menuScale, 14*menuScale, null);
		
		y+=16*5;
		if(count==1) g2.drawImage(menu[5].image, x, y, 30*menuScale, 14*menuScale, null);
		g2.drawImage(menu[11].image, x, y, 30*menuScale, 14*menuScale, null);
		
		y+=16*5;
		if(count==2) g2.drawImage(menu[5].image, x, y, 30*menuScale, 14*menuScale, null);
		g2.drawImage(menu[3].image, x, y, 30*menuScale, 14*menuScale, null);
	}

	public void drawPauseScreen(Graphics2D g2) {
		int x, y;
		x = (gamePanel.screenWidth-60*menuScale)/2;
		y = (gamePanel.screenHeight-80*menuScale)/2;
		g2.drawImage(menu[0].image, x, y, 60*menuScale, 80*menuScale, null);
		x+=15*5;
		y+=17*5;
		if(ct==0) g2.drawImage(menu[5].image, x, y, 30*menuScale, 14*menuScale, null);
		g2.drawImage(menu[4].image, x, y, 30*menuScale, 14*menuScale, null);
		
		y+=16*5;
		if(ct==1) g2.drawImage(menu[5].image, x, y, 30*menuScale, 14*menuScale, null);
		g2.drawImage(menu[10].image, x, y, 30*menuScale, 14*menuScale, null);
		
		y+=16*5;
		if(ct==2) g2.drawImage(menu[5].image, x, y, 30*5, 14*5, null);
		g2.drawImage(menu[3].image, x, y, 30*menuScale, 14*menuScale, null);
	}
	
	public void drawStatus(Graphics2D g2) {
		g2.drawImage(menu[6].image, 0, 0, 77*statusScale, 24*statusScale, null);
		g2.setFont(Monogram.deriveFont(Font.PLAIN, 30f));
		g2.setColor(Color.white); 
		g2.drawString(""+gamePanel.player.coin, 35*statusScale, 16*statusScale+12);
		
		double oneScale = (double) 10*statusScale*5/gamePanel.player.maxLife;
		double hpBarValue = oneScale*gamePanel.player.life;
		g2.setColor(new Color(255, 0, 68));
		g2.fillRect(26*statusScale, 2*statusScale, (int)hpBarValue, 2*statusScale);
		g2.setColor(new Color(158, 40, 53));
		g2.fillRect(26*statusScale, 4*statusScale, (int)hpBarValue, 1*statusScale);
		
		for(int i=0; i<gamePanel.player.mana; i++) {
			g2.drawImage(menu[8].image, 26*statusScale+i*9*statusScale, 8*statusScale, 9*statusScale, 3*statusScale, null);
		}
		
		g2.setColor(Color.white);
		int add = 20;
		g2.drawString("Level: " + gamePanel.player.level, 10, 24*statusScale + add);
		add += 30;
		g2.drawString("Exp: " + gamePanel.player.exp + "/" + gamePanel.player.nextLevelExp , 10, 24*statusScale + add);
		add += 30;
		g2.drawString("Key: " + gamePanel.player.hasKey , 10, 24*statusScale + add);
		add += 30;
		g2.drawString("Special Key: " + gamePanel.player.hasSpecialKey, 10, 24*statusScale + add);
		
		if(checkCoin == false) {
			counter++;
			g2.setColor(Color.red);
			g2.drawString("Not enough coin", 50, gamePanel.screenHeight/2 - 15);
			if(counter==60) {
				checkCoin = true ;
				counter=0;
			}
		}
	}
	
	public void drawCharacterScreen(Graphics2D g2) {
		g2.setFont(Monogram.deriveFont(Font.PLAIN, 30f));
		g2.setColor(Color.white); 
		int xFra, yFra, widthFra, heightFra;
		widthFra = 65*menuScale;
		heightFra = 86*menuScale;
		xFra = (gamePanel.screenWidth - widthFra)/4;
		yFra = (gamePanel.screenHeight - heightFra)/2;
		int lineHeight = 30;
		g2.drawImage(menu[9].image, xFra, yFra, widthFra, heightFra, null);
		xFra = xFra + 20*menuScale;
		yFra = yFra + 10*menuScale + lineHeight - 3;
		g2.drawString("Hp: " + gamePanel.player.life + "/" + gamePanel.player.maxLife , xFra, yFra);
		yFra = yFra + 10*menuScale + 3;
		g2.drawString("Mana: " + gamePanel.player.mana + "/" + gamePanel.player.maxMana, xFra, yFra);
		yFra = yFra + 10*menuScale + 3;
		g2.drawString("Strength: " + gamePanel.player.strength, xFra, yFra);
		yFra = yFra + 10*menuScale + 6;
		g2.drawString("Defend: " + gamePanel.player.def, xFra, yFra);
		yFra = yFra + 10*menuScale + 3;
		g2.drawString("Attack: " + gamePanel.player.atk, xFra, yFra);
		yFra = yFra + 10*menuScale + 12;
		g2.drawString("Dex: " + gamePanel.player.dex, xFra, yFra);
		
	}
	
	public void drawGameOverScreen(Graphics2D g2) {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
		g2.setFont(Monogram.deriveFont(Font.PLAIN, 96f));
		g2.setColor(Color.white);
		String s = "GAME OVER";
		g2.drawString(s, (gamePanel.screenWidth - getStringLength(s, g2))/2, gamePanel.screenHeight/2 );
	}
	
	public void drawBuyState(Graphics2D g2) {
		int x, y, width, height;
		width = 150*menuScale;
		height = 30*menuScale;
		x = gamePanel.screenWidth/2 - width/2;
		y = gamePanel.screenHeight - height;
		g2.drawImage(menu[12].image, x, y, width, height, null);
		g2.setFont(Monogram.deriveFont(Font.PLAIN, 32f));
		g2.setColor(Color.black);
		String s = "Full recovery health and mana with 300 coins ?";
		y+=50;
 		g2.drawString(s, x + (width-getStringLength(s, g2))/2 , y);
 		String sY = "Yes";
 		y+=32;
 		if(select == 0) g2.drawString(">", x + (width-getStringLength(s, g2))/2 - 25, y);
 		g2.drawString(sY, x + (width-getStringLength(s, g2))/2 , y);
 		String sN = "No";
 		y+=32;
 		if(select == 1) g2.drawString(">", x + (width-getStringLength(s, g2))/2 - 25, y);
 		g2.drawString(sN, x + (width-getStringLength(s, g2))/2 , y);
	}
	
	public void drawGameWin(Graphics2D g2) {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
		g2.setFont(Monogram.deriveFont(Font.PLAIN, 96f));
		g2.setColor(Color.white);
		String s = "Win";
		g2.drawString(s, (gamePanel.screenWidth - getStringLength(s, g2))/2, gamePanel.screenHeight/2 );
	}
	
	public void draw(Graphics2D g2) {
		g2.setFont(Monogram.deriveFont(Font.PLAIN, 24f));
		g2.setColor(Color.black);
		
		if(gamePanel.gameState == gamePanel.tileState) drawTileScreen(g2);
		
		if(gamePanel.gameState == gamePanel.pauseState) drawPauseScreen(g2);
		
		if(gamePanel.gameState == gamePanel.playState) {
				String loc = "Location: "+ "(" + gamePanel.player.worldX/gamePanel.tileSize + "," + gamePanel.player.worldY/gamePanel.tileSize + ")";
				g2.drawString("FPS: " + gamePanel.currentFPS, gamePanel.screenWidth-getStringLength(loc,g2), 20);
				g2.drawString(loc, gamePanel.screenWidth-getStringLength(loc,g2), 40);
				drawStatus(g2);
		}
		
		if(gamePanel.gameState == gamePanel.characterState) drawCharacterScreen(g2);
		
		if(gamePanel.gameState == gamePanel.gameOverState) drawGameOverScreen(g2);
		
		if(gamePanel.gameState == gamePanel.buyState) drawBuyState(g2);
		
		if(gamePanel.gameState == gamePanel.gameWin) drawGameWin(g2);
	}

}
