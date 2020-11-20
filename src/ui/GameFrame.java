package ui;

import javax.swing.*;

/**
 * 游戏窗体
 * java中的窗体类JFrame
 * 自定义窗体的步骤：
 * 1写一个类继承JFrame
 * 2写一个构造方法，初始化窗体属性
 * 属性对应特点方法对应行为
 */
public class GameFrame extends JFrame {
    public GameFrame(){
        //设置标题
        setTitle("全民飞机大战");
        //设置大小setSise(宽度，高度)
        setSize(512,768);
        //设置位置居中
        //null表示界面对于屏幕左上角居中
        setLocationRelativeTo(null);
        //设置不允许玩家改变窗体大小
        setResizable(false);
        //设置默认关闭选项
        //关闭窗体时退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        GamePanel panel = new GamePanel(frame);
        panel.action();
        //将面板加入到窗体中
        frame.add(panel);
        frame.setVisible(true);

    }

}
