package Java_Shooting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

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
		while(loop) {
			if((System.currentTimeMillis() - fpsTime) > 1000 ) {
				fpsTime = System.currentTimeMillis();
				FPS = FPSCount;
				FPSCount = 0;
				System.out.println(FPS);
			}
			FPSCount++;
			startTime = System.currentTimeMillis();
			
			gra.setColor(Color.WHITE);
			gra.fillRect(0,  0,  500,  500);
			
			gra.setColor(Color.BLACK);
			gra.fillRect(100,  100,  100, 100);
			
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
