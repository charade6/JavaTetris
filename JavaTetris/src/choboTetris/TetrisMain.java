package choboTetris;

import javax.swing.*;

import com.chobocho.tetris.Tetris;

public class TetrisMain extends JFrame {
//    JLabel score;
	private static MainPanel mainPage;
	private static GameOverPanel gameOverPage;
	private static InfoPanel infoPage;
	private static RankingPanel rankPage;
	private static TetrisBoardGui tetrisBoardGui;
	
    public TetrisMain() {
    	infoPage = new InfoPanel();
        add(infoPage);
        
        rankPage = new RankingPanel();
        add(rankPage);
        
    	gameOverPage = new GameOverPanel();
        add(gameOverPage);
        
        
    	mainPage = new MainPanel();
        add(mainPage);
        
        
        tetrisBoardGui  = new TetrisBoardGui(this);
        tetrisBoardGui.start(); 
        add(tetrisBoardGui);
        
        mainPage.setVisible(true);
    	gameOverPage.setVisible(false);
    	infoPage.setVisible(false);
    	rankPage.setVisible(false);
        setSize(450, 620);
        setTitle("TETRIS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void movePage(int index) {
    	infoPage.setVisible(false);
    	rankPage.setVisible(false);
    	mainPage.setVisible(false);
    	gameOverPage.setVisible(false);
    	switch (index) {
    		case 1:
    			mainPage.setVisible(true);
    			break;
    		case 2:
    			tetrisBoardGui.setVisible(true);
    			tetrisBoardGui.add(tetrisBoardGui.bt_pause);
    			break;
    		case 3:
    			gameOverPage.setVisible(true);
    			tetrisBoardGui.remove(tetrisBoardGui.bt_pause);
    			break;
    		case 4:
    			infoPage.setVisible(true);
    			break;
    		case 5:
    			rankPage.setVisible(true);
    			gameOverPage.loadScore();
    			rankPage.load_list();
    			break;
    	}
    }
    
    public static void main(String[] args) {        
        TetrisMain tetrisGui = new TetrisMain();
        tetrisGui.setLocationRelativeTo(null);
        tetrisGui.setResizable(false);
        tetrisGui.setVisible(true);
        movePage(1);
    }
}