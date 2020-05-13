package pl.lapinski.ksb2vaadin.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lapinski.ksb2vaadin.model.Car;
import pl.lapinski.ksb2vaadin.service.CarService;

import java.util.HashSet;

@Route("mod-car")
public class CarModifyGui extends VerticalLayout {

    private CarService carService;

    @Autowired
    public CarModifyGui(CarService carService) {
        this.carService = carService;

        HashSet<Long> ids = new HashSet<>();
        for (Car car : carService.getCarList()){
            ids.add(car.getId());
        }

        ComboBox<Long> comboBoxId = new ComboBox<>("Id");
        comboBoxId.setItems(ids);
        TextField textFieldMark = new TextField();
        textFieldMark.setPlaceholder("Mark");

        TextField textFieldModel = new TextField();
        textFieldModel.setPlaceholder("Model");

        Dialog dialogCar = new Dialog();
        dialogCar.setWidth("400px");
        dialogCar.setHeight("150px");

        Button buttonMod = new Button("Modify car");
        buttonMod.addClickListener(buttonClick -> {
            Car modCar = new Car(comboBoxId.getValue(), textFieldMark.getValue(),
                    textFieldModel.getValue());
            dialogCar.removeAll();
            if (carService.changeCar(modCar)) {
                dialogCar.add(new Label("Modification successful"));
            } else {
                dialogCar.add(new Label("Error"));
            }
            dialogCar.open();
        });

        Button buttonModField = new Button("Modify car field");
        buttonModField.addClickListener(buttonClickEvent -> {
            dialogCar.removeAll();
            if (carService.changeField(comboBoxId.getValue().longValue(), textFieldMark.getValue(), textFieldModel.getValue())){
                dialogCar.add(new Label("Modification successful"));
            } else {
                dialogCar.add(new Label("Error"));
            }
            dialogCar.open();
        });

        add(comboBoxId, textFieldMark, textFieldModel, buttonMod, buttonModField);
    }
}
