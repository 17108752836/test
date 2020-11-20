package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * java中的游戏面板
 * 1写一个类继承JPanel
 * 2写一个构造方法初始化面板属性
 * 画图片的步骤
 * 1在类中定义图片并取名
 * 2在构造方法中调用工具初始化图片
 * 3在画图方法中画图片
 */
public class GamePanel extends JPanel {
    //1定义背景图
    BufferedImage bg;
    //创建英雄机
    Hero hero = new Hero();
    //创建敌机
    //Ep ep = new Ep();
    //创建敌机大本营
    List<Ep> eps = new ArrayList<>();

    //创建英雄机弹药库
    List<Fire> fs = new ArrayList<>();
    //分数
    int score;

    /**
     * 开始游戏
     *
     */
    public void action(){
        new Thread(){
            public void run(){
                while (true){
                    epEnter();
                    //调用让敌机移动方法
                    epMove();
                    //发射子弹
                    shoot();
                    fireMove();
                    shootEp();

                    //每执行一次休眠20
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //重绘，刷新
                    repaint();

                }
            }
        }.start();
    }

    /**
     * 检测子弹是否击中敌机
     */
    protected void shootEp(){
        //遍历所有子弹
        for (int i = 0; i < fs.size(); i++){
            //获取每一颗子弹
            Fire f = fs.get(i);
            //获取每一颗子弹，就判断子弹是否击中敌机
            bang(f);
        }
    }


    /**
     * 判断子弹是否击中敌机
     * f 子弹
     */
    private void bang(Fire f) {
        //遍历所以敌机
        for (int i = 0; i < eps.size(); i++) {
            //获取每一个敌机
            Ep e = eps.get(i);
            //判断子弹是否击中敌机
            if (e.shootBy(f)) {
                //如果敌机被子弹击中
                //1敌机死亡，从敌机大本营删除
                eps.remove(e);
                //删除子弹
                fs.remove(f);
                //增加分数
                score += 10;
            }

        }
    }

    private void fireMove()
    {

        for (int i = 0; i < fs.size(); i++){
            Fire fire = fs.get(i);
            fire.move();
        }
    }

    /**
     * 发射子弹
     */
    int findex = 0;
    private void shoot() {
        findex++;
        if (findex >= 20){
            //创建子弹
            Fire f = new Fire(hero.x,hero.y);
            //将子弹存在弹药库中
            fs.add(f);
            findex = 0;
        }
    }

    private void epMove() {
        for (int i = 0; i < eps.size(); i++){
            //获取一个敌机
            Ep e = eps.get(i);
            //让敌机移动
            e.move();
        }
    }

    /**
     * 敌机入场方法
     */

    int index = 0;//敌机创建次数
    private void epEnter() {
        index++;
        if (index > 20){
            //创建敌机
            Ep e = new Ep();
            //将敌机加入敌机大本营
            eps.add(e);
            index = 0;
        }

    }


    //构造方法确定面板属性
    public GamePanel(GameFrame frame){
        //设置背景
        setBackground(Color.black);
        //初始化图片
        bg = App.getImg("/img/bg3.jpg");
        //使用鼠标监听器
        //1创建鼠标适配器
        MouseAdapter adapter = new MouseAdapter() {
            /**
             * 需要监听的鼠标事件
             * 鼠标事件
             * mouseMoved()监听鼠标移动事件
             * mouseClicked()监听鼠标单击事件
             * mousePressed()监听鼠标按下去事件
             * mouseEntered()监听鼠标移入游戏界面事件
             * mouseExited()监听鼠标移出界面事件
             * mouseEvent记录鼠标做的事情
             */
            @Override
            public void mouseMoved(MouseEvent e) {
                //当鼠标移动时触发方法
                //让英雄机跟鼠标一起移动，鼠标横纵坐标等于英雄机横纵坐标
                int mx = e.getX();//鼠标横坐标
                int my = e.getY();//鼠标纵坐标
                //让英雄机移动到鼠标位置上
                hero.moveToMouse(mx,my);
                //重新调用paint方法
                repaint();
            }
        };

        //将适配器加入监听器中
        addMouseListener(adapter);
        addMouseMotionListener(adapter);

        //使用键盘监听器
        //创建键盘适配器
        KeyAdapter kd = new KeyAdapter() {
            //确定键盘监听事件

            @Override
            public void keyPressed(KeyEvent e) {
                //按下键盘触发事件
                int keCode = e.getKeyCode();
                if(keCode == KeyEvent.VK_UP){
                    //英雄机向上移动
                    hero.moveUP();
                }else if(keCode == KeyEvent.VK_DOWN){
                    //英雄机向下移动
                    hero.moveDown();
                }else if (keCode == KeyEvent.VK_LEFT){
                    //英雄机向左移动
                    hero.moveLeft();
                }else if (keCode == KeyEvent.VK_RIGHT){
                    //英雄机向右移动
                    hero.moveRight();
                }
                repaint();
            }
        };

        //将键盘适配器加入到键盘监听器中
        frame.addKeyListener(kd);

    }

    /**
     * 专用画图方法
     * Graphics g画笔
     */

    @Override
    public void paint(Graphics g){
        super.paint(g);
        //画图片
        //g.drawImage(图片，图片横坐标，图片纵坐标，null)
        //横纵坐标设置图片左上角坐标
        //在paint中画图有顺序，先画的会被后画的覆盖
        g.drawImage(bg,0,0,512,768,null);
        g.drawImage(hero.img,hero.x,hero.y,hero.w,hero.h,null);
        //敌机
        for (int i = 0; i < eps.size(); i++){
            Ep ep = eps.get(i);
            g.drawImage(ep.img, ep.x, ep.y, ep.w, ep.h, null);
        }

        //画子弹
        for (int i = 0; i < fs.size(); i++){
            Fire fire = fs.get(i);
            g.drawImage(fire.img, fire.x, fire.y, fire.w, fire.h, null);
        }

        //画分数
        g.setColor(Color.white);
        g.setFont(new Font("楷体",Font.BOLD,20));
        g.drawString("分数" + score,10,30);

        //画英雄机血量
        for(int i = 0; i < hero.hp; i++){
            g.drawImage(hero.img, 350+i*35, 5,30,30 ,null);
        }

    }
}
