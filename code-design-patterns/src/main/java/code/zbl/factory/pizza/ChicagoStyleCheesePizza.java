package code.zbl.factory.pizza;

import code.zbl.factory.pizza.raw.ny.Dough;
import code.zbl.factory.pizza.raw.ny.Sauce;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/11
 * @Description:
 * @Version: $
 */
public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        super.name = "ChicagoStyleCheesePizza";
        //"Extra Thick Crust Dough";
        super.dough = new Dough();
        //"Plum Tomato Sauce";
        super.sauce = new Sauce();

        //topping.add("Shredded Mozzarella Cheese");
    }

    @Override
    public void prepare() {

    }

    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
