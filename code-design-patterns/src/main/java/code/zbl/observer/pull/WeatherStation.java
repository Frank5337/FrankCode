package code.zbl.observer.pull;

import org.junit.jupiter.api.Test;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/7
 * @Email: zbl5337@gmail.com
 * @Description:
 */
public class WeatherStation {

    @Test
    public void test01() throws Exception {
        WeatherData weatherData = new WeatherData();

        CurrentConditionDisplay currentConditionDisplay =
                new CurrentConditionDisplay(weatherData);
        ForecastDisplay forecastDisplay =
                new ForecastDisplay(weatherData);

        weatherData.setMeasurementsChanged(80, 65, 30.4f);
//        currentPressure: 30.4  lastPressure: 29.92
//        Current conditions: 80.0F degrees and 65.0% humidity
//      次序不同的原因是, 在主题(Observable)中, 是倒着遍历的Vector
    }
}
