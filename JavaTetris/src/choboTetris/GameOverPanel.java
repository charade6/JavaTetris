package choboTetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.chobocho.player.IPlayer;
import com.chobocho.player.IPlayerAction;
import com.chobocho.player.IPlayerDraw;
import com.chobocho.player.Player;
import com.chobocho.player.PlayerOneAction;
import com.chobocho.player.PlayerOneDraw;
import com.chobocho.tetris.ITetris;
import com.chobocho.tetris.Tetris;

public class GameOverPanel extends JPanel {
	Color c1 = new Color(179,217,255);
    Color c2 = new Color(209,195,252);
    Color bt = new Color(235,235,235);
    JLabel bt_home = new JLabel();
    static int lastscore;
    static int[] intArray = new int[10];
    
    public static void getScore(int score) {
		lastscore = score;
	}
	public GameOverPanel() {
        
		add(bt_home);
		bt_home.setBounds(65, 400, 300, 80);
		MyMouseAdapter adapter = new MyMouseAdapter();
		bt_home.addMouseListener(adapter);
		
		setLayout(null);
		setSize(450,620);
		
		File f = new File("src/SaveScore.txt");
		if (f.exists() == false) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/SaveScore.txt"));
                out.writeObject(intArray);
                out.close();
                JOptionPane.showMessageDialog(null, "SaveScore.txt is missing!\nrecreate SaveScore file.");
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			loadScore();
		}
		
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0,0,c1,0,620,c2);
		g2.setPaint(gp);
		g2.fillRect(0, 0, 450, 620);
		
		g2.setColor(Color.black);
		g2.setFont(new Font("Gothic", Font.BOLD, 90));
		g2.drawString("GAME", 80, 160);
		g2.drawString("OVER", 87, 235);
		
		g2.setFont(new Font("Century Gothic", Font.BOLD, 48));
		g2.drawString("SCORE", 135, 315);
		
		g2.setFont(new Font("Century Gothic", Font.BOLD, 32));
		g2.drawString(String.valueOf(lastscore), 150, 355);
		
		g2.setColor(bt);
        g2.fillRoundRect(65, 400, 300, 80, 10, 10);
        g2.setColor(Color.black);
        g2.drawRoundRect(65, 400, 300, 80, 10, 10);
        g2.drawString("HOME", 165, 450);
	}
	
	class MyMouseAdapter extends MouseAdapter{
		public void mousePressed (MouseEvent e) {
			if (e.getComponent().equals(bt_home)) {
				TetrisMain.movePage(1);
				TetrisBoardGui.setState(1);
				saveScore();
			}
		}
	}
	public void loadScore() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/SaveScore.txt"));
			int[] intArray = (int[]) ois.readObject();
			ois.close();
			this.intArray = intArray;
			Arrays.sort(intArray);
			System.out.println("Á¡¼ö ºÒ·¯¿È");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void saveScore() {
		boolean overLap = false;
		
		for(int i = 0; i < 10; i++) {
			if(intArray[i] == lastscore) {
				overLap = true;
			}
		}
		if(intArray[0] < lastscore && overLap == false) {
			try {
				intArray[0] = lastscore;
				Arrays.sort(intArray);
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/SaveScore.txt"));
	            out.writeObject(intArray);
	            out.close();
	            System.out.println("Á¡¼ö ÀúÀåµÊ");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}
	public static int[] return_Array() {
		int[] ret_Array = intArray;
		return ret_Array;
	}
}
