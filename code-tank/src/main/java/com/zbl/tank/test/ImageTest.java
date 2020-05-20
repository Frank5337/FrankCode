package com.zbl.tank.test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author: zbl
 * @Date: Created in 17:05 2020/5/20
 * @Description:
 * @Version: $
 */
public class ImageTest {

    @Test
    public void test01() throws Exception {
//        BufferedImage bufferedImage = ImageIO.read(new File("D:\\work\\develop\\IdeaProjects\\code\\code-tank\\src\\main\\resources\\image\\bulletD.gif"));

        //OK 读的是 resources下的文件
        BufferedImage bufferedImage = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("image/bulletD.gif"));
//        BufferedImage bufferedImage = ImageIO.read(new File("com/zbl/tank/image/bulletD.gif"));
        assertNotNull(bufferedImage);

        //BufferedImage bi1 = ImageIO.read(new File("image/bulletD.gif"));
        //assertNotNull(bi1);
    }

}
