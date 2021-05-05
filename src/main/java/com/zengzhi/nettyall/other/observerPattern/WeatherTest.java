package com.zengzhi.nettyall.other.observerPattern;

/**
 * @author xiejiawei
 * @Date 2021-04-29
 * @Time 22:27
 */
public class WeatherTest {
    public static void main (String[] args){
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(20, 30, 40);
        weatherData.setMeasurements(21, 31, 41);
        weatherData.setMeasurements(22, 32, 42);
    }

}
