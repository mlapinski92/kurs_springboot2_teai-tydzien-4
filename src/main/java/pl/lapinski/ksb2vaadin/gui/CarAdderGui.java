package pl.lapinski.ksb2vaadin.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lapinski.ksb2vaadin.service.CarService;
import pl.lapinski.ksb2vaadin.model.Car;

import java.util.Optional;

@Route("add-car")
public class CarAdderGui extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarAdderGui(CarService carService) {
        this.carService = carService;

        NumberField numberFieldId = new NumberField("ID");
        TextField textFieldMark = new TextField("Mark");
        TextField textFieldModel = new TextField("Model");

        Button buttonAdd = new Button("Add car");

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        buttonAdd.addClickListener(buttonClickEvent -> {
            Optional<Car> firstCar = carService.showCarById(numberFieldId.getValue().longValue());
            dialogCar.removeAll();

            if (firstCar.isPresent()) {
                dialogCar.add(new Label("There is already a car with this ID"));
            } else {
                Car car = new Car(numberFieldId.getValue().longValue(), textFieldMark.getValue(), textFieldModel.getValue());
                if (carService.addCar(car)) {
                    dialogCar.add(new Label("Car added succesfully"));
                } else {
                    dialogCar.add(new Label("Error"));
                }
            }
            dialogCar.open();
        });
        add(numberFieldId, textFieldMark, textFieldModel, buttonAdd);
    }
}
