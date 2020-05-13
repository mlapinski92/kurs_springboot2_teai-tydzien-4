package pl.lapinski.ksb2vaadin.service;

import org.springframework.stereotype.Service;
import pl.lapinski.ksb2vaadin.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    List<Car> carList;
    public CarService(){
        carList = new ArrayList<>();
        carList.add(new Car(1000, "Fiat","126p"));
        carList.add(new Car(1001, "BMW","X1"));
        carList.add(new Car(1002, "Honda","Civic"));
    }

    public List<Car> getCarList() {
        return carList;
    }

    public Optional<Car> showCarById(long id){
        return carList.stream().filter(car -> car.getId() == id).findFirst();
    }

    public boolean addCar(Car newCar){
        if (newCar.getMark() != null && !newCar.getModel().isEmpty()){
            return carList.add(newCar);
        }
        return false;
    }

    public boolean changeCar(Car newCar) {
        Optional<Car> firstCar = carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if (firstCar.isPresent()) {
            Car modCar = firstCar.get();
            modCar.setId(newCar.getId());
            modCar.setMark(newCar.getMark());
            modCar.setModel(newCar.getModel());
            return true;
        }
        return false;
    }

    public boolean changeField(long id, String mark, String model) {
        Optional<Car> firstCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (firstCar.isPresent()) {
            Car modCar = firstCar.get();
            if (mark != null) {
                modCar.setMark(mark);
            }
            if (!model.isEmpty()) {
                modCar.setModel(model);
            }
            return true;
        }
        return false;
    }

    public boolean deleteCar(long id) {
        Optional<Car> firstCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (firstCar.isPresent()) {
            carList.remove(firstCar.get());
            return true;
        }
        return false;
    }
}
