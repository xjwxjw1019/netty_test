package com.zengzhi.nettyall.other.javaobserver;

import java.util.Observable;

/**
 * @author xiejiawei
 * @Date 2021-04-29
 * @Time 21:53
 */
public class WeatherData extends Observable {


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
    }
    public WeatherData(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
    }


    public void setMeasurements(float temp, float humidity, float pressure){
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        MeasurementsChanges();
    }

    /**
     * 改变的时候 通知方法
     */
    private void MeasurementsChanges() {
        setChanged();
        notifyObservers();
    }


    public float getTemperature() {
        return temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }


}
