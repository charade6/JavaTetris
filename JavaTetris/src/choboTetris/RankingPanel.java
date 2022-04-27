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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RankingPanel extends JPanel {
	public int first = 0;
	private int[] intArray;
	JLabel bt_back = new JLabel();
    
    Color c1 = new Color(179,217,255);
    Color c2 = new Color(209,195,252);
    ImageIcon backImg = new ImageIcon(this.getClass().getResource("../img/backbtn.png"));
    Image img = backImg.getImage();
    
    
	public RankingPanel() {
		
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
		g2.drawString("RANKING", 90, 60);
		
		
		g2.setColor(Color.white);
		g2.fillRoundRect(20, 80, 390, 480, 10, 10);
		g2.setColor(Color.black);
		g2.drawRoundRect(20, 80, 390, 480, 10, 10);
		
		g2.setFont(new Font("Century Gothic", Font.BOLD, 28));
		g2.drawString("1st", 40, 127);
		g2.drawString("2nd", 40, 172);
		g2.drawString("3rd", 40, 217);
		g2.drawString("4th", 40, 262);
		g2.drawString("5th", 40, 307);
		g2.drawString("6th", 40, 352);
		g2.drawString("7th", 40, 397);
		g2.drawString("8th", 40, 442);
		g2.drawString("9th", 40, 487);
		g2.drawString("10th", 40, 532);
		
		for(int i = 9; i >= 0; i--) {
			g2.drawString(String.valueOf(intArray[i] == 0 ? "NO DATA" : intArray[i]), 240, 532 - 45*i);
		}
		
	}
	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed (MouseEvent e) {
			TetrisMain.movePage(1);
		}
	}
	public void load_list() {
		intArray = GameOverPanel.return_Array();
	}
}
