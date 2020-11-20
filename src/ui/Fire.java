package ui;

/**
 *子弹类
 */
public class Fire extends FlyObject{
    /**
     * 初始化子弹
     * hx 英雄机横坐标
     * hy 英雄纵坐标
     */
    public Fire(int hx, int hy){
        img = App.getImg("/img/zd.png");
        //子弹大小
        w = img.getWidth();
        h = img.getHeight();
        //子弹位置
        x = hx;
        y = hy;
    }

    public void move() {
        y -= 10;
    }
}
