package com.shq.tank;

import com.shq.tank.abstractfactory.BaseExplode;

import java.awt.*;

public class Explode extends BaseExplode {
    private static int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth(), HIGHT = ResourceMgr.getInstance().explodes[0].getHeight();
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
    @Override
    public void paint(Graphics g){
        g.drawImage(ResourceMgr.getInstance().explodes[step++], x, y, null);
        if (step >= ResourceMgr.getInstance().explodes.length) {tf.explodes.remove(this);}
    }


}
