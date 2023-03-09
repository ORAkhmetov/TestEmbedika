package ru.akhmetov.TestEmbedika.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model")
    @NotEmpty(message = "Модель не должна быть пустой")
    private String model;

    @Column(name = "state_number")
    @NotEmpty(message = "Госномер не должен быть пустым")
    private String stateNumber;

    @Column(name = "color")
    @NotEmpty(message = "Цвет не должен быть пустым")
    private String color;

    @Column(name = "year")
    @Min(value = 1900, message = "Год не должен быть меньше 1900")
    private int year;

    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (getId() != car.getId()) return false;
        if (getYear() != car.getYear()) return false;
        if (getModel() != null ? !getModel().equals(car.getModel()) : car.getModel() != null) return false;
        if (getStateNumber() != null ? !getStateNumber().equals(car.getStateNumber()) : car.getStateNumber() != null)
            return false;
        return getColor() != null ? getColor().equals(car.getColor()) : car.getColor() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + (getStateNumber() != null ? getStateNumber().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + getYear();
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", stateNumber='" + stateNumber + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                '}';
    }
}
