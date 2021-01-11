package code.zbl.decorate.decorator;

/**
 * @Author: zbl
 * @Date: Created in 2020/12/14
 * @Description:
 * @Version: $
 */
public class Mocha extends Beverages{

    @Override
    public double cost() {
        return 1.99;
    }

    @Override
    public String getDescription() {
        description = "Mocha";
        return description;
    }
}
