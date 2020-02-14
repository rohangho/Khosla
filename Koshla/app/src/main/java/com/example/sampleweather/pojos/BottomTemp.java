package com.example.sampleweather.pojos;

public class BottomTemp {

    String icon;
    String temp;
    String dateToShow;

    public BottomTemp(String icon, String temp, String dateToShow) {
        this.icon = icon;
        this.temp = temp;
        this.dateToShow = dateToShow;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDateToShow() {
        return dateToShow;
    }

    public void setDateToShow(String dateToShow) {
        this.dateToShow = dateToShow;
    }
}
