package com.shq.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x, y;
    private Dir dir;
    private int speed = 5;
    private boolean moving = false;
    private TankFrame tf = null;
    public static int WIDTH = ResourceMgr.getInstance().tankl.getWidth(), HIGHT = ResourceMgr.getInstance().tankl.getHeight();
    private boolean live = true;
    private Group group = Group.BAD;
    private Random random = new Random();
    Rectangle rect = new Rectangle();
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group, int speed) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.speed = speed;
        if (this.getGroup() == Group.BAD) {
            this.moving = true;
        }
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HIGHT;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.tanks.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.getInstance().tankl, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.getInstance().tanku, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.getInstance().tankr, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.getInstance().tankd, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case UP:
                y-= speed;
                break;
            case DOWN:
                y+= speed;
                break;
            case LEFT:
                x-= speed;
                break;
            case RIGHT:
                x+= speed;
                break;
            default:
                break;
        }

        if (this.getGroup() == Group.BAD) {
            getCurrDir();
        }

        if (random.nextInt(10) > 8 && this.getGroup() == Group.BAD) {
            this.fire();
        }
        boundsCheck();
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x<0) x = 0;
        if (this.y< 30) y = 30;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HIGHT) y = TankFrame.GAME_HEIGHT - Tank.HIGHT;
    }

    private void getCurrDir() {
        switch (random.nextInt(100)) {
            case 1:
                dir = Dir.UP;
                break;
            case 2:
                dir = Dir.DOWN;
                break;
            case 3:
                dir = Dir.LEFT;
                break;
            case 0:
                dir = Dir.RIGHT;
                break;
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void fire() {
        switch (dir) {
            case LEFT:
                tf.bulles.add(new Bullet(x - ResourceMgr.getInstance().tankl.getWidth()/2  ,
                        y + ResourceMgr.getInstance().bulletl.getHeight()/7*6 , dir, this.tf, this.group));
                break;
            case UP:
                tf.bulles.add(new Bullet(x + ResourceMgr.getInstance().bulletl.getWidth()/7*6 ,
                        y - ResourceMgr.getInstance().tanku.getWidth()/2 , dir, this.tf,this.group));
                break;
            case RIGHT:
                tf.bulles.add(new Bullet(x + ResourceMgr.getInstance().tankr.getWidth() ,
                        y + ResourceMgr.getInstance().bulletl.getHeight()/7*6 , dir, this.tf, this.group));
                break;
            case DOWN:
                tf.bulles.add(new Bullet(x + ResourceMgr.getInstance().bulletl.getWidth()/7*6,
                        y + ResourceMgr.getInstance().tanku.getWidth() , dir, this.tf, this.group));
                break;

        }


    }
}
