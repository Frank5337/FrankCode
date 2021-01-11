package code.zbl.factory;

import code.zbl.factory.pizza.CheesePizza;
import code.zbl.factory.pizza.ClamPizza;
import code.zbl.factory.pizza.Pizza;
import code.zbl.factory.pizza.VeggiePizza;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/11
 * @Description:
 * @Version: $
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if (item.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if (item.equals("veggie")) {
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("New York Style Veggie Pizza");
        } else if (item.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("New York Style Clam Pizza");
        }

        return pizza;
    }
}
