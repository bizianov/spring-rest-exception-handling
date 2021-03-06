package app.service;

import app.exception.CarNotFoundException;
import app.exception.NoCustomCarExistsException;
import app.model.Car;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    public static final String DEFAULT_CAR_BRAND = "Toyota";
    public static final String DEFAULT_CAR_MODEL = "Camry";
    public static final String DEFAULT_CAR_COLOR = "Black";
    public static final Integer DEFAULT_MAX_SPEED = 240;
    public static final Integer MAX_POSSIBLE_SPEED = 270;

    @Override
    public Car create(boolean isDefault) {
        if (isDefault) {
            return getDefaultCar();
        } else {
            throw new NoCustomCarExistsException("Only default car exists");
        }
    }

    @Override
    public Car find(String brand, String model) {
        if (brand.equals(DEFAULT_CAR_BRAND) && model.equals(DEFAULT_CAR_MODEL)) {
            return getDefaultCar();
        } else {
            throw new CarNotFoundException("No car found");
        }
    }

    @Override
    public boolean validate(Car car) {
        return car.getMaxSpeed() < MAX_POSSIBLE_SPEED;
    }

    private Car getDefaultCar() {
        return Car.builder()
                .brand(DEFAULT_CAR_BRAND)
                .model(DEFAULT_CAR_MODEL)
                .color(DEFAULT_CAR_COLOR)
                .maxSpeed(DEFAULT_MAX_SPEED)
                .build();
    }
}