package ui;

import java.util.Random;

/**
 * 敌机类
 */
public class Ep extends FlyObject{

    /**
     * 给敌机定型
     */
    public Ep(){
        Random rd = new Random();
        //生成一个随机数，用来选取图片
        int index = rd.nextInt(2) + 1;
        img = App.getImg("/img/ep" + index + ".png");
        w = img.getWidth();
        h = img.getHeight();
        x = rd.nextInt(512 - w);
        y = 0;
    }

    /**
     * 敌机移动方法
     * 敌机向下移动
     */
    public void move() {
        y += 10;

    }

    public boolean shootBy(Fire f) {
        Boolean hit = x <= f.x + f.w && x>= f.x - w
                && y < f.y + f.h && y > f.y - h;
        return  hit;
    }
}
