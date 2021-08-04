package com.shq.tank;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame t = new TankFrame();
        int badCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        //初始化敌方坦克
        for (int i = 0; i< badCount; i++) {
            t.tanks.add(new Tank(new Random().nextInt(1000), new Random().nextInt(1000), Dir.DOWN, t,Group.BAD, 3));
        }
        //循环调用刷新页面
        while (true) {
            Thread.sleep(50);
            t.repaint();
        }
    }
}
