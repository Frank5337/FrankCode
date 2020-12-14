package code.zbl.decorate.beverage;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/13
 * @Email: zbl5337@gmail.com
 * @Description: 浓缩咖啡
 */
public class Espresso extends Beverage {

    /**
     * 浓缩咖啡
     */
    public Espresso() {
        description = "Espresso";
    }

    public double cost() {
        return 1.99;
    }

}
