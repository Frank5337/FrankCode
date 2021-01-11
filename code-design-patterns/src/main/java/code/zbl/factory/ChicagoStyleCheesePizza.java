package code.zbl.factory;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/11
 * @Description:
 * @Version: $
 */
public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        super.name = "ChicagoStyleCheesePizza";
        super.dough = "Extra Thick Crust Dough";
        super.sauce = "Plum Tomato Sauce";

        topping.add("Shredded Mozzarella Cheese");
    }

    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
