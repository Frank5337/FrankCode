package code.zbl.decorate.decorator;

/**
 * @Author: zbl
 * @Date: Created in 2020/12/14
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        Beverages beverages = new Mocha();
        beverages = new Soy(beverages);
        System.out.println(beverages.cost());
        System.out.println(beverages.getDescription());
    }

}
