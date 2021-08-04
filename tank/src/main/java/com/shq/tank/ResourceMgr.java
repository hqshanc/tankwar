package com.shq.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    private volatile static ResourceMgr resMgr;
    private ResourceMgr() {}

    public static ResourceMgr getInstance(){
        if (resMgr == null) {
            synchronized (ResourceMgr.class){
                if (resMgr == null) {
                    resMgr = new ResourceMgr();
                }
            }
        }
        return resMgr;
    }
    public  BufferedImage tankl, tanku,tankr, tankd;
    public  BufferedImage bulletl, bulletu, bulletr, bulletd;
    public  BufferedImage [] explodes = new BufferedImage[8];
    {
        try {
            tankl = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankl.png"));
            tanku = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tanku.png"));
            tankr = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankr.png"));
            tankd = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/tankd.png"));
            bulletl = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletl.png"));
            bulletu = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletu.png"));
            bulletr = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletr.png"));
            bulletd = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bulletd.png"));
            for (int i = 0; i< 8; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("image/bum"+(i+1) + (i+1)+".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
