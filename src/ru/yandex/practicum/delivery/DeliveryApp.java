package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> allTrackable = new ArrayList<>();
    private static final ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(300);
    private static final ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(200);
    private static final ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(100);


    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatus();
                    break;
                case 5:
                    showParcelsMenu();
                    int com = Integer.parseInt(scanner.nextLine());
                    printParcelsByBoxType(com);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Рассчитать стоимость доставки");
        System.out.println("4 - Мониторинг доставки");
        System.out.println("5 - Содержимое посылок");
        System.out.println("0 — Завершить");
    }

    private static void showParcelsMenu() {
        System.out.println("Укажите тип посылки:");
        System.out.println("1 - Стандартная посылка");
        System.out.println("2 - Посылка с сроком годности");
        System.out.println("3 - Хрупкая посылка");
        System.out.println("4 - Вернуться назад");
    }

    private static void showTypesOfParcel() {
        System.out.println("Укажите тип посылки:");
        System.out.println("1 - Стандартная посылка");
        System.out.println("2 - Хрупкая посылка");
        System.out.println("3 - Посылка с сроком годности");
    }

    private static void addParcel() {
        showTypesOfParcel();
        int type = Integer.parseInt(scanner.nextLine());
        if (type < 1 || type > 3) {
            System.out.println("Неизвестная команда");
            return;
        }

        String description;
        int weigth;
        String address;
        int sendDay;
        System.out.println("Описание посылки:");
        description = scanner.nextLine();
        System.out.println("Вес посылки:");
        weigth = Integer.parseInt(scanner.nextLine());
        System.out.println("Адрес отправки:");
        address = scanner.nextLine();
        System.out.println("День отправления:");
        sendDay = Integer.parseInt(scanner.nextLine());

        switch (type) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weigth, address, sendDay);
                allParcels.add(standardParcel);
                standardParcelBox.addParcel(standardParcel);
                break;
            case 2:
                FragileParcel fragileParcel  = new FragileParcel(description, weigth, address, sendDay);
                allParcels.add(fragileParcel);
                fragileParcelBox.addParcel(fragileParcel);
                allTrackable.add(fragileParcel);
                break;
            case 3:
                System.out.println("Введите срок годности посылки:");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description, weigth, address, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableParcelBox.addParcel(perishableParcel);
                break;
        }
    }

    private static void reportStatus() {
        if (allTrackable.isEmpty()) {
            System.out.println("Хрупких посылок нет");
            return;
        }
        System.out.println("Новое местонахождение посылки");
        String status = scanner.nextLine();
        for (Trackable parcel : allTrackable) {
            parcel.reportStatus(status);
        }
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("В базе нет посылок");
            return;
        }
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        double sum = 0;
        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех отправлений: " + sum);
    }

    private static void printParcelsByBoxType(int type) {
        switch (type) {
            case 1:
                printParcelsByBox(standardParcelBox);
                break;
            case 2:
                printParcelsByBox(fragileParcelBox);
                break;
            case 3:
                printParcelsByBox(perishableParcelBox);
                break;
            default:
                System.out.println("Введена неизвестная команда");
        }
    }

    private static void printParcelsByBox(ParcelBox<? extends Parcel> box) {
        int i = 1;
        if (box.isEmpty()) {
            System.out.println("Данная коробка пуста");
            return;
        }
        for (Parcel parcel : box.getAllParcels()) {
            System.out.printf("Parcel %d : %s \n", i++, parcel.getDescription());
        }
    }
}