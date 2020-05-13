package pl.lapinski.ksb2vaadin.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lapinski.ksb2vaadin.model.Car;
import pl.lapinski.ksb2vaadin.service.CarService;

import java.util.HashSet;

@Route("delete-car")
public class CarDeleteGui extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarDeleteGui(CarService carService) {
        this.carService = carService;

        HashSet<Long> ids = new HashSet<>();
        for (Car car : carService.getCarList()){
            ids.add(car.getId());
        }

        ComboBox<Long> comboBoxId = new ComboBox<>("Id");
        comboBoxId.setItems(ids);

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        Button buttonRemove = new Button("Remove car");
        buttonRemove.addClickListener(buttonClick -> {
            dialogCar.removeAll();
            if (carService.deleteCar(comboBoxId.getValue())) {
                dialogCar.add(new Label("Car removed"));
            } else {
                dialogCar.add(new Label("No car with this id"));
            }
            dialogCar.open();
        });
        add(comboBoxId, buttonRemove);
    }
}
