package com.shq.tank;

public class DefaultFireStrategy implements FireStrategy {
    private volatile static DefaultFireStrategy strategy;
    private DefaultFireStrategy() {}
    public static DefaultFireStrategy getInstance(){
        if (strategy == null) {
            synchronized (DefaultFireStrategy.class) {
                if (strategy == null) {
                    strategy = new DefaultFireStrategy();
                }
            }
        }
        return strategy;
    }
    public void fire(Tank tank) {
        switch (tank.getDir()) {
            case LEFT:
                new Bullet(tank.x - ResourceMgr.getInstance().tankl.getWidth()/2  ,
                        tank.y + ResourceMgr.getInstance().bulletl.getHeight()/7*6 , tank.dir, tank.tf, tank.group);
                break;
            case UP:
                new Bullet(tank.x + ResourceMgr.getInstance().bulletl.getWidth()/7*6 ,
                        tank.y - ResourceMgr.getInstance().tanku.getWidth()/2 , tank.dir, tank.tf,tank.group);
                break;
            case RIGHT:
                new Bullet(tank.x + ResourceMgr.getInstance().tankr.getWidth() ,
                        tank.y + ResourceMgr.getInstance().bulletl.getHeight()/7*6 , tank.dir, tank.tf, tank.group);
                break;
            case DOWN:
                new Bullet(tank.x + ResourceMgr.getInstance().bulletl.getWidth()/7*6,
                        tank.y + ResourceMgr.getInstance().tanku.getWidth() , tank.dir, tank.tf, tank.group);
                break;
        }
    }
}
