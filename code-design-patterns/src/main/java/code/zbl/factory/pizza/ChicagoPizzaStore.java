package code.zbl.factory.pizza;

import code.zbl.factory.PizzaStore;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/11
 * @Description:
 * @Version: $
 */
public class ChicagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
        if (item.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else if (item.equals("veggie")) {
            return new ChicagoStyleVeggiePizza();
        } else if (item.equals("clam")) {
            return new ChicagoStyleClamPizza();
        } else
            return null;
    }
}
