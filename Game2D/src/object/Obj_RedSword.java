package object;

import entity.Entity;
import main.GamePanel;

public class Obj_RedSword extends Entity {

	public Obj_RedSword(GamePanel gamePanel) {
		super(gamePanel);
		name = "RedSword";
		down1 = setup("/objects/red_sword", 30, 21*3);
		atk = 1;
	}

	
}
