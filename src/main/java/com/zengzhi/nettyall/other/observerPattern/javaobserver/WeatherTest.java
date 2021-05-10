package com.zengzhi.nettyall.other.observerPattern.javaobserver;


/**
 * @author xiejiawei
 * @Date 2021-04-29
 * @Time 22:27
 */
public class WeatherTest {
    public static void main (String[] args){
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }

}
