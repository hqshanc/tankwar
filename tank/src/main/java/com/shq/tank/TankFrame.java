package com.shq.tank;


import com.shq.tank.abstractfactory.BaseExplode;
import com.shq.tank.abstractfactory.DefaultFactory;
import com.shq.tank.abstractfactory.GameFactory;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {
    static final int GAME_WIDTH = Integer.parseInt(PropertyMgr.get("gameWidth")),
            GAME_HEIGHT = Integer.parseInt(PropertyMgr.get("gameHeight"));
    Tank myTank = new Tank(Integer.parseInt(PropertyMgr.get("gameWidth"))/2,
            Integer.parseInt(PropertyMgr.get("gameHeight"))- Tank.HIGHT, Dir.UP, this, Group.GOOD, 10);
    List<Bullet> bulles = new ArrayList<Bullet>();
    List<Tank> tanks = new ArrayList<Tank>();
    List<BaseExplode> explodes = new ArrayList<BaseExplode>();

    GameFactory factory = new DefaultFactory();

    public TankFrame() throws HeadlessException {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        //可以调整窗口大小
        this.setResizable(true);
        this.setTitle("tank war");
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(new MyKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹数量" + bulles.size(), 10, 60);
        g.drawString("敌人的数量" + tanks.size(), 10, 80);
        g.setColor(c);
        if (myTank.isLive()) {
            myTank.paint(g);
        }

        for(Iterator<Bullet> it = bulles.iterator();it.hasNext();){
            Bullet b = it.next();
            if(!b.isLive()) {
                it.remove();
            }else {
                b.paint(g);
            }
        }

        for(Iterator<Tank> it = tanks.iterator();it.hasNext();){
            Tank t = it.next();
            if(!t.isLive()) {
                it.remove();
            }else {
                t.paint(g);
            }
        }

        for(Iterator<BaseExplode> it = explodes.iterator();it.hasNext();){
            BaseExplode t = it.next();
//            if(!t.isLive()) {
//                it.remove();
//            }else {
                t.paint(g);
//            }
        }


        //碰撞删除
        for (int i = 0; i< bulles.size();i ++) {
            //bulles.get(i).collideWith(myTank);
            for (int j = 0; j< tanks.size();j ++) {
                bulles.get(i).collideWith(tanks.get(j));
            }
        }
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    class MyKeyListener extends KeyAdapter {
        boolean bl = false;
        boolean bu = false;
        boolean br = false;
        boolean bd = false;
        //KEY 按下调用
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bl && !bd && !br && !bu) {
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
                if (bl) myTank.setDir(Dir.LEFT);
                if (bd) myTank.setDir(Dir.DOWN);
                if (br) myTank.setDir(Dir.RIGHT);
                if (bu) myTank.setDir(Dir.UP);
            }
        }

        //key 抬起调用
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                default:
                    break;
            }

            setMainTankDir();
        }
    }
}
