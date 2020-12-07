package code.zbl.observer.push;

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
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature
                        + "F degrees and " + humidity + "% humidity");
    }

}
