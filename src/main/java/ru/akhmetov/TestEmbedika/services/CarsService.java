package ru.akhmetov.TestEmbedika.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akhmetov.TestEmbedika.models.Car;
import ru.akhmetov.TestEmbedika.models.Statistic;
import ru.akhmetov.TestEmbedika.models.Status;
import ru.akhmetov.TestEmbedika.repositories.CarsRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class CarsService {
    private final CarsRepository carsRepository;

    @Autowired
    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }
    public List<Car> findAll(int typeOfSort) {
        if (typeOfSort == 0)
            return carsRepository.findAll(Sort.by("model"));
        if (typeOfSort == 1)
            return carsRepository.findAll(Sort.by("color"));
        if (typeOfSort == 2)
            return carsRepository.findAll(Sort.by("year"));
        if (typeOfSort == 3)
            return carsRepository.findAll(Sort.by("date_of_creation"));
        return carsRepository.findAll();
    }
    public Optional<Car> findById(int id) {
        return carsRepository.getCarById(id);
    }
    @Transactional
    public Status save(Car car) {
        Optional<Car> foundedCar = carsRepository.getCarByStateNumber(car.getStateNumber());
        if (foundedCar.isPresent()) {
            return Status.AlreadyExist;
        }
        car.setDateOfCreation(LocalDate.now());
        Car savedCar = carsRepository.save(car);
        if (savedCar.equals(car)) {
            return Status.OK;
        }
        return Status.Error;
    }
    @Transactional
    public Status delete(int id) {
        Optional<Car> carBefore = carsRepository.getCarById(id);
        if (carBefore.isEmpty()) {
            return Status.NotFound;
        }
        carsRepository.deleteById(id);
        Optional<Car> carAfter = carsRepository.getCarById(id);
        if (carAfter.isPresent()) {
            return Status.Error;
        }
        return Status.OK;
    }
    public List<Car> search(String stateNumber, String color, String model, Integer year, LocalDate dateOfCreation) {
        Stream<Car> stream;
        if (stateNumber != null) {
            stream = carsRepository.getCarByStateNumber(stateNumber).stream();
        } else if (color != null) {
            stream = carsRepository.getCarsByColor(color).stream();
        } else if (model != null) {
            stream = carsRepository.getCarsByModel(model).stream();
        } else if (year != null) {
            stream = carsRepository.getCarsByYear(year).stream();
        } else if (dateOfCreation != null) {
            stream = carsRepository.getCarsByDateOfCreation(dateOfCreation).stream();
        } else {
            return carsRepository.findAll();
        }
        if (color != null) {
            stream = stream.filter(car -> car.getColor().equals(color));
        }
        if (model != null) {
            stream = stream.filter(car -> car.getModel().equals(model));
        }
        if (year != null) {
            stream = stream.filter(car -> car.getYear() == year);
        }
        if (dateOfCreation != null) {
            stream = stream.filter(car -> car.getDateOfCreation().equals(dateOfCreation));
        }
        return stream.toList();
    }

    public Statistic getStats() {
        List<Car> carList = carsRepository.findAll();
        long counter = carList.size();
        Comparator<Car> comparator = (o1, o2) -> {
            int cmp = (o1.getDateOfCreation().getYear() - o2.getDateOfCreation().getYear());
            if (cmp == 0) {
                cmp = (o1.getDateOfCreation().getMonthValue() - o2.getDateOfCreation().getMonthValue());
                if (cmp == 0) {
                    cmp = (o1.getDateOfCreation().getDayOfMonth() - o2.getDateOfCreation().getDayOfMonth());
                }
            }
            return cmp;
        };
        LocalDate dateFirstRecord = carList.stream().min(comparator).get().getDateOfCreation();
        LocalDate dateLastRecord = carList.stream().max(comparator).get().getDateOfCreation();
        return new Statistic(counter, dateFirstRecord, dateLastRecord);
    }
}
