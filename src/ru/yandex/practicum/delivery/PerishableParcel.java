package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel{

    private final int timeToLive;
    private static final int parcelCost = 3;

    public PerishableParcel(String description, double weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay, int sendDay){
        boolean isExpired = false;
        int dayDiffernce = sendDay + currentDay;
        if(this.timeToLive < dayDiffernce){
            isExpired = true;
        }
        return isExpired;
    }

    @Override
    protected int getParcelCost(){
        return parcelCost;
    }
}
