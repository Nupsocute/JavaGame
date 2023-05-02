package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.ScaleImage;

public class TileManager {
	GamePanel gamePanel;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		tile = new Tile[30];
		mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
		getTileImage();
		loadMap("/map/worldMap01.txt");
	}

	public void getTileImage() {
		setup(0,"grass",false ,gamePanel.tileSize*2, gamePanel.tileSize*2);
		setup(1,"wall",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(2,"bush",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(3,"sand",false ,gamePanel.tileSize, gamePanel.tileSize);
		setup(4,"water",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(5,"dirt",false ,gamePanel.tileSize, gamePanel.tileSize);
		setup(6,"moss",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(7,"dead_bush",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(8,"flower",true ,gamePanel.tileSize*2, gamePanel.tileSize*2);
		setup(9,"wallland",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(10,"snow_wall",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(11,"snow",false ,gamePanel.tileSize, gamePanel.tileSize);
		setup(12,"snow_man",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(13,"earthmonster",false ,gamePanel.tileSize, gamePanel.tileSize);
		setup(14,"wall_up_left",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(15,"wall_up_right",true ,gamePanel.tileSize, gamePanel.tileSize);
		setup(16,"left_col",true, gamePanel.tileSize, gamePanel.tileSize);
		setup(17,"right_col",true, gamePanel.tileSize, gamePanel.tileSize);
		
	}
	
	public void setup(int i, String fileName, boolean collision, int width, int height) {
		ScaleImage sImage = new ScaleImage();
		try {
			tile[i] = new Tile();
			tile[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ fileName + ".png"));
			tile[i].image = sImage.scaleImage(tile[i].image, width, height);
			tile[i].collision = collision;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadMap(String patch) {
		InputStream is = getClass().getResourceAsStream(patch);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		int col = 0, row = 0;
		try {		
			while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {	
				String line = br.readLine();
				while(col < gamePanel.maxWorldCol) {
					String numbers[] = line.split("\t");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gamePanel.maxWorldCol) {
					col = 0;
					row++;
				}
			}
		} catch(Exception e) {
		
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0, worldRow = 0;
		while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gamePanel.tileSize;
			int worldY = worldRow * gamePanel.tileSize;
			int screenX = worldX + gamePanel.player.screenX - gamePanel.player.worldX;
			int screenY = worldY + gamePanel.player.screenY - gamePanel.player.worldY; 
			g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
			worldCol++;
			if(worldCol == gamePanel.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
