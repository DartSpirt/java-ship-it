package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable{

    private static final int parcelCost = 4;

    public FragileParcel(String description, double weight,
                         String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + description + " упакована в защитную плёнку");
        super.packageItem();
    }

    @Override
    protected int getParcelCost(){
        return parcelCost;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + description + " изменила местоположение на " + newLocation);
    }
}
