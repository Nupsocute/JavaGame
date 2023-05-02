package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.GamePanel;

public class ConnectDataBase {
	GamePanel gamePanel;
	Connection conn;
	
	public ConnectDataBase(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		conn = null;
		try {
			String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=Save;user=sa;password=sa;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
			conn = DriverManager.getConnection(dbURL);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void save() {
		try {
			java.sql.Statement statement;
			statement = conn.createStatement();
			String sql = "INSERT INTO Luu values(";
			sql = sql + gamePanel.player.level + "," ;
			sql = sql + gamePanel.player.maxLife + "," ;
			sql = sql + gamePanel.player.life + "," ;
			sql = sql + gamePanel.player.maxMana + "," ;
			sql = sql + gamePanel.player.mana + "," ;
			sql = sql + gamePanel.player.strength + "," ;
			sql = sql + gamePanel.player.atk + "," ;
			sql = sql + gamePanel.player.dex + "," ;
			sql = sql + gamePanel.player.exp + "," ;
			sql = sql + gamePanel.player.nextLevelExp + "," ;
			sql = sql + gamePanel.player.coin + "," ;
			sql = sql + gamePanel.player.def + "," ;
			sql = sql + gamePanel.player.hasKey + "," ;
			sql = sql + gamePanel.player.worldX + "," ;
			sql = sql + gamePanel.player.worldY + ")" ;
			
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void load() {
		try {
			java.sql.Statement statement;
			statement = conn.createStatement();
			String sql = "SELECT * FROM Luu";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int level = rs.getInt("level");
				int maxLife = rs.getInt("maxLife");
				int life = rs.getInt("life");
				int maxMana = rs.getInt("maxMana");
				int mana = rs.getInt("mana");
				int strength = rs.getInt("strength");
				int atk = rs.getInt("atk");
				int dex = rs.getInt("dex");
				int exp = rs.getInt("expr");
				int nextLevelExp = rs.getInt("nextLevelExp");
				int coin = rs.getInt("coin");
				int def = rs.getInt("def");
				int hasKey = rs.getInt("hasKey");
				int worldX = rs.getInt("worldX");
				int worldY = rs.getInt("worldY");
				
				System.out.println(life +" "+level);
				
				gamePanel.player.level = level;
				gamePanel.player.maxLife = maxLife;
				gamePanel.player.life = life;
				gamePanel.player.maxMana = maxMana;
				gamePanel.player.mana = mana;
				gamePanel.player.strength = strength;
				gamePanel.player.atk = atk;
				gamePanel.player.dex = dex;
				gamePanel.player.exp = exp;
				gamePanel.player.nextLevelExp = nextLevelExp;
				gamePanel.player.coin = coin;
				gamePanel.player.def = def;
				gamePanel.player.hasKey = hasKey;
				gamePanel.player.worldX = worldX ;
				gamePanel.player.worldY = worldY ;
				
				
				String del = "DELETE Luu";
				statement.executeUpdate(del);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
