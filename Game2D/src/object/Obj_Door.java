package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Door extends Entity{
	public Obj_Door(GamePanel gamePanel) {
		super(gamePanel);
		name = "Door";
		down1 = setup("/objects/door", gamePanel.tileSize, gamePanel.tileSize);
		collision = true ;
	}
}
