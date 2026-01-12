package src;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

public class SettingsSliderModule extends HBox {
    private Slider slider;

    SettingsSliderModule(String name, int min, int max, int current, double spcaing) {
        super(spcaing);
        Label nameLabel = new Label(name);
        Label valueLabel = new Label(current + "");
        HBox sliderContainer = new HBox();
        slider = new Slider(min, max, current);

        slider.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            slider.lookupAll(".thumb").forEach(node -> {
                node.setLayoutX(Math.round(node.getLayoutX()));
            });
        });

        nameLabel.setMinWidth(100);
        valueLabel.setMinWidth(100);
        slider.setPrefWidth(200);
        valueLabel.setTextAlignment(TextAlignment.JUSTIFY);

        sliderContainer.getChildren().add(slider);
        sliderContainer.setAlignment(Pos.CENTER_RIGHT);
        setAlignment(Pos.CENTER);

        getChildren().add(nameLabel);
        getChildren().add(valueLabel);
        getChildren().add(sliderContainer);

        slider.valueProperty().addListener((ObservableValue, oldValue, newValue) -> {
            valueLabel.setText(String.valueOf(newValue.intValue()));
        });
    }

    public Slider getSlider() {
        return getSlider();
    }

    public DoubleProperty getSliderProperty() {
        return slider.valueProperty();
    }

}
