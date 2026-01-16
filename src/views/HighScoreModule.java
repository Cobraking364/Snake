package src;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;

public class HighScoreModule extends HBox {
    

    HighScoreModule(String name, int current, double spacing) {
        super(spacing);
        Label nameLabel = new Label(name);
        Label valueLabel = new Label(current + "");     
        HBox valueContainer = new HBox();
        HBox nameContainer= new HBox();
        nameLabel.setId("highscore-label");
        valueLabel.setId("highscore-label");

        nameLabel.setMinWidth(80);
        valueLabel.setMinWidth(80);
        valueLabel.setAlignment(Pos.CENTER_RIGHT);
        

        valueContainer.getChildren().add(valueLabel);
        nameContainer.getChildren().add(nameLabel);
        
        valueContainer.setAlignment(Pos.CENTER);
        //nameContainer.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);

        getChildren().add(nameContainer);
        getChildren().add(valueContainer);
        
    }

}