package object;

import entity.Entity;
import main.GamePanel;

public class Obj_SpecialKey extends Entity{
	
	public Obj_SpecialKey(GamePanel gamePanel) {
		super(gamePanel);
		name = "SpecialKey";
		down1 = setup("/objects/skey", gamePanel.tileSize, gamePanel.tileSize);
	}
	
}
