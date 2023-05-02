package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.ScaleImage;

public class Entity {
	
	GamePanel gamePanel;
	public int worldX, worldY, speed;
	public BufferedImage up1, up2, up3, up4;
	public BufferedImage down1, down2, down3, down4;
	public BufferedImage left1, left2, left3, left4;
	public BufferedImage right1, right2, right3, right4;
	public BufferedImage up_left1, up_left2, up_left3, up_left4;
	public BufferedImage up_right1, up_right2, up_right3, up_right4;
	public BufferedImage down_left1, down_left2, down_left3, down_left4;
	public BufferedImage down_right1, down_right2, down_right3, down_right4;
	public BufferedImage image, image1;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	
	public String name;
	public boolean collision = false;
	public String direction = "down";
	public Rectangle hitbox = new Rectangle(0,0,48,48);
	public int hitboxDefaultX = hitbox.x, hitboxDefaultY = hitbox.y;
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	public int spriteCounter = 0;
	public int spriteNum = 1;	
	public boolean invincible = false;
	public int invincibleCounter = 0;
	public boolean attacking = false;
	public boolean alive = true, dying = false;
	public int dyingCounter = 0;
	public int type; //1 player, 2 monster, 0 obj , 3 npc
	
	//status
	public int level, nextLevelExp, exp, dex, strength, atk, def, coin;
	public Entity currentWeapon;
	public int maxLife, life;
	public int maxMana, mana;
	
	//item att
	public int atkVal, defVal;

	public Entity(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public BufferedImage setup(String imgPath, int width, int height) {
		ScaleImage sImage = new ScaleImage();
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(imgPath + ".png"));
			img = sImage.scaleImage(img, width, height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX + gamePanel.player.screenX - gamePanel.player.worldX;
		int screenY = worldY + gamePanel.player.screenY - gamePanel.player.worldY;
		
		switch (direction) {
		case "up":
			if (spriteNum == 1)
				image = up1;
			else if (spriteNum == 2)
				image = up2;
			break;
		case "down":
			if (spriteNum == 1)
				image = down1;
			else if (spriteNum == 2)
				image = down2;
			break;
		case "left":
			if (spriteNum == 1)
				image = left1;
			else if (spriteNum == 2)
				image = left2;
			break;
		case "right":
			if (spriteNum == 1)
				image = right1;
			else if (spriteNum == 2)
				image = right2;
			break;
		}
		
		//monster hpbar
		if(type == 2) {
			double oneScale = (double) gamePanel.tileSize/maxLife;
			double hpBarValue = oneScale*life;
			g2.setColor(Color.black);
			g2.fillRect(screenX, screenY-15, gamePanel.tileSize, 10);
			g2.setColor(new Color(255, 0, 68));
			g2.fillRect(screenX, screenY-15, (int)hpBarValue, 10);
		}
		
		
		if(invincible == true) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		
		if(dying == true) dyingAnimation(g2);
			

//		}
		
		g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
		if(invincible == true) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		//entity hitbox
//		g2.setColor(Color.black);
//		g2.drawRect(screenX + hitboxDefaultX, screenY + hitboxDefaultY, hitbox.width, hitbox.height);
	}

	public void dyingAnimation(Graphics2D g2) {
		dyingCounter++;
		if(dyingCounter <= 5) changeAlpha(g2,0f);
		if(dyingCounter > 5 && dyingCounter<= 10) changeAlpha(g2,1f);
		if(dyingCounter > 10 && dyingCounter<= 15) changeAlpha(g2,0f);
		if(dyingCounter > 15 && dyingCounter<= 20) changeAlpha(g2,1f);
		if(dyingCounter > 20 && dyingCounter<= 25) changeAlpha(g2,0f);
		if(dyingCounter > 25 && dyingCounter<= 30) changeAlpha(g2,1f);
		if(dyingCounter > 30 && dyingCounter<= 35) changeAlpha(g2,0f);
		if(dyingCounter > 35 && dyingCounter<= 40) changeAlpha(g2,1f);
		if(dyingCounter > 40) {dying = false; alive = false;}
	}
	
	public void changeAlpha(Graphics2D g2, float alpha) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}
	
	public void setAction() {
		
	}
	
	public void update() {
		setAction();
		collisionOn = false;
		gamePanel.colChecker.checkTile(this);
		gamePanel.colChecker.checkObject(this,false); 
//		gamePanel.colChecker.checkEntity(this, gamePanel.monster); 
		boolean contactPlayer = gamePanel.colChecker.checkPlayer(this);
		if(this.type ==2 && contactPlayer == true) {
			if(gamePanel.player.invincible == false) {
				gamePanel.sound.playSE(5, -10);
				int dam = atk - gamePanel.player.def;
				if(dam < 0) dam = 0;
				gamePanel.player.life -= dam;
				gamePanel.player.invincible = true;
			}			
		}
		
		if (collisionOn == false) {
			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1)
				spriteNum = 2;
			else if (spriteNum == 2)
				spriteNum = 1;
			spriteCounter = 0;
		}
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 40) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
}
