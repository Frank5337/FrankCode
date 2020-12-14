package code.zbl.decorate.beverage;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/13
 * @Email: zbl5337@gmail.com
 * @Description: 奶泡
 */
public class Whip extends CondimentDecorator {

    Beverage beverage;

    /**
     * 奶泡
     * @param beverage
     */
    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription () {
        return beverage.getDescription() + " Whip ";
    }

    public double cost() {
        return .01 + beverage.cost();
    }
}
