package com.app.o.convert;

/**
 * Created by o on 10/05/2016.
 */
public class Patient {
    private String id;
    private String firstName;
    private String name;
    private String numHeartRateMin, numHeartRateMax, numBloodPressureLowerMin, numBloodPressureLowerMax,
            numBloodPressureHigherMin, numBloodPressureHigherMax,
            numTemperatureMin, numTemperatureMax;

    public Patient() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumHeartRateMin() {
        return numHeartRateMin;
    }

    public void setNumHeartRateMin(String numHeartRateMin) {
        this.numHeartRateMin = numHeartRateMin;
    }
    public String getNumHeartRateMax() {
        return numHeartRateMax;
    }
    public void setNumHeartRateMax(String numHeartRateMax) {
        this.numHeartRateMax = numHeartRateMax;
    }
    public String getnumBloodPressureLowerMin() {
        return numBloodPressureLowerMin;
    }
    public void setnumBloodPressureLowerMin(String numBloodPressureLowerMin) {
        this.numBloodPressureLowerMin = numBloodPressureLowerMin;
    }
    public String getnumBloodPressureLowerMax() {
        return numBloodPressureLowerMax;
    }
    public void setBloodPressureLowerMax(String numBloodPressureLowerMax) {
        this.numBloodPressureLowerMax = numBloodPressureLowerMax;
    }
    public String getnumBloodPressureHigherMin() {
        return numBloodPressureHigherMin;
    }
    public void setnumBloodPressureHigherMin(String numBloodPressureHigherMin) {
        this.numBloodPressureHigherMin = numBloodPressureHigherMin;
    }
    public String getnumBloodPressureHigherMax() {
        return numBloodPressureHigherMax;
    }
    public void setnumBloodPressureHigherMax(String numBloodPressureHigherMax) {
        this.numBloodPressureHigherMax = numBloodPressureHigherMax;
    }
    public String getnumTemperatureMin() {
        return numTemperatureMin;
    }
    public void setnumTemperatureMin(String numTemperatureMin) {
        this.numTemperatureMin = numTemperatureMin;
    }
    public String getTemperatureMax() {
        return numTemperatureMax;
    }
    public void setnumTemperatureMax(String numTemperatureMax) {
        this.numTemperatureMax = numTemperatureMax;
    }
}