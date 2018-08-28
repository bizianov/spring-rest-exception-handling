package app.service;

import app.model.Car;

public interface CarService {

    Car create(boolean isDefault);

    Car find(String brand, String model);

    boolean save(Car car);
}