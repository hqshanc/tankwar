package com.shq.tank.abstractfactory;

import com.shq.tank.*;

public class DefaultFactory extends GameFactory {
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf, int speed) {
        return new Tank(x, y, dir, tf, group, speed);
    }

    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y , tf);
    }

    public BaseBullet createBullet(int x, int y, TankFrame tf) {
        return null;
    }
}
