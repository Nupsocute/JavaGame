package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Chest extends Entity{
	
	public Obj_Chest(GamePanel gamePanel) {
		super(gamePanel);
		name = "Chest";
		hitboxDefaultY = 6;
		hitbox.height = 42;
		down1 = setup("/objects/chest_close", gamePanel.tileSize, gamePanel.tileSize);
		collision = true;
	}
}
