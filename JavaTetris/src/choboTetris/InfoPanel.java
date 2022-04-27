package choboTetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
    JPanel bt_back = new JPanel();
    
    Color c1 = new Color(179,217,255);
    Color c2 = new Color(209,195,252);
    ImageIcon backImg = new ImageIcon(this.getClass().getResource("../img/backbtn.png"));
    Image img = backImg.getImage();
	public InfoPanel() {
		
		
		bt_back.setBounds(10, 10, 50, 50);
		add(bt_back);
		
		MyMouseAdapter adapter = new MyMouseAdapter();
		bt_back.addMouseListener(adapter);
		
		setLayout(null);
		setSize(450,620);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0,0,c1,0,620,c2);
		g2.setPaint(gp);
		g2.fillRect(0, 0, 450, 620);
		
		g2.drawImage(img,20,20,40,40,this);
		
		g2.setColor(Color.black);
		g2.setFont(new Font("Century Gothic", Font.BOLD, 60));
		g2.drawString("INFO", 150, 60);
		
		
		g2.setColor(Color.white);
		g2.fillRoundRect(20, 80, 390, 480, 10, 10);
		g2.setColor(Color.black);
		g2.drawRoundRect(20, 80, 390, 480, 10, 10);
		
		// command left
		g2.setFont(new Font("Calibri", Font.BOLD, 46));
		g2.drawString("↑", 30, 130);
		g2.drawString("↓", 30, 195);
		g2.drawString("←", 30, 260);
		g2.drawString("→", 30, 325);
		g2.setFont(new Font("Century Gothic", Font.BOLD, 40));
		g2.drawString("SPACE", 30, 390);
		g2.drawString("P", 30, 455);
		g2.drawString("H", 30, 520);
		
		// command right
		
		g2.setFont(new Font("Century Gothic", Font.PLAIN, 32));
		g2.drawString("ROTATE", 290, 127);
		g2.drawString("DOWN", 295, 192);
		g2.drawString("LEFT", 340, 257);
		g2.drawString("RIGHT", 310, 322);
		g2.drawString("HARD DROP", 215, 387);
		g2.drawString("PAUSE / RESUME", 152, 452);
		g2.drawString("SHADOW ON / OFF", 107, 517);
	}
	
	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed (MouseEvent e) {
			TetrisMain.movePage(1);
		}
	}
}
