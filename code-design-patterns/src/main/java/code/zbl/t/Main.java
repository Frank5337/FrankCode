package code.zbl.t;

import java.util.HashMap;

/**
 * @Author: zbl
 * @Date: Created in 2022/4/17
 * @Description:
 * @Version: $
 */
public class Main {

    public static void main(String[] args) {
        HashMap map = new HashMap();
        A a = new A();
        B b = new B();
        map.put(a, "a");
        map.put(b, "b");
        System.out.println(map.get(a));
        System.out.println(map.get(b));
    }
}
