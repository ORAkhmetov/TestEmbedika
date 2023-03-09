package ru.akhmetov.TestEmbedika.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akhmetov.TestEmbedika.models.Car;
import ru.akhmetov.TestEmbedika.models.Statistic;
import ru.akhmetov.TestEmbedika.models.Status;
import ru.akhmetov.TestEmbedika.services.CarsService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CarsController {

    private final CarsService carsService;

    @Autowired
    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/cars")
    public List<Car> findAll(@RequestParam(value = "stateNumber", required = false) String stateNumber,
                             @RequestParam(value = "model", required = false) String model,
                             @RequestParam(value = "color", required = false) String color,
                             @RequestParam(value = "year", required = false) Integer year,
                             @RequestParam(value = "dateOfCreation", required = false) LocalDate dateOfCreation,
                             @RequestParam(value = "sort_by_model", required = false) boolean sortByModel,
                             @RequestParam(value = "sort_by_color", required = false) boolean sortByColor,
                             @RequestParam(value = "sort_by_year", required = false) boolean sortByYear,
                             @RequestParam(value = "sort_by_dateOfCreation", required = false) boolean sortByDateOfCreation) {
        if (sortByModel) {
            return carsService.findAll(0);
        } else if (sortByColor) {
            return carsService.findAll(1);
        } else if (sortByYear) {
            return carsService.findAll(2);
        } else if (sortByDateOfCreation) {
            return carsService.findAll(3);
        } else {
            return carsService.search(stateNumber, color, model, year, dateOfCreation);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<Object> addCar(@RequestBody Car car) {
        Status status = carsService.save(car);
        if (status == Status.OK) {
            return ResponseEntity.status(200).build();
        } else if (status == Status.AlreadyExist) {
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") int id) {
        Status status = carsService.delete(id);
        if (status == Status.OK) {
            return ResponseEntity.status(204).build();
        } else if (status == Status.NotFound) {
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/cars/stats")
    public Statistic stats() {
        return carsService.getStats();
    }
}
