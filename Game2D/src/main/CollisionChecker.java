package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gamePanel;
	
	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void checkTile(Entity entity) {
		int corner1, corner2, corner3;
		
		int hitboxX, hitboxY;
		hitboxX = entity.hitbox.x + entity.worldX;
		hitboxY = entity.hitbox.y + entity.worldY;
		
		switch(entity.direction) { 
		case "up":
			hitboxY -= entity.speed;
			corner1 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			corner2 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			if(gamePanel.tileManager.tile[corner1].collision == true || gamePanel.tileManager.tile[corner2].collision == true) entity.collisionOn = true;
			break;
		case "down":
			hitboxY += entity.speed;
			corner1 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			corner2 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			if(gamePanel.tileManager.tile[corner1].collision == true || gamePanel.tileManager.tile[corner2].collision == true) entity.collisionOn = true;
			break;
		case "left":
			hitboxX -= entity.speed;
			corner1 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			corner2 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			if(gamePanel.tileManager.tile[corner1].collision == true || gamePanel.tileManager.tile[corner2].collision == true) entity.collisionOn = true;
			break;
		case "right":
			hitboxX += entity.speed;
			corner1 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			corner2 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			if(gamePanel.tileManager.tile[corner1].collision == true || gamePanel.tileManager.tile[corner2].collision == true) entity.collisionOn = true;
			break;
		case "up_left":
			hitboxX -= entity.speed-1;
			hitboxY -= entity.speed-1;
			corner1 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			corner2 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			corner3 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			if(gamePanel.tileManager.tile[corner1].collision == true || gamePanel.tileManager.tile[corner2].collision == true || gamePanel.tileManager.tile[corner3].collision == true) entity.collisionOn = true;
			break;
		case "up_right":
			hitboxX += entity.speed-1;
			hitboxY -= entity.speed-1;
			corner1 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			corner2 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			corner3 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			if(gamePanel.tileManager.tile[corner1].collision == true || gamePanel.tileManager.tile[corner2].collision == true || gamePanel.tileManager.tile[corner3].collision == true) entity.collisionOn = true;
			break;
		case "down_left":
			hitboxX -= entity.speed-1;
			hitboxY += entity.speed-1;
			corner1 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			corner2 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			corner3 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			if(gamePanel.tileManager.tile[corner1].collision == true || gamePanel.tileManager.tile[corner2].collision == true || gamePanel.tileManager.tile[corner3].collision == true) entity.collisionOn = true;
			break;
		case "down_right":
			hitboxX += entity.speed-1;
			hitboxY += entity.speed-1;
			corner1 = gamePanel.tileManager.mapTileNum[hitboxX/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			corner2 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][(hitboxY + entity.hitbox.height)/gamePanel.tileSize];
			corner3 = gamePanel.tileManager.mapTileNum[(hitboxX + entity.hitbox.width)/gamePanel.tileSize][hitboxY/gamePanel.tileSize];
			if(gamePanel.tileManager.tile[corner1].collision == true || gamePanel.tileManager.tile[corner2].collision == true || gamePanel.tileManager.tile[corner3].collision == true) entity.collisionOn = true;
			break;
		} 
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		for(int i=0; i<gamePanel.obj.length; i++) {
			if(gamePanel.obj[i]!=null) {
				entity.hitbox.x += entity.worldX;
				entity.hitbox.y += entity.worldY;
				gamePanel.obj[i].hitbox.x += gamePanel.obj[i].worldX;
				gamePanel.obj[i].hitbox.y += gamePanel.obj[i].worldY;	
				
				switch(entity.direction) {
				case "up":
					entity.hitbox.y -= entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) entity.collisionOn = true;
						if(player == true) index=i;
					}	
					break;
				case "down":
					entity.hitbox.y += entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) entity.collisionOn = true;
						if(player == true) index=i;
					}
					break;
				case "left":
					entity.hitbox.x -= entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) entity.collisionOn = true;
						if(player == true) index=i;
					}
					break;
				case "right":
					entity.hitbox.x += entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) entity.collisionOn = true;
						if(player == true) index=i;
					}
					break;		
				case "up_left":
					entity.hitbox.y -= entity.speed;
					entity.hitbox.x -= entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) entity.collisionOn = true;
						if(player == true) index=i;
					}
					break;
				case "up_right":
					entity.hitbox.y -= entity.speed;
					entity.hitbox.x += entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) entity.collisionOn = true;
						if(player == true) index=i;
					}
					break;
				case "down_left":
					entity.hitbox.y += entity.speed;
					entity.hitbox.x -= entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) entity.collisionOn = true;
						if(player == true) index=i;
					}
					break;
				case "down_right":
					entity.hitbox.y += entity.speed;
					entity.hitbox.x += entity.speed;
					if(entity.hitbox.intersects(gamePanel.obj[i].hitbox)) {
						if(gamePanel.obj[i].collision == true) entity.collisionOn = true;
						if(player == true) index=i;
					}
					break;
				}
				
				entity.hitbox.x = entity.hitboxDefaultX;
				entity.hitbox.y = entity.hitboxDefaultY;
				gamePanel.obj[i].hitbox.x = gamePanel.obj[i].hitboxDefaultX;
				gamePanel.obj[i].hitbox.y = gamePanel.obj[i].hitboxDefaultY;
			}
			
		}
		return index;	
	}
	
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;
		for(int i=0; i<target.length; i++) {
			if(target[i]!=null) {
				entity.hitbox.x += entity.worldX;
				entity.hitbox.y += entity.worldY;
				target[i].hitbox.x += target[i].worldX;
				target[i].hitbox.y += target[i].worldY;	
				
				switch(entity.direction) {
				case "up":
					entity.hitbox.y -= entity.speed;
					if(entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
						index=i;
					}	
					break;
				case "down":
					entity.hitbox.y += entity.speed;
					if(entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
						index=i;
					}
					break;
				case "left":
					entity.hitbox.x -= entity.speed;
					if(entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
						index=i;
					}
					break;
				case "right":
					entity.hitbox.x += entity.speed;
					if(entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
						index=i;
					}
					break;		
				case "up_left":
					entity.hitbox.y -= entity.speed;
					entity.hitbox.x -= entity.speed;
					if(entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
						index=i;
					}
					break;
				case "up_right":
					entity.hitbox.y -= entity.speed;
					entity.hitbox.x += entity.speed;
					if(entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
						index=i;
					}
					break;
				case "down_left":
					entity.hitbox.y += entity.speed;
					entity.hitbox.x -= entity.speed;
					if(entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
						index=i;
					}
					break;
				case "down_right":
					entity.hitbox.y += entity.speed;
					entity.hitbox.x += entity.speed;
					if(entity.hitbox.intersects(target[i].hitbox)) {
						entity.collisionOn = true;
						index=i;
					}
					break;
				}
				entity.hitbox.x = entity.hitboxDefaultX;
				entity.hitbox.y = entity.hitboxDefaultY;
				target[i].hitbox.x = target[i].hitboxDefaultX;
				target[i].hitbox.y = target[i].hitboxDefaultY;
			}
			
		}
		return index;
	}
	
	public boolean checkPlayer(Entity entity) {
		boolean contactPlayer = false;
		entity.hitbox.x += entity.worldX;
		entity.hitbox.y += entity.worldY;
		gamePanel.player.hitbox.x += gamePanel.player.worldX;
		gamePanel.player.hitbox.y += gamePanel.player.worldY;	
		
		switch(entity.direction) {
		case "up":
			entity.hitbox.y -= entity.speed;	
			break;
		case "down":
			entity.hitbox.y += entity.speed;
			break;
		case "left":
			entity.hitbox.x -= entity.speed;
			break;
		case "right":
			entity.hitbox.x += entity.speed;
			break;		
		case "up_left":
			entity.hitbox.y -= entity.speed;
			entity.hitbox.x -= entity.speed;
			break;
		case "up_right":
			entity.hitbox.y -= entity.speed;
			entity.hitbox.x += entity.speed;
			break;
		case "down_left":
			entity.hitbox.y += entity.speed;
			entity.hitbox.x -= entity.speed;
			break;
		case "down_right":
			entity.hitbox.y += entity.speed;
			entity.hitbox.x += entity.speed;
			break;
		}
		
		if(entity.hitbox.intersects(gamePanel.player.hitbox)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		entity.hitbox.x = entity.hitboxDefaultX;
		entity.hitbox.y = entity.hitboxDefaultY;
		gamePanel.player.hitbox.x = gamePanel.player.hitboxDefaultX;
		gamePanel.player.hitbox.y = gamePanel.player.hitboxDefaultY;
		
		return contactPlayer;
	}
}
