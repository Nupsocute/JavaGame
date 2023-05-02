 package entity;

import java.util.Random;

import main.GamePanel;

public class npcOldMan extends Entity{
	
	public int count = 0;
	public npcOldMan(GamePanel gamePanel) {
		super(gamePanel);
		type = 3;
		name = "oldMan";
		direction = "down";
		speed = 1 ;
//		worldX = gp.tileSize * 40 ;
//		worldY = gp.tileSize * 43 ;
		
		getNPCImage();
	}
	public void getNPCImage() {
		up1 = setup("/npc/oldman_up_1", gamePanel.tileSize, gamePanel.tileSize);
		up2 = setup("/npc/oldman_up_2", gamePanel.tileSize, gamePanel.tileSize);
		down1 = setup("/npc/oldman_down_1", gamePanel.tileSize, gamePanel.tileSize);
		down2 = setup("/npc/oldman_down_2", gamePanel.tileSize, gamePanel.tileSize);
		left1 = setup("/npc/oldman_left_1", gamePanel.tileSize, gamePanel.tileSize);
		left2 = setup("/npc/oldman_left_2", gamePanel.tileSize, gamePanel.tileSize);
		right1 = setup("/npc/oldman_right_1", gamePanel.tileSize, gamePanel.tileSize);
		right2 = setup("/npc/oldman_right_2", gamePanel.tileSize, gamePanel.tileSize);
	}
	
	public void setAction() {
		actionLockCounter++;
		if(actionLockCounter==120) {
			Random random = new Random();
			int rd = random.nextInt(100)+1;
			if(rd<=25 && rd>0) { 
				direction = "up";
			}
			if(rd>25 && rd<=50) {
				direction = "down";	
			}
			if(rd>50 && rd<=75) {
				direction = "left";
			}
			if(rd>75 && rd<=100) {
				direction = "right";
			}
			actionLockCounter=0;
		}
		
	}
}
