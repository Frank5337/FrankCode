package code.zbl.factory;

import code.zbl.factory.pizza.Pizza;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/11
 * @Description:
 * @Version: $
 */
public abstract class PizzaStore {

    /**
     * 订购披萨
     */
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    /**
     * 工厂方法用来处理对象的创建, 并将这样的行为封装在子类中,
     * 这样, 客户程序中关于超类的代码和子类对象创建代码就解耦了
     */
    protected abstract Pizza createPizza(String type);
}
