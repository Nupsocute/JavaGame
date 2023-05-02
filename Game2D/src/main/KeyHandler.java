package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, xPressed;
	GamePanel gamePanel;
	
	public KeyHandler(GamePanel gamePanel) {
		super();
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(gamePanel.gameState == gamePanel.tileState) tileState(code);
			
		else if(gamePanel.gameState == gamePanel.playState) playState(code);
		
		else if(gamePanel.gameState == gamePanel.pauseState) pauseState(code);
		
		else if(gamePanel.gameState == gamePanel.characterState) characterState(code);
		
		else if(gamePanel.gameState == gamePanel.gameOverState) gameOverState(code);
		
		else if(gamePanel.gameState == gamePanel.buyState) buyState(code);
		
		else if(gamePanel.gameState == gamePanel.gameWin) gameWin(code);
	}
	
	public void tileState(int code) {
		if(code == KeyEvent.VK_W) {
			gamePanel.ui.count--;
			if(gamePanel.ui.count<0) gamePanel.ui.count=2;
		}
		if(code == KeyEvent.VK_S) {
			gamePanel.ui.count++;
			if(gamePanel.ui.count>2) gamePanel.ui.count=0;
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gamePanel.ui.count==0) {
				gamePanel.gameState = gamePanel.playState;
				gamePanel.restart();
				gamePanel.ui.ct=0;
			}
			if(gamePanel.ui.count==1) {
				gamePanel.cn.load();
				gamePanel.gameState = gamePanel.playState;
			}
			if(gamePanel.ui.count==2) System.exit(0);
		}
	}
	
	public void playState(int code) {
		if(code == KeyEvent.VK_W) upPressed = true;
		if(code == KeyEvent.VK_S) downPressed = true;
		if(code == KeyEvent.VK_A) leftPressed = true;
		if(code == KeyEvent.VK_D) rightPressed = true;
		if(code == KeyEvent.VK_X) gamePanel.gameState = gamePanel.characterState;
		if(code == KeyEvent.VK_ENTER) enterPressed = true;
		if(code == KeyEvent.VK_ESCAPE) gamePanel.gameState = gamePanel.pauseState;
	}
	
	public void pauseState(int code) {
		if(code == KeyEvent.VK_W) {
			gamePanel.ui.ct--;
			if(gamePanel.ui.ct<0) gamePanel.ui.ct=2;
		}
		if(code == KeyEvent.VK_S) {
			gamePanel.ui.ct++;
			if(gamePanel.ui.ct>2) gamePanel.ui.ct=0;
		}
		if(code == KeyEvent.VK_ESCAPE) gamePanel.gameState = gamePanel.playState;
		if(code == KeyEvent.VK_ENTER) {
			if(gamePanel.ui.ct==0) gamePanel.gameState = gamePanel.playState;
			if(gamePanel.ui.ct==1) {
				gamePanel.cn.save();
				gamePanel.gameState = gamePanel.playState;
			}
			if(gamePanel.ui.ct==2) {
				gamePanel.gameState = gamePanel.tileState;
				gamePanel.ui.count=0;
			}
		}
	}
	
	public void characterState(int code) {
		if(code == KeyEvent.VK_X) gamePanel.gameState = gamePanel.playState;
	}
	
	public void gameOverState(int code) {
		if(code == KeyEvent.VK_ENTER) gamePanel.gameState = gamePanel.tileState;
	}
	
	public void gameWin(int code) {
		if(code == KeyEvent.VK_ENTER) gamePanel.gameState = gamePanel.tileState;
	}
	
	public void buyState(int code) {
		if(code == KeyEvent.VK_W) {
			gamePanel.ui.select--;
			if(gamePanel.ui.select<0) gamePanel.ui.select=1;
		}
		if(code == KeyEvent.VK_S) {
			gamePanel.ui.select++;
			if(gamePanel.ui.select>1) gamePanel.ui.select=0;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			if(gamePanel.ui.select==0) {
				if(gamePanel.player.coin > 300) {
					gamePanel.player.life = gamePanel.player.maxLife;
					gamePanel.player.coin -= 300;
					gamePanel.gameState = gamePanel.playState;
				
				}
				else {
					gamePanel.gameState = gamePanel.playState;
					gamePanel.ui.checkCoin = false ;
				}
			}
			if(gamePanel.ui.select==1) {
				gamePanel.gameState = gamePanel.playState;
				gamePanel.ui.select = 0;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) upPressed = false;
		if(code == KeyEvent.VK_S) downPressed = false;
		if(code == KeyEvent.VK_A) leftPressed = false;
		if(code == KeyEvent.VK_D) rightPressed = false;
	}

}
