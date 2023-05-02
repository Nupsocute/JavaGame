package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Boot extends Entity {

	public Obj_Boot(GamePanel gamePanel) {
		super(gamePanel);
		name = "Boot";
		down1 = setup("/objects/boots", gamePanel.tileSize, gamePanel.tileSize);
		collision = true ;
	}

}
