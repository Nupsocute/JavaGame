package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Gate extends Entity {
	public Obj_Gate(GamePanel gamePanel) {
		super(gamePanel);
		name = "Gate";
		down1 = setup("/objects/out_gate", gamePanel.tileSize, gamePanel.tileSize);
		collision = true;
	}
}
