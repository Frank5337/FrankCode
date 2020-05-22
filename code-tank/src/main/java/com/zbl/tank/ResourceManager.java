package com.zbl.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: zbl
 * @Date: Created in 10:03 2020/5/21
 * @Description: 管理图片 加载图片
 * @Version: $
 */
public class ResourceManager {

    public static BufferedImage tankL, tankU, tankR, tankD, bulletL, bulletU, bulletR, bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankL = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/tankL.gif")));
            tankU = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/tankU.gif")));
            tankR = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/tankR.gif")));
            tankD = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/tankD.gif")));
            bulletL = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/bulletL.gif")));
            bulletU = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/bulletU.gif")));
            bulletR = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/bulletR.gif")));
            bulletD = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/bulletD.gif")));
            for (int i = 0; i < explodes.length ; i++) {
                explodes[i] = ImageIO.read(Objects.requireNonNull(
                              ResourceManager.class.getClassLoader().getResourceAsStream("image/e"+(i+1)+".gif")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
