package code.zbl.factory.pizza;

import code.zbl.factory.pizza.raw.ny.*;

import java.util.Arrays;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/11
 * @Description:
 * @Version: $
 */
public abstract class Pizza {

    String name;

    /**
     * 面团
     */
    Dough dough;

    /**
     * 酱
     */
    Sauce sauce;

    Veggies[] veggies;

    Cheese cheese;

    /**
     * 意大利香肠
     */
    Pepperoni pepperoni;

    Clams clams;

    public abstract void prepare();

    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 打印披萨
     * @return
     */
    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", dough=" + dough +
                ", sauce=" + sauce +
                ", veggies=" + Arrays.toString(veggies) +
                ", cheese=" + cheese +
                ", pepperoni=" + pepperoni +
                ", clams=" + clams +
                '}';
    }
}
