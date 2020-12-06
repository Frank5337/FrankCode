package code.zbl.simuduck;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/6
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class MuteQuack implements QuackBehavior{
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
