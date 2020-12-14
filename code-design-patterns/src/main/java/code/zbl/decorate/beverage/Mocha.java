package code.zbl.decorate.beverage;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/13
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription () {
        return beverage.getDescription() + " Mocha ";
    }

    public double cost() {
        return .02 + beverage.cost();
    }
}
