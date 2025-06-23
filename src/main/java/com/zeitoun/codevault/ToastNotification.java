package com.zeitoun.codevault;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class ToastNotification {
    private final Text pane;


    public ToastNotification(StackPane root) {
        this.pane = new Text();
        root.getChildren().add(pane);
    }

    public ToastNotification(String Message, StackPane root) {
        this.pane = new Text(Message);
        root.getChildren().add(pane);
    }

    public void showAndHide(long duration) {
        pane.setVisible(true);
        //Schedule the Visibility for 1000ms
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // message disappears after duration
                        pane.setVisible(false);
                    }
                });
            }
        }, duration);
    }

    public void setVisible(boolean value) {
        pane.setVisible(value);
    }

    public void setText(String message) {
        pane.setText(message);
    }

    public Text getPane() {
        return pane;
    }
}
