package ui;

import java.awt.image.BufferedImage;

/**
 * 游戏中有的飞行物类
 * 在开发中通常会将具有相同特点的类放到一起将这些相同特点抽离出来形成一个父类
 * 好处提高代码的复用性
 */
public class FlyObject {
    BufferedImage img; //飞行物图片
    int x;   //飞行物横坐标
    int y;   //纵坐标
    int w;   //宽
    int h;   //高
}
