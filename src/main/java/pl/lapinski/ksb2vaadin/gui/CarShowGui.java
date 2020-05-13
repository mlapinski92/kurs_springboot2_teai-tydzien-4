package pl.lapinski.ksb2vaadin.gui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.lapinski.ksb2vaadin.service.CarService;
import pl.lapinski.ksb2vaadin.model.Car;

@Route("show-cars")
public class CarShowGui extends VerticalLayout {

    private CarService carService;

    public CarShowGui(CarService carService) {
        this.carService = carService;

        Grid<Car> grid = new Grid<>(Car.class);
        grid.setItems(carService.getCarList());

        add(grid);
    }
}
