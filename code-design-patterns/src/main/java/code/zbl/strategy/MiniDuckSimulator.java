package code.zbl.strategy;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/6
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class MiniDuckSimulator {

    @Test
    public void test01() throws Exception{
        Duck mallard = new MallardDuck();
        mallard.performFly();
        mallard.performQuack();
    }

    @Test
    public void test02() throws Exception {
        Duck model = new ModelDuck();
        model.performFly();
        model.performQuack();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }

    @Test
    public void test03() throws Exception {
        try (FileInputStream inputStream = new FileInputStream(new File("D:\\work\\develop\\IdeaProjects\\code\\code-design-patterns\\src\\main\\java\\code\\zbl\\simuduck\\Duck.java"))){
            System.out.println(inputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        for (;;) {

        }
    }
}
