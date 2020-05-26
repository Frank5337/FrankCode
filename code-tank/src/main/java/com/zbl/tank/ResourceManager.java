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

    public static BufferedImage tankL, tankU, tankR, tankD,
                                BadTankL, BadTankU, BadTankR, BadTankD,
                                bulletL, bulletU, bulletR, bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankU = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/GoodTank2.png")));
            tankD = ImageUtil.rotateImage(tankU, 180);
            tankL = ImageUtil.rotateImage(tankU, -90);
            tankR = ImageUtil.rotateImage(tankU, 90);


            BadTankU = ImageIO.read(Objects.requireNonNull(
                    ResourceManager.class.getClassLoader().getResourceAsStream("image/BadTank1.png")));
            BadTankD = ImageUtil.rotateImage(BadTankU, 180);
            BadTankL = ImageUtil.rotateImage(BadTankU, -90);
            BadTankR = ImageUtil.rotateImage(BadTankU, 90);


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
