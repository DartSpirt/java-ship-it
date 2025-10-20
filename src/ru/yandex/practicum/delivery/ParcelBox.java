package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox <T extends Parcel>{
    private final double maxWeight;
    private double currentWeight = 0;
    private final List<T> parcelList;

    public ParcelBox(double maxWeight){
        this.maxWeight = maxWeight;
        parcelList = new ArrayList<>();
    }

    public void addParcel(T parcel) {
        if (parcel.getWeight() + currentWeight <= maxWeight) {
            parcelList.add(parcel);
            currentWeight += parcel.getWeight();
        } else {
            System.out.println("Вес посылки превышен, посылка не добавлена!");
        }
    }

    public ArrayList<T> getAllParcels() {
        return new ArrayList<>(parcelList);
    }

    public boolean isEmpty() {
        return parcelList.isEmpty();
    }
}
