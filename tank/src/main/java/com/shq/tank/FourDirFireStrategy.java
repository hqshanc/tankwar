package com.shq.tank;

public class FourDirFireStrategy implements FireStrategy {
    private volatile static FourDirFireStrategy strategy;
    private FourDirFireStrategy() {}
    public static FourDirFireStrategy getInstance(){
        if (strategy == null) {
            synchronized (FourDirFireStrategy.class) {
                if (strategy == null) {
                    strategy = new FourDirFireStrategy();
                }
            }
        }
        return strategy;
    }
    public void fire(Tank tank) {
        int bx = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.y + Tank.HIGHT/2 - Bullet.HIGHT/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bx, by, dir,tank.tf, tank.group);
        }
    }
}
