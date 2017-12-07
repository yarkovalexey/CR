/*
 * Created by Alexey Yarkov on 25.10.17
 * Copyright © 2017 Alexey Yarkov. All rights reserved.
 */

import java.util.Arrays;

public class CarShop {
    private int N;
    private Car cars[];
    private int s;

    public CarShop() {
        s = 0;
        N = 1;
        cars = new Car[N];
    }

    private Car[] enlargeCar() {
        if (s >= N) {
            Car tmp[] = new Car[N];
            System.arraycopy(cars, 0, tmp, 0, cars.length);
            N += 1;
            cars = new Car[N];
            System.arraycopy(tmp, 0, cars, 0, tmp.length);
        }
        return cars;
    }

    public void addCar(int id, String m, String c, int y, int v, int p) {
        enlargeCar();
        Car A = new Car(id, m, c, y, v, p);
        cars[s] = A;
        s++;
    }

    public void addCar(Car T) {
        enlargeCar();
        cars[s] = T;
        s++;
    }

    public Car[] getCarShop() {
        return cars;
    }

    public void editCarShop(int pos, int id, String m, String c, int y, int v, int p) {
        cars[pos].setCar(id, m, c, y, v, p);
    }

    public void editCarShop(int pos, Car T) {
        cars[pos].setCar(T);
    }

    public String findCar(int pos) {
        String res = "";
        for (int i = 0; i < s; i++) {
            if (i == pos) {
                res = cars[i].toString();
            }
        }
        return res;
    }

    public void delCar(int pos) {
        for (int i = pos; i < s - 1; i++) {
            cars[i] = cars[i + 1];
        }
        s--;
    }

    public void bubbleSortCar() {
        for (int i = s - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (cars[j].getID() > cars[j + 1].getID()) {
                    Car t = cars[j];
                    cars[j] = cars[j + 1];
                    cars[j + 1] = t;
                }
            }
        }
    }

    public int getS() {
        return s;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < s; i++) res += cars[i].toString() + "\n";
        return res;
    }
}
