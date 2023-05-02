package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Key extends Entity{
	
	public Obj_Key(GamePanel gamePanel) {
		super(gamePanel);
		name = "Key";
		down1 = setup("/objects/key", gamePanel.tileSize, gamePanel.tileSize);
	}
	
}
