package code.zbl.simuduck;

import org.junit.jupiter.api.Test;

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
}
