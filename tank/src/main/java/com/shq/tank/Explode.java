package com.shq.tank;

import java.awt.*;

public class Explode {
    private static int WIDTH = ResourceMgr.explodes[0].getWidth(), HIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;
    private boolean live = true;
    private TankFrame tf;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {this.setLive(false);}
    }


}