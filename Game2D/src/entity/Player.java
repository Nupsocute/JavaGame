package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;
import object.Obj_RedSword;

public class Player extends Entity {
	GamePanel gamePanel;
	KeyHandler keyHandler;
	public final int screenX;
	public final int screenY;
	public int hasKey, hasSpecialKey;
	int standCounter = 0;
	public int obj[] = new int[40];
	public int monster[] = new int[40];

	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		super(gamePanel);
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
		screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;
		hitbox = new Rectangle(10, 16, 28, 32);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;	
		
		attackArea.x = 0;
		attackArea.y = 0;
		attackArea.width = 48;
		attackArea.height = 48;
		type = 1;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}

	public void setDefaultValues() {
		worldX = gamePanel.tileSize*3 ;
		worldY = gamePanel.tileSize*3 ;
		speed = 10;
		direction = "down";
		level = 0;
		maxLife = 10;
		life = maxLife;
		maxMana = 5;
		mana = maxMana;
		strength = 1;
		dex = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 500;
		currentWeapon = new Obj_RedSword(gamePanel);
		atk = getAtk();
		def = dex;	
		hasKey = 5;
		hasSpecialKey = 0;
	}
	
	public int getAtk() {
		return strength * 1;
	}

	public void getPlayerImage() {
		
			up1 = setup("/player/up_1", gamePanel.tileSize, gamePanel.tileSize);
			up2 = setup("/player/up_2", gamePanel.tileSize, gamePanel.tileSize);
			up3 = setup("/player/up_3", gamePanel.tileSize, gamePanel.tileSize);
			up4 = setup("/player/up_4", gamePanel.tileSize, gamePanel.tileSize);

			down1 = setup("/player/down_1", gamePanel.tileSize, gamePanel.tileSize);
			down2 = setup("/player/down_2", gamePanel.tileSize, gamePanel.tileSize);
			down3 = setup("/player/down_3", gamePanel.tileSize, gamePanel.tileSize);
			down4 = setup("/player/down_4", gamePanel.tileSize, gamePanel.tileSize);

			left1 = setup("/player/left_1", gamePanel.tileSize, gamePanel.tileSize);
			left2 = setup("/player/left_2", gamePanel.tileSize, gamePanel.tileSize);
			left3 = setup("/player/left_3", gamePanel.tileSize, gamePanel.tileSize);
			left4 = setup("/player/left_4", gamePanel.tileSize, gamePanel.tileSize);

			right1 = setup("/player/right_1", gamePanel.tileSize, gamePanel.tileSize);
			right2 = setup("/player/right_2", gamePanel.tileSize, gamePanel.tileSize);
			right3 = setup("/player/right_3", gamePanel.tileSize, gamePanel.tileSize);
			right4 = setup("/player/right_4", gamePanel.tileSize, gamePanel.tileSize);

			up_left1 = setup("/player/left_1", gamePanel.tileSize, gamePanel.tileSize);
			up_left2 = setup("/player/left_2", gamePanel.tileSize, gamePanel.tileSize);
			up_left3 = setup("/player/left_3", gamePanel.tileSize, gamePanel.tileSize);
			up_left4 = setup("/player/left_4", gamePanel.tileSize, gamePanel.tileSize);

			up_right1 = setup("/player/right_1", gamePanel.tileSize, gamePanel.tileSize);
			up_right2 = setup("/player/right_2", gamePanel.tileSize, gamePanel.tileSize);
			up_right3 = setup("/player/right_3", gamePanel.tileSize, gamePanel.tileSize);
			up_right4 = setup("/player/right_4", gamePanel.tileSize, gamePanel.tileSize);

			down_left1 = setup("/player/left_1", gamePanel.tileSize, gamePanel.tileSize);
			down_left2 = setup("/player/left_2", gamePanel.tileSize, gamePanel.tileSize);
			down_left3 = setup("/player/left_3", gamePanel.tileSize, gamePanel.tileSize);
			down_left4 = setup("/player/left_4", gamePanel.tileSize, gamePanel.tileSize);

			down_right1 = setup("/player/right_1", gamePanel.tileSize, gamePanel.tileSize);
			down_right2 = setup("/player/right_2", gamePanel.tileSize, gamePanel.tileSize);
			down_right3 = setup("/player/right_3", gamePanel.tileSize, gamePanel.tileSize);
			down_right4 = setup("/player/right_4", gamePanel.tileSize, gamePanel.tileSize);
	}
	
	public void getPlayerAttackImage() {
		attackUp1 = setup("/player/up_atk_1", gamePanel.tileSize, gamePanel.tileSize*2);
		attackUp2 = setup("/player/up_atk_2", gamePanel.tileSize, gamePanel.tileSize*2);
		
		attackDown1 = setup("/player/down_atk_1", gamePanel.tileSize, gamePanel.tileSize);
		attackDown2 = setup("/player/down_atk_2", gamePanel.tileSize, gamePanel.tileSize*2);
		
		attackLeft1 = setup("/player/left_atk_1", gamePanel.tileSize*2, gamePanel.tileSize);
		attackLeft2 = setup("/player/left_atk_2", gamePanel.tileSize*2, gamePanel.tileSize);
		
		attackRight1 = setup("/player/right_atk_1", gamePanel.tileSize*2, gamePanel.tileSize);
		attackRight2 = setup("/player/right_atk_2", gamePanel.tileSize*2, gamePanel.tileSize);	
	}

	public void attacking() {
		spriteCounter++;
		if(spriteCounter <= 5) {
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			int currentWorldX = worldX, currentWorldY = worldY;
			int hitboxWidth = hitbox.width, hitboxHeight = hitbox.height;
			
			switch(direction) {
				case "up": 
					worldY -=attackArea.height;
					break;
				case "down": 
					worldY +=attackArea.height;
					break;
				case "left": 
					worldX -=attackArea.width;
					break;
				case "right": 
					worldX +=attackArea.width;
					break;

			}
			int monsterIndex = gamePanel.colChecker.checkEntity(this, gamePanel.monster);
			damageMonster(monsterIndex);
			worldX = currentWorldX;
			worldY = currentWorldY;
			hitbox.width = hitboxWidth;
			hitbox.height = hitboxHeight;
			
		}
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void update() {
		
		if(attacking == true) {
			
			attacking();
		}
		
		else if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.enterPressed) {
			if (keyHandler.upPressed) {
				direction = "up";
			}
			if (keyHandler.downPressed) {
				direction = "down";
			}
			if (keyHandler.leftPressed) {
				direction = "left";
			}
			if (keyHandler.rightPressed) {
				direction = "right";
			}
			if (keyHandler.enterPressed) {
				gamePanel.sound.playSE(6, -10);
				
				attacking = true;
			}

			if (keyHandler.upPressed && keyHandler.leftPressed) {
				direction = "up_left";
			}
			if (keyHandler.upPressed && keyHandler.rightPressed) {
				direction = "up_right";
			}
			if (keyHandler.downPressed && keyHandler.leftPressed) {
				direction = "down_left";
			}
			if (keyHandler.downPressed && keyHandler.rightPressed) {
				direction = "down_right";
			}
			
			//tile collision
			collisionOn = false;
			gamePanel.colChecker.checkTile(this);
			//obj collision
			int objIndex = gamePanel.colChecker.checkObject(this, true);
			pickUpObj(objIndex);
			//npc collision
			int npcIndex = gamePanel.colChecker.checkEntity(this, gamePanel.npc);
			interactNPC(npcIndex);
			//monster collision
			int monsterIndex = gamePanel.colChecker.checkEntity(this, gamePanel.monster);
			contactMonster(monsterIndex);
			
			if (collisionOn == false && keyHandler.enterPressed == false) {
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
				case "up_left":
					worldY -= speed - 1;
					worldX -= speed - 1;
					break;
				case "up_right":
					worldY -= speed - 1;
					worldX += speed - 1;
					break;
				case "down_left":
					worldY += speed - 1;
					worldX -= speed - 1;
					break;
				case "down_right":
					worldY += speed - 1;
					worldX += speed - 1;
					break;
				}
			}
			keyHandler.enterPressed = false;
			
			spriteCounter++;
			if (spriteCounter > 7) {
				if (spriteNum == 1)
					spriteNum = 2;
				else if (spriteNum == 2)
					spriteNum = 3;
				else if (spriteNum == 3)
					spriteNum = 4;
				else if (spriteNum == 4)
					spriteNum = 1;
				spriteCounter = 0;
			}	
		} else {
			spriteNum = 1;
			
		}
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		if(life <= 0) gamePanel.gameState = gamePanel.gameOverState;
		
	}

	public void pickUpObj(int i) {
		if(i != 999) {
				String objectName = gamePanel.obj[i].name;
				switch(objectName) {
				case "Key" :
					hasKey++;
					gamePanel.sound.playSE(1, -10);
					gamePanel.obj[i] = null;
					this.obj[i] = 1;
					break ;
				case "Door" :
					if(hasKey>0) {
						gamePanel.sound.playSE(2, -10);
						gamePanel.obj[i] = null;
						this.obj[i] = 1;
						hasKey--;
					}
					else {
						
					}
					break;
				case "Boot":
					gamePanel.obj[i] = null;
					this.obj[i] = 1;
					speed+=1;
					break;
				case "SpecialKey" :
					gamePanel.sound.playSE(1, -10);
					hasSpecialKey++;
					gamePanel.obj[i] = null ;
					this.obj[i] = 1;
					break ;
				case "Gate" :
					if(hasSpecialKey>0) {
						gamePanel.sound.playSE(2, -10);
						gamePanel.obj[i] = null ;
						gamePanel.gameState = gamePanel.gameWin;
						hasSpecialKey--;
					}
					else {
						
					}
					break;
				
				case "Chest" :
					if(hasKey > 0) {
						gamePanel.sound.playSE(2, -10);
						hasKey--;
						coin += 100;
						gamePanel.obj[i] = null;
						this.obj[i] = 1;
					}
					else {
						
					}
				break ;
				}
				
			}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			gamePanel.gameState = gamePanel.buyState;
		}
	}
	
	
	private void contactMonster(int i) {
		if(i != 999) {
			if(invincible == false) {
				gamePanel.sound.playSE(5, -10);
				int dam = gamePanel.monster[i].atk - def;
				if(dam < 0) dam = 0;
				life -= dam;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i) {
		if(i != 999) {
			if(gamePanel.monster[i].invincible == false) {
				gamePanel.sound.playSE(4, -10);
				int dam = atk - gamePanel.monster[i].def;
				if(dam <= 0) dam = 0;
				gamePanel.monster[i].life-=dam;
				gamePanel.monster[i].invincible = true;
				if(gamePanel.monster[i].life <= 0) {
					if(i%2==0) hasKey++;
					if(i%20==0) hasSpecialKey++;
					gamePanel.monster[i].dying = true;
					exp += gamePanel.monster[i].exp;
					coin += gamePanel.monster[i].coin;
					checkLvlUp();
				}
			}
		}
	}
	
	public void checkLvlUp() {
		if(exp >= nextLevelExp) {
			level++;
			exp = exp - nextLevelExp;
			nextLevelExp += 5;
			maxLife++;
			maxMana++;
			dex++;
			strength++;
			atk = getAtk();
			gamePanel.sound.playSE(7, -10);
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int tempX = screenX, tempY = screenY;
		
		switch (direction) {
		case "up":
			if(attacking == false) {
				if (spriteNum == 1) image = up1;
				if (spriteNum == 2) image = up2;
				if (spriteNum == 3) image = up3;
				if (spriteNum == 4) image = up4;
			}
			if(attacking == true) {
				tempY = screenY - gamePanel.tileSize;
				if (spriteNum == 1) image = attackUp1;
				if (spriteNum == 2) image = attackUp2;
			}
			break;
		case "down":
			if(attacking == false) {
				if (spriteNum == 1) image = down1;
				if (spriteNum == 2) image = down2;
				if (spriteNum == 3) image = down3;
				if (spriteNum == 4) image = down4;
			}
			if(attacking == true ) {
				if (spriteNum == 1) image = attackDown1;
				if (spriteNum == 2) image = attackDown2;
			}
			break;
		case "left":
			if(attacking == false) {
				if (spriteNum == 1) image = left1;
				if (spriteNum == 2) image = left2;
				if (spriteNum == 3) image = left3;
				if (spriteNum == 4) image = left4;
			}
			if(attacking == true ) {
				tempX = screenX - gamePanel.tileSize;
				if (spriteNum == 1) image = attackLeft1;
				if (spriteNum == 2) image = attackLeft2;
				
			}
			break;
		case "right":
			if(attacking == false) {
				if (spriteNum == 1) image = right1;
				if (spriteNum == 2) image = right2;
				if (spriteNum == 3) image = right3;
				if (spriteNum == 4) image = right4;
			}
			if(attacking == true ) {
				if (spriteNum == 1) image = attackRight1;
				if (spriteNum == 2) image = attackRight2;
			}
			break;
		case "up_left":
			if(attacking == false) {
				if (spriteNum == 1) image = up_left1;
				if (spriteNum == 2) image = up_left2;
				if (spriteNum == 3) image = up_left3;
				if (spriteNum == 4) image = up_left4;
			}
			if(attacking == true ) {
				if (spriteNum == 1) image = attackLeft1;
				if (spriteNum == 2) image = attackLeft2;
			}
			break;
		case "up_right":
			if(attacking == false) {
				if (spriteNum == 1) image = up_right1;
				if (spriteNum == 2) image = up_right2;
				if (spriteNum == 3) image = up_right3;
				if (spriteNum == 4) image = up_right4;
			}
			if(attacking == true) {
				if (spriteNum == 1) image = attackRight1;
				if (spriteNum == 2) image = attackRight2;
			}
			break;
		case "down_left":
			if(attacking == false) {
				if (spriteNum == 1) image = down_left1;
				if (spriteNum == 2) image = down_left2;
				if (spriteNum == 3) image = down_left3;
				if (spriteNum == 4) image = down_left4;
			}
			if(attacking == true) {
				if (spriteNum == 1) image = attackLeft1;
				if (spriteNum == 2) image = attackLeft2;
			}
			break;
		case "down_right":
			if(attacking == false) {
				if (spriteNum == 1) image = down_right1;
				if (spriteNum == 2) image = down_right2;
				if (spriteNum == 3) image = down_right3;
				if (spriteNum == 4) image = down_right4;
			}
			if(attacking == true) {
				if (spriteNum == 1) image = attackRight1;
				if (spriteNum == 2) image = attackRight2;
			}
			break;
		}
		
		if(invincible == true) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		
		g2.drawImage(image, tempX, tempY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));	
		//player hitbox
//		g2.setColor(Color.black);
//		g2.drawRect(screenX, screenY, 48, 48);
//		g2.setColor(Color.blue);
//		g2.drawRect(screenX + 10, screenY + 16, hitbox.width, hitbox.height);
		
		
	}
}