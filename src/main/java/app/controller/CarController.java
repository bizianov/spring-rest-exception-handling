package app.controller;

import app.model.Car;
import app.model.SearchCarRequest;
import app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cars")
public class CarController extends BaseApiController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/create/{isDefault}")
    public ResponseEntity<Car> create(@PathVariable("isDefault") boolean isDefault) {
        return apiCreate(() -> carService.create(isDefault));
    }

    @PutMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> find(@Valid @RequestBody SearchCarRequest searchCarRequest, BindingResult bindingResult) {
        return apiFind(() -> carService.find(searchCarRequest.getBrand(), searchCarRequest.getModel()), bindingResult);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> save(@Valid @RequestBody Car car, BindingResult bindingResult) {
        return apiValidate(() -> carService.validate(car), bindingResult);
    }
}