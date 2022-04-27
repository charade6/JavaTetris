package choboTetris;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MainPanel extends JPanel{
	JLabel imageLabel = new JLabel();
	JLabel bt_info = new JLabel();
	JLabel bt_rank = new JLabel();
	ImageIcon ii = new ImageIcon(this.getClass().getResource("../img/main.gif"));
	ImageIcon infoImg = new ImageIcon(this.getClass().getResource("../img/infobtn.png"));
	ImageIcon rankImg = new ImageIcon(this.getClass().getResource("../img/rankbtn.png"));

	
	
	
	public MainPanel() {
		bt_info.setBounds(10,10,50,50);
		bt_info.setIcon(infoImg);
		add(bt_info);	
		
		bt_rank.setBounds(375,10,50,50);
		bt_rank.setIcon(rankImg);
		add(bt_rank);
		
		imageLabel.setIcon(ii);
		imageLabel.setBounds(-10, 0, 450, 590);
		imageLabel.setBackground(Color.black);
		add(imageLabel);
		
		MyMouseAdapter adapter = new MyMouseAdapter();
		bt_info.addMouseListener(adapter);
		bt_rank.addMouseListener(adapter);
		
		setLayout(null);
		setSize(450,620);
		
	}
	
	class MyMouseAdapter extends MouseAdapter{
		public void mousePressed (MouseEvent e) {
			if(e.getComponent().equals(bt_info)) {
				TetrisMain.movePage(4);
			} else if(e.getComponent().equals(bt_rank)) {
				TetrisMain.movePage(5);
			}
		}
	}
}

