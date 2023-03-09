package ru.akhmetov.TestEmbedika.models;

import java.time.LocalDate;

public class Statistic {
    private final long quantity;
    private final LocalDate dateFirstRecord;
    private final LocalDate dateLastRecord;

    public Statistic(long quantity, LocalDate dateFirstRecord, LocalDate dateLastRecord) {
        this.quantity = quantity;
        this.dateFirstRecord = dateFirstRecord;
        this.dateLastRecord = dateLastRecord;
    }

    public long getQuantity() {
        return quantity;
    }

    public LocalDate getDateFirstRecord() {
        return dateFirstRecord;
    }

    public LocalDate getDateLastRecord() {
        return dateLastRecord;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "quantity=" + quantity +
                ", dateFirstRecord=" + dateFirstRecord +
                ", dateLastRecord=" + dateLastRecord +
                '}';
    }
}
