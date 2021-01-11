package code.zbl.decorate.decorator;

/**
 * @Author: zbl
 * @Date: Created in 2020/12/14
 * @Description:
 * @Version: $
 */
public class Soy extends CommonBeverages {

    private Beverages beverages;

    public Soy(Beverages beverages) {
        this.beverages = beverages;
    }

    @Override
    public double cost() {
        return beverages.cost() + .99;
    }

    @Override
    public String getDescription() {
        return beverages.getDescription() + "Soy ";
    }
}
