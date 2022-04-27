package com.chobocho.player;

import java.awt.event.KeyEvent;

import choboTetris.GameOverPanel;

public class PlayerOneAction implements IPlayerAction {
   IPlayer player = null;

    public void onKeyEvent(int keycode) {
        System.out.println("Tetris (d) Player1 Press key : " + keycode);

        if (player == null) {
            return;
        }

        if (player.isIdleState()) {
            if (keycode == 10){
                player.play();
            }
            return;
        }

        if (player.isGameOverState()) {
            if (keycode == 10){
            	GameOverPanel.saveScore();
                player.init();
            }
            return;
        }

        if (player.isPauseState()) {
            if (keycode =='p' || keycode == 'P'){
                player.resume();
            }
            return;
        }

        if (player.isPlayState() == false) {
        	return;
        }

        switch (keycode) {
            case KeyEvent.VK_LEFT:
                player.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                player.moveRight();
                break;
            case KeyEvent.VK_DOWN:
                player.moveDown();
                break;
            case KeyEvent.VK_UP:
                player.rotate();
                break;
            case KeyEvent.VK_SPACE:
                player.moveBottom();
                break;
            case 'H':
            case 'h':
                if (player.isEnableShadow()) {
                    player.disableShadow();
                } else {
                    player.enableShadow();
                }
                break;
            case 'p':
            case'P':
                player.pause();
                break;
        }
    }

    public void setPlayer(IPlayer player) {
        this.player = player;
    }
}
