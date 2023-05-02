package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class Mimic extends Entity {
	GamePanel gamePanel;
	public Mimic(GamePanel gamePanel) {
		super(gamePanel);
		this.gamePanel = gamePanel;
		name = "Mimic";
		speed = 1;
		maxLife = 5;
		life = maxLife;
		setImage();
		type = 2;
		atk = 6;
		def = 1;
		exp = 5;
		coin = 50;
	}
	
	public void setImage() {
		up1 = setup("/monster/mimic_1", gamePanel.tileSize, gamePanel.tileSize);
		up2 = setup("/monster/mimic_2", gamePanel.tileSize, gamePanel.tileSize);
		down1 = setup("/monster/mimic_1", gamePanel.tileSize, gamePanel.tileSize);
		down2 = setup("/monster/mimic_2", gamePanel.tileSize, gamePanel.tileSize);
		left1 = setup("/monster/mimic_1", gamePanel.tileSize, gamePanel.tileSize);
		left2 = setup("/monster/mimic_2", gamePanel.tileSize, gamePanel.tileSize);
		right1 = setup("/monster/mimic_1", gamePanel.tileSize, gamePanel.tileSize);
		right2 = setup("/monster/mimic_2", gamePanel.tileSize, gamePanel.tileSize);
	}
	
	public void setAction() {
		actionLockCounter++;
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			if(i<=25) direction = "up";
			if(i>25 && i<=50) direction = "down";
			if(i>50 && i<=75) direction = "left";
			if(i>75 && i<=100) direction = "right";
			actionLockCounter=0;
		}
		
	}
}
