package com.shq.tank;

import java.awt.*;

public class Bullet {
    private static final int speed = 30;
    private static int WIDTH = ResourceMgr.bulletl.getWidth(), HIGHT = ResourceMgr.bulletl.getHeight();
    private int x, y;
    private Dir dir;
    private boolean live = true;
    private TankFrame tf;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();


    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g){
        if (!live) {
            tf.bulles.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletl, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletu, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletr, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletd, x, y, null);
                break;

        }
        move();
    }

    private void move() {
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
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.live = false;
        }
    }

    public void collideWith(Tank tank) {
        if (this.getGroup() == tank.getGroup()) return;
        if (rect.intersects(tank.rect)) {
            tf.explodes.add(new Explode(x,y,tf));
            //tank.die();
            this.setLive(false);
            tank.setLive(false);
        }
    }
}
