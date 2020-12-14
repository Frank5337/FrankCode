package code.zbl.observer.pull;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/7
 * @Email: zbl5337@gmail.com
 * @Description: 可观察者, 也是主题
 */
public class WeatherData extends Observable implements Observer {

    private float temperature;

    private float humidity;

    private float pressure;

    public WeatherData() {
    }

    public void measurementsChanged() {
        setChanged();
        //没有用notifyObservers传送数据 表示用的是 拉
        notifyObservers();
    }

    public void setMeasurementsChanged(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void update(Observable o, Object arg) {

    }
}
