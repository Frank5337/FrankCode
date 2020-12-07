package code.zbl.observer.push;

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

        weatherData.setMeasurementsChanged(80, 65, 30.4f);
    }
}
