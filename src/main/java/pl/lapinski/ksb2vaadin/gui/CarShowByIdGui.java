package pl.lapinski.ksb2vaadin.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lapinski.ksb2vaadin.model.Car;
import pl.lapinski.ksb2vaadin.service.CarService;

import java.util.Optional;

@Route("search")
public class CarShowByIdGui extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarShowByIdGui(CarService carService) {
        this.carService = carService;

        NumberField numberFieldId = new NumberField("Type car ID here");
        Button searchButton = new Button("Search Car");

        Grid<Car> grid = new Grid<>(Car.class);
        grid.setColumns("id", "mark", "model");

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        searchButton.addClickListener(buttonClickEvent -> {
            Optional<Car> carOptional = carService.showCarById(numberFieldId.getValue().longValue());
            dialogCar.removeAll();
            if (carOptional.isPresent()) {
                grid.setItems(carOptional.get());
                add(grid);
            } else {
                dialogCar.add(new Label("There is no car with this ID"));
                dialogCar.open();
            }
        });
        add(numberFieldId, searchButton);
    }
}
