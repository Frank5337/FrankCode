package code.zbl.observer.push;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/7
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public interface Observer {

    /**
     *
     * @param temp      温度
     * @param humidity  湿度
     * @param pressure  气压
     */
    void update(float temp, float humidity, float pressure);
}
