import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

import java.util.*;
import java.awt.Event.*;
import java.applet.*;
import java.awt.event.*;

public class  Pong extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public Color color = new Color(0, 0, 0);
	public static final long DELAY = 3;
	public static int x = 100, y = 100;
	public static int y2 = 0;
	public static int xA = 1270, yA = 0;
	public static String direction = "RU";
	public Rectangle r = new Rectangle(x, y, 20, 20);
	public static int score = 0;
	public static long time = 5L;
	
	
	public Pong (){
		Thread animationThread = new Thread(new Runnable() {
	    public void run() {
	    	while(true){
	    		int yrange2 = y2 + 100;
	    		
	    		switch (score){
	    		case 2:
	    			time = 4L;
	    			break;
	    		case 4:
	    			time = 3L;
	    			break;
	    		case 6:
	    			time = (int)(Math.random() *3 + 2);
	    			break;
	    		case 8:
	    			time = 5;
	    			break;
	    		case 9:
	    			time = 2L;
	    			break;
	    		default:
	    			break;
	    		
	    		}
	    		
	    		if (score == 10){
	    			System.out.println("YOU WIN!");
	    			System.exit(0);
	    		}else if (Pong.x == 0){
	    			System.out.println("YOU LOSE!");
	    			System.exit(0);
	    		}else if(Pong.y == 0){
	    			switch (Pong.direction){
	    			case "RU":
	    				Pong.direction = "RD";
	    				break;
	    			case "LU":
	    				Pong.direction = "LD";
	    			}
	    		}else if (Pong.y == 360){
	    			switch (Pong.direction){
	    			case "RD":
	    				Pong.direction = "RU";
	    				break;
	    			case "LD":
	    				Pong.direction = "LU";
	    				break;
	    			}
	    			
	    		}else if (Pong.x == 1230){
	    			switch (Pong.direction){
	    			case "RU":
	    				Pong.direction = "LU";
	    				break;
	    			case "RD":
	    				Pong.direction = "LD";
	    			}   			
	    		}else if (x < 27 && (y2 <=  y && y <= yrange2)){
	    			Pong.score++;
	    			switch (Pong.direction){
	    			case "LU":
	    				Pong.direction = "RU";
	    				break;
	    			case "LD":
	    				Pong.direction = "RD";
	    				break;
	    			}
	    		}
	    		switch (Pong.direction){
	    		case "RU":
	    			Pong.x++;
	    			Pong.y--;
	    			break;
	    		case "RD":
	    			Pong.y++;
	    			Pong.x++;
	    			break;
	    		case "LU":
	    			Pong.x--;
	    			Pong.y--;
	    			break;
	    		case "LD":
	    			Pong.x--;
	    			Pong.y++;
	    		}
	            repaint();
	    repaint();
	    try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    
	    	}
		}
		});
		animationThread.start(); 
	
	

	}
	
		public void changeDirection(){
		
		}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gg = (Graphics2D) g;
		gg.setBackground(color);
	    gg.setColor(Color.WHITE);
	    gg.clearRect(0, 0, 1280, 720);
	    gg.fillOval(Pong.x, Pong.y, 13, 13);
	    gg.fillRect(20, y2, 7, 100);
	    int fontSize = 100;
	    gg.setFont(new Font("Courier", Font.PLAIN, fontSize));
	    gg.drawString("Pong", 450, 100);
	    gg.setFont(new Font("courier", Font.BOLD, 30));
	    gg.drawString(("Score: " + score), 500, 200);
	    
	    int ya = y;
	    if(ya<=0){
			ya = 0;
		}else if (ya >= 320){
			ya = 320;
		}
	    gg.fillRect(1230, (ya - 50), 7, 100);
	    PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		y2 = (int) b.getY();
		y2 += -50;
		if(y2<=0){
			y2 = 0;
		}else if (y2 >= 260){
			y2 = 260;
		}
		
		
	}
	
	
        
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pong");
		frame.add(new Pong());
		frame.setSize(1280, 400);
		frame.requestFocus();
		frame.setVisible(true);
        frame.setContentPane(new Pong());
        int width = frame.getContentPane().getSize().width;
        int height = frame.getContentPane().getSize().height;
	}
	
}

