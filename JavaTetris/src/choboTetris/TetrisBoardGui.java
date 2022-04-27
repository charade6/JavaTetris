package choboTetris;

import com.chobocho.player.*;
import com.chobocho.tetris.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TetrisBoardGui extends JPanel implements ITetrisObserver {
    final int BOARD_WIDTH = 14;
    final int BOARD_HEIGHT = 20;

    static IPlayer playerOne;
    IPlayerDraw playerOneDraw;
    IPlayerAction playerOneAction;
    
    JLabel bt_pause = new JLabel();
    JLabel bt_resume = new JLabel();
    JLabel bt_home = new JLabel();

    Color c1 = new Color(179,217,255);
    Color c2 = new Color(209,195,252);
    Color c3 = new Color(0,0,0,60);
    Color bt = new Color(235,235,235);
    
    MyMouseAdapter adapter = new MyMouseAdapter();

    private Image screenBuffer = null;
    private Graphics graphicsBuffer = null;


    public TetrisBoardGui(TetrisMain parent) {
        playerOneDraw = new PlayerOneDraw();
        playerOneAction = new PlayerOneAction();
        playerOne = new Player(this, playerOneDraw, playerOneAction);
        
        bt_pause.setBounds(375,10,50,50);
        bt_pause.addMouseListener(adapter);
        
        
        bt_resume.setBounds(85,235, 260, 80);
        bt_resume.addMouseListener(adapter);
        
        bt_home.setBounds(85,340, 260, 80);
        bt_home.addMouseListener(adapter);

        setLayout(null);
        setFocusable(true);
        addKeyListener(new TetrisKeyAdapter());        
    }

    public void update() {
        System.out.println("Tetris (d) View.update()");
        repaint();
    }
    private int blockWidth() { return (int) getSize().getWidth() / BOARD_WIDTH; }
    private int blockHeight() { return (int) getSize().getHeight() / BOARD_HEIGHT; }


    public void start()
    {
        playerOne.init();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Dimension size = getSize();
        
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, c1, 0, 620, c2);
        
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();

        if (screenBuffer == null) {
            screenBuffer = createImage(width, height);
        }

        graphicsBuffer = screenBuffer.getGraphics();
        Graphics2D g3 = (Graphics2D) graphicsBuffer;
        g3.setPaint(gp);
        graphicsBuffer.fillRect(0, 0, width, height);
        
        int boardY = (int) size.getHeight() - BOARD_HEIGHT * blockHeight();
        int boardX = (int) (size.getWidth() - BOARD_WIDTH * blockWidth())/2;
        playerOne.onDraw(graphicsBuffer, boardX, boardY, blockWidth(), blockHeight());
        g.drawImage(screenBuffer, 0, 0, null);
        
        
        // pause btn
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(3));
        g2.fillRoundRect(382, 17, 13, 35, 5, 5);
        g2.fillRoundRect(402, 17, 13, 35, 5, 5);
        
        // pause pop-up
        if (playerOne.isPauseState()) {
        	g2.setColor(c3);
            g2.fillRect(0, 0, 450, 620);
            g2.setColor(Color.white);
            g2.fillRoundRect(50, 120, 330, 360, 10, 10);
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(50, 120, 330, 360, 10, 10);
            g2.setFont(new Font("Century Gothic", Font.BOLD, 50));
            g2.drawString("PAUSE",140,200);
            
            g2.setColor(bt);
            g2.fillRoundRect(85, 235, 260, 80, 10, 10);
            g2.fillRoundRect(85, 340, 260, 80, 10, 10);
            
            g2.setColor(Color.black);
            g2.drawRoundRect(85, 235, 260, 80, 10, 10);
            g2.drawRoundRect(85, 340, 260, 80, 10, 10);
            
            g2.setFont(new Font("Century Gothic", Font.BOLD, 32));
            g2.drawString("RESUME", 155, 285);
            g2.drawString("HOME", 170, 390);
            
            add(bt_resume);
			add(bt_home);
			remove(bt_pause); 
		} else if (playerOne.isPauseState()==false && playerOne.isPlayState()){
			remove(bt_resume);
			remove(bt_home);
			add(bt_pause);
		}
        

        screenBuffer = null;
    }

    class TetrisKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            playerOne.onPressKey(keycode);
        }
    }
    class MyMouseAdapter extends MouseAdapter{
    	public void mousePressed (MouseEvent e) {
			if (e.getComponent().equals(bt_pause)) {
				setState(2);
			} else if (e.getComponent().equals(bt_resume)) {
				setState(3);
			} else if (e.getComponent().equals(bt_home)) {
				setState(1);
				TetrisMain.movePage(1);
				remove(bt_pause);
				remove(bt_resume);
				remove(bt_home);
			} 
		}
    }
    public static void setState(int index) {
    	switch(index) {
    		case 1: 
    			playerOne.init();
    			break;
    		case 2: 
    			playerOne.pause();
    			break;
    		case 3:
    			playerOne.resume();
    			break;
    	}
    }
}