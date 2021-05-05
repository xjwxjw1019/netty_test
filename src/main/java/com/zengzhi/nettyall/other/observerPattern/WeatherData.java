package com.zengzhi.nettyall.other.observerPattern;

import java.util.ArrayList;

/**
 * @author xiejiawei
 * @Date 2021-04-29
 * @Time 21:53
 */
public class WeatherData implements Subject {

    /**
     * 定义一个集合用来存储所有的观察者
     */
    private ArrayList<Observer> obServerList;

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

    public WeatherData() {
        this.obServerList = new ArrayList<>();
    }
    public WeatherData(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    /**
     * 添加观察者
     * @param o
     */
    @Override
    public void registerObserver(Observer o) {
        obServerList.add(o);
    }

    /**
     * 移除观察者
     * @param o
     */
    @Override
    public void removeObserver(Observer o) {
        int i= obServerList.indexOf(o);
        if(i > 0){
            obServerList.remove(i);
        }

    }

    /**
     * 通知观察者
     */
    @Override
    public void notifyObserver() {
        for (int i = 0; i < obServerList.size(); i++) {
            Observer observer = obServerList.get(i);
            observer.update(temp, humidity, pressure);
        }

    }

    void setMeasurements(float temp, float humidity, float pressure){
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        MeasurementsChanges();
    }

    /**
     * 改变的时候 通知方法
     */
    private void MeasurementsChanges() {
        notifyObserver();
    }
}
