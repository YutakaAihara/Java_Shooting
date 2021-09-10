package Java_Shooting;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Shooting {
	public static ShootingFrame shootingFrame;
	public static boolean loop;
	
	public static void main(String[] args) {
		shootingFrame = new ShootingFrame();
		loop = true;
		
		Graphics gra = shootingFrame.panel.image.getGraphics();
		
		//FPS
		long startTime;
		long fpsTime = 0;
		int fps = 30;
		int FPS = 0;
		int FPSCount = 0;
		
		EnumShootingScreen screen = EnumShootingScreen.START;
		
		// GAME
		int playerX = 0, playerY = 0;
		ArrayList<Bullet> bullets = new ArrayList<>();
		ArrayList<Enemy> enemies = new ArrayList<>();
		
		
		while(loop) {
			if((System.currentTimeMillis() - fpsTime) > 1000 ) {
				fpsTime = System.currentTimeMillis();
				FPS = FPSCount;
				FPSCount = 0;
			}
			FPSCount++;
			startTime = System.currentTimeMillis();
			
			gra.setColor(Color.WHITE);
			gra.fillRect(0,  0,  500,  500);
			
			switch (screen) {
			case START:
				gra.setColor(Color.BLACK);
				Font font = new Font("SansSerif", Font.PLAIN, 50);
				gra.setFont(font);
				FontMetrics metrics = gra.getFontMetrics(font);
				gra.drawString("Shooting", 250 - (metrics.stringWidth("Shooting") / 2), 100);
				font = new Font("SansSerif", Font.PLAIN, 20);
				gra.setFont(font);
				metrics = gra.getFontMetrics(font);
				gra.drawString("Please SPASE to Start", 250 - (metrics.stringWidth("Please SPASE to Start") / 2), 180);
				if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
					screen = EnumShootingScreen.GAME;
					bullets = new ArrayList<>();
					enemies = new ArrayList<>();
					playerX = 235;
					playerY = 420;
				}
				break;
			case GAME:
				gra.setColor(Color.BLUE);
				gra.fillRect(playerX + 10, playerY, 10, 10);
				gra.fillRect(playerX, playerY + 10, 30, 10);
				
				for (int i = 0; i < bullets.size(); i++) {
					Bullet bullet = bullets.get(i);
					gra.setColor(Color.BLUE);
					gra.fillRect(bullet.x, bullet.y, 5, 5);
					bullet.y -= 10;
					if(bullet.y < 0) {
						bullets.remove(i);
						i--;
					}
				}
				if(Keyboard.isKeyPressed(KeyEvent.VK_LEFT) && playerX > 0) playerX -= 5;
				if(Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && playerX < 460) playerX += 5;
				if(Keyboard.isKeyPressed(KeyEvent.VK_UP) && playerY > 30) playerY -= 5;
				if(Keyboard.isKeyPressed(KeyEvent.VK_DOWN) && playerY < 440) playerY += 5;
				
				
				if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
					bullets.add(new Bullet(playerX + 12, playerY));
				}
				break;
			case GAME_OVER:
				break;
			}
			
			
			gra.setColor(Color.BLACK);
			gra.setFont(new Font("SanSerif", Font.PLAIN, 10));
			gra.drawString(FPS + "FPS", 0, 460);
			
			shootingFrame.panel.draw();
			
			
			try {
				long runTime = System.currentTimeMillis() - startTime;
				if(runTime<(1000/fps)) {
					Thread.sleep((1000 / fps) - (runTime));				
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
