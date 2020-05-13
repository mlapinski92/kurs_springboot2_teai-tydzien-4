package pl.lapinski.ksb2vaadin.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Car {
    private long id;
    private String mark;
    private String model;

    public Car(long id, String mark, String model) {
        this.id = id;
        this.mark = mark;
        this.model = model;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
