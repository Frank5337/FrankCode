package code.zbl.decorate.beverage;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/13
 * @Email: zbl5337@gmail.com
 * @Description: 豆浆
 */
public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription () {
        return beverage.getDescription() + " Soy ";
    }

    public double cost() {
        return .03 + beverage.cost();
    }
}
