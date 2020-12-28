package code.zbl.strategy;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/6
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class MallardDuck extends Duck {

    public MallardDuck(){
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    public void display(){
        System.out.println("I'm a real Mallard duck");
    }
}
