package com.zeitoun.codevault.shared;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A CustomListView for implementing the folder pane and snippets pane views
 * Is editable and allows
 */
public class CustomListView extends ListCell<String> {
    private final Image icon;
    private final TextField textField;
    private final HBox hbox;


    public CustomListView(Image icon, TextField textField) {
        this.icon = icon;
        ImageView imageView = new ImageView(icon);
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(heightProperty().multiply(0.5));

        this.textField = textField;
        this.textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                commitEdit(textField.getText());
            }
        });

        hbox = new HBox(imageView, textField);
        hbox.setAlignment(Pos.CENTER_LEFT);

        setEditable(true);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else if(isEditing()) {
            setText(null);
            setGraphic(hbox);
        } else {
            final ImageView imageView = new ImageView(icon);
            imageView.setPreserveRatio(true);
            imageView.fitHeightProperty().bind(heightProperty().multiply(0.5));
            setText(item);
            setGraphic(imageView);
        }
    }

    @Override
    public void startEdit() {
        super.startEdit();
        setGraphic(hbox);
        setText(null);
        textField.setText(getItem());
        textField.requestFocus();
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        final ImageView imageView = new ImageView(icon);
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(heightProperty().multiply(0.5));
        setText(getItem());
        setGraphic(imageView);
    }

}
