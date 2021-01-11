package code.zbl.factory.pizza;

import code.zbl.factory.PizzaIngredientFactory;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/11
 * @Description:
 * @Version: $
 */
public class CheesePizza extends Pizza {

    /**
     * 原料工厂
     * 所生产的原料依赖于所使用的工厂 Pizza不需要去关心原料, 只需要关心制作pizza
     * pizza和原料之间被解耦
     */
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}
