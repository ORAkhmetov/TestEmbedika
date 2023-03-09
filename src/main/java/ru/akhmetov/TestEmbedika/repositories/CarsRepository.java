package ru.akhmetov.TestEmbedika.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akhmetov.TestEmbedika.models.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Car, Integer> {
    Optional<Car> getCarByStateNumber(String stateNumber);
    List<Car> getCarsByColor(String color);
    List<Car> getCarsByModel(String model);
    List<Car> getCarsByYear(int year);
    List<Car> getCarsByDateOfCreation(LocalDate dateOfCreation);
    Optional<Car> getCarById(int id);
   /* List<Car> getCarsByColorAndModel(String color, String model);
    List<Car> getCarsByColorAndYear(String color, int year);
    List<Car> getCarsByModelAndYear(String model, int year);
    List<Car> getCarsByColorAndModelAndYear (String color, String model, int year);*/
}
