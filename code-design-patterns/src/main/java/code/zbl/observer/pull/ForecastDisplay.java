package code.zbl.observer.pull;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/7
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class ForecastDisplay implements Observer, DisplayElement {

    private float currentPressure = 29.92f;

    private float lastPressure;

    /**
     * @param obs subject
     * */
    public ForecastDisplay(Observable obs) {
        obs.addObserver(this);
    }

    public void display() {
        System.out.println("currentPressure: " + currentPressure + "  lastPressure: " + lastPressure);
    }

    public void update(Observable o, Object arg) {
        if (o instanceof  WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            lastPressure = currentPressure;
            currentPressure = weatherData.getPressure();
        }
        display();
    }
}
