package code.zbl.observer.pull;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/7
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class CurrentConditionDisplay implements Observer, DisplayElement {

    private float temperature;

    private float humidity;

    /**
     * 留着引用取消订阅比较方便
     */
    private Observable observable;

    public CurrentConditionDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable obs, Object arg) {
        //这样变成拉的, 不是就传不进来, 其实还是fori遍历推送
        //然后让其自己get参数, 这样的 '拉'  如果是推送的话, 就是参数传到arg Observable把参数传过来
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            temperature = weatherData.getTemperature();
            humidity = weatherData.getHumidity();
            display();
        }
    }

    public void display() {
        System.out.println("Current conditions: " + temperature
                        + "F degrees and " + humidity + "% humidity");
    }


}
