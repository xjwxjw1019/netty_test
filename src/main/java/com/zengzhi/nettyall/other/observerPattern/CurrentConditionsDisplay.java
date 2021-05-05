package com.zengzhi.nettyall.other.observerPattern;

/**
 * @author xiejiawei
 * @Date 2021-04-29
 * @Time 22:18
 * 数据展示类
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement{

    /**
     * 温度
     */
    private float temp;

    /**
     * 湿度
     */
    private float humidity;

    /**
     * 压力
     */
    private float pressure;

    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("当前温度"
        + temp + "湿度" + humidity + "压力"+ pressure);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}
