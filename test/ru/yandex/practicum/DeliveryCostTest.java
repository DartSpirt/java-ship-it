package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryCostTest {

    @Test
    public void shouldBe20WhenStandardParcelWeigth10() {
        StandardParcel standardParcel =
                new StandardParcel("Standart parcel 1", 10, "Address-1", 4);
        assertEquals(20, standardParcel.calculateDeliveryCost());
    }
    @Test
    public void shouldBe40WhenStandardParcelWeigth20() {
        StandardParcel standardParcel =
                new StandardParcel("Standart parcel 1", 20, "Address-1", 4);
        assertEquals(40, standardParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldBe0WhenStandardParcelWeigth0() {
        StandardParcel standardParcel =
                new StandardParcel("Standart parcel 1", 0, "Address-1", 4);
        assertEquals(0, standardParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldBe30WhenPerishableParcelWeigth10() {
        PerishableParcel perishableParcel =
                new PerishableParcel("Perishable parcel 2", 10, "Address-2", 7, 10);
        assertEquals(30, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldBe60WhenPerishableParcelWeigth20() {
        PerishableParcel perishableParcel =
                new PerishableParcel("Perishable parcel 2", 20, "Address-2", 7, 10);
        assertEquals(60, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldBe0WhenPerishableParcelWeigth0() {
        PerishableParcel perishableParcel =
                new PerishableParcel("Perishable parcel 2", 0, "Address-2", 7, 10);
        assertEquals(0, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldBe40WhenFragileParcelWeigth10() {
        FragileParcel fragileParcel =
                new FragileParcel("Fragile parcel 3", 10, "Address-3", 6);
        assertEquals(40, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldBe80WhenFragileParcelWeigth20() {
        FragileParcel fragileParcel =
                new FragileParcel("Fragile parcel 3", 20, "Address-3", 6);
        assertEquals(80, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldBe0WhenFragileParcelWeigth0() {
        FragileParcel fragileParcel =
                new FragileParcel("Fragile parcel 3", 0, "Address-3", 6);
        assertEquals(0, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldBeTrueWhenParcelIsExpired() {
        PerishableParcel perishableParcel =
                new PerishableParcel("Perishable parcel 4", 1, "Address-4", 7, 1);
        assertTrue(perishableParcel.isExpired(10, 3));
    }

    @Test
    public void shouldBeFalseWhenParcelNotExpired() {
        PerishableParcel perishableParcel =
                new PerishableParcel("Perishable parcel 4", 10, "Address-4", 7, 10);
        assertFalse(perishableParcel.isExpired(1, 7));
    }

    @Test
    public void shouldBeAddParcelToBoxWhenWeightLimitNotExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(100);
        StandardParcel standardParcel =
                new StandardParcel("Standart parcel 1", 20, "Address-1", 4);
        box.addParcel(standardParcel);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    public void shouldNotAddParcelToBoxWhenWeightLimitExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel standardParcel =
                new StandardParcel("Standart parcel 1", 20, "Address-1", 4);
        box.addParcel(standardParcel);
        assertEquals(0, box.getAllParcels().size());
    }
}