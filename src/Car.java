/*
 * Created by Alexey Yarkov on 25.10.17
 * Copyright © 2017 Alexey Yarkov. All rights reserved.
 */

public class Car {
    private int ID, Year, V, Price;
    private String Model, Country;

    public Car() {
        this.ID = 0;
        Model = " ";
        Country = " ";
        Year = 0;
        V = 0;
        Price = 0;
    }

    public Car(int id, String model, String country, int year, int v, int price) {
        ID = id;
        Model = model;
        Country = country;
        Year = year;
        V = v;
        Price = price;
    }

    public void setCar(int id, String model, String country, int year, int v, int price) {
        ID = id;
        Model = model;
        Country = country;
        Year = year;
        V = v;
        Price = price;
    }

    public void setCar(Car T) {
        ID = T.getID();
        Model = T.getModel();
        Country = T.getCountry();
        Year = T.getYear();
        V = T.getV();
        Price = T.getPrice();
    }

    public void getCar() {
        System.out.println("ID: " + ID);
        System.out.println("марка автомобиля: " + Model);
        System.out.println("страна-производитель: " + Country);
        System.out.println("год выпуска: " + Year);
        System.out.println("объем двигателя: " + V);
        System.out.println("стоимость: " + Price + "\n");
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    @Override
    public String toString() {
        return "ID=" + getID() +
                ", Model='" + getModel() + '\'' +
                ", Country='" + getCountry() + "\'" +
                ", Year=" + getYear() +
                ", V=" + getV() +
                ", Price=" + getPrice();
    }
}
