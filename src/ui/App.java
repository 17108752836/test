package ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class App {

    public static BufferedImage getImg(String path){
        //加载图片
        //App.class找到App类的路径
        try {
            BufferedImage image = ImageIO.read(App.class.getResource(path));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
