package code.zbl.strategy;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/6
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public abstract class Duck {

    FlyBehavior flyBehavior;

    QuackBehavior quackBehavior;

    public Duck() {

    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }

    /**
     * 必须有颜色, 自己实现
     */
    public abstract void display();

    /**
     * 所有鸭子都会游泳,
     * 所有的鸭子都是飘着的, 包括诱饵鸭
     */
    public void swim(){
        System.out.println("All ducks float, even decoys!");
    }

    //动态设置  fly quack

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        System.out.println("give fly power");
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }


}
