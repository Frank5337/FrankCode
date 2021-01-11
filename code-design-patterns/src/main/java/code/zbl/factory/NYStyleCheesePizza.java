package code.zbl.factory;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/11
 * @Description:
 * @Version: $
 */
public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza() {
        super.name = "NYStyleCheesePizza";
        super.dough = "Thin Crust Dough";
        super.sauce = "Marinara Sauce";

        topping.add("Grated Reggiano Cheese");
    }
}
