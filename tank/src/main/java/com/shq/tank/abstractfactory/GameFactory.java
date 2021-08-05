package com.shq.tank.abstractfactory;

import com.shq.tank.Dir;
import com.shq.tank.Group;
import com.shq.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank (int x, int y, Dir dir, Group group, TankFrame tf , int speed);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, TankFrame tf);
}
