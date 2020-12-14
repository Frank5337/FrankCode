package code.zbl.decorate.beverage;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/13
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public abstract class Beverage {

    String description = "Unknown Beverage";

    public String getDescription () {
        return description;
    }

    public abstract double cost();
}
