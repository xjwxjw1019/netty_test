package com.zengzhi.nettyall.other.javaobserver;

import com.zengzhi.nettyall.other.observerPattern.DisplayElement;
import com.zengzhi.nettyall.other.observerPattern.Subject;

import java.util.Observable;
import java.util.Observer;

/**
 * @author xiejiawei
 * @Date 2021-04-29
 * @Time 22:18
 * 当前的天气情况展示
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement{

    /**
     * 温度
     */
    private float temp;

    /**
     *
     */
    private Observable observable;

    /**
     * 湿度
     */
    private float humidity;

    /**
     * 压力
     */
    private float pressure;

    private Subject weatherData;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("当前温度"
        + temp + "湿度" + humidity + "压力"+ pressure);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData)o;
            this.temp = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
