package ui;

import java.awt.image.BufferedImage;

/**
 * 英雄机
 */
public class Hero extends FlyObject{
    int hp = 3;

    /**
     * 构造方法确定英雄机特点
     */
    public Hero (){
        //英雄机图片
        img = App.getImg("/img/hero.png");
        //确定英雄机初始位置
        x = 200;
        y = 500;
        //英雄机大小
        w = img.getWidth();
        h = img.getHeight();
    }

    //让英雄机移动到鼠标位置上
    //mx鼠标横坐标
    // my鼠标纵坐标
    public void moveToMouse(int mx,int my){
        x = mx - w/2;
        y = my - h/2;
    }

    //向上移动
    public void moveUP(){
        y = y - 10;
    }

    //向下移动
    public void moveDown(){
        y = y + 10;
    }
    //向左移动
    public void moveLeft(){
        x = x - 10;
    }
    //向右移动
    public void moveRight(){
        x = x + 10;
    }

}
