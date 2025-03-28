package org.academiadecodigo.vimdiesels.GameObject;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.vimdiesels.ColisionDetector;
import org.academiadecodigo.vimdiesels.Game;
import org.academiadecodigo.vimdiesels.gfx.SimpleGFX.SimpleGfxGridPosition;
import org.academiadecodigo.vimdiesels.grid.Grid;
import org.academiadecodigo.vimdiesels.grid.GridDirection;
import org.academiadecodigo.vimdiesels.menu.GameOverHandler;

import java.util.ArrayList;

public class PlayableCharacter extends GameObject implements KeyboardHandler {

    private String name;
    private int health;
    private int speed;
    private boolean dead;
    private int score;
    private Keyboard keyboard;
    private SimpleGfxGridPosition pos;
    private Grid grid;
    private ArrayList<GameObject> gameObjects;
    private ColisionDetector colisionDetector;
    private GameOverHandler gameOver;
    private GameOverHandler gameWin;
    private Game game;
    private Picture picture;

    public PlayableCharacter(SimpleGfxGridPosition pos) {

        this.name = name;
        this.health = health;
        this.speed = 1;
        this.dead = false;
        this.score = 0;
        this.pos = pos;
        this.keyboard = new Keyboard(this);
        move();

    }

    public void setColisionDetector(ColisionDetector colisionDetector) {
        this.colisionDetector = colisionDetector;

    }

    public void move() {

        KeyboardEvent event_UP = new KeyboardEvent();
        event_UP.setKey(KeyboardEvent.KEY_UP);
        event_UP.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent event_DOWN = new KeyboardEvent();
        event_DOWN.setKey(KeyboardEvent.KEY_DOWN);
        event_DOWN.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent event_LEFT = new KeyboardEvent();
        event_LEFT.setKey(KeyboardEvent.KEY_LEFT);
        event_LEFT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent event_RIGHT = new KeyboardEvent();
        event_RIGHT.setKey(KeyboardEvent.KEY_RIGHT);
        event_RIGHT.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event_UP);
        keyboard.addEventListener(event_DOWN);
        keyboard.addEventListener(event_LEFT);
        keyboard.addEventListener(event_RIGHT);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:
                if (!isDead()) {
                    if (colisionDetector.wallColision(pos.getCol(), pos.getRow() - 1)) {
                        break;
                    }
                    pos.moveInDirection(GridDirection.UP, 1);
                    if (colisionDetector.coinColision(pos.getCol(), pos.getRow())) {
                        this.score++;
                        if (score > 215) {
                            this.die();
                        }
                    }
                    if (colisionDetector.isUnSafe(pos.getCol(), pos.getRow())) {
                        this.die();
                    }
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (!isDead()) {
                    if (colisionDetector.wallColision(pos.getCol(), pos.getRow() + 1)) {
                        break;
                    }
                    pos.moveInDirection(GridDirection.DOWN, speed);
                    if (colisionDetector.coinColision(pos.getCol(), pos.getRow())) {
                        this.score++;
                        if (score > 215) {
                            this.die();

                        }
                    }
                    if (colisionDetector.isUnSafe(pos.getCol(), pos.getRow())) {
                        this.die();
                    }
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (!isDead()) {
                    if (colisionDetector.wallColision(pos.getCol() - 1, pos.getRow())) {
                        break;
                    }
                    pos.moveInDirection(GridDirection.LEFT, speed);
                    if (colisionDetector.coinColision(pos.getCol(), pos.getRow())) {
                        this.score++;
                        if (score > 215) {
                            this.die();
                        }
                    }
                    if (colisionDetector.isUnSafe(pos.getCol(), pos.getRow())) {
                        this.die();
                    }
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (!isDead()) {
                    if (colisionDetector.wallColision(pos.getCol() + 1, pos.getRow())) {
                        break;
                    }
                    pos.moveInDirection(GridDirection.RIGHT, speed);
                    if (colisionDetector.coinColision(pos.getCol(), pos.getRow())) {
                        this.score++;
                        if (score > 215) {
                            this.die();
                        }
                    }
                    if (colisionDetector.isUnSafe(pos.getCol(), pos.getRow())) {
                        this.die();
                    }
                }
                break;
        }
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

    public boolean isDead() {
        return dead;
    }

    public void die() {
        this.dead = true;
    }

    @Override
    public SimpleGfxGridPosition getPos() {
        return pos;
    }

    public void setGameOver(GameOverHandler gameOver) {
        this.gameOver = gameOver;
    }

    public void setGameWin(GameOverHandler gameWin) {
        this.gameWin = gameWin;
    }

    public void setPos(SimpleGfxGridPosition pos) {
        this.pos = pos;
    }
}