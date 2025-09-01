package com.zeitoun.codevault.shared;

import atlantafx.base.controls.PasswordTextField;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class GithubSettingsDialog extends Dialog<GithubSettingsResult> {
    private final PasswordTextField personalAccessTokenField;
    private final RadioButton publicButton;
    private final RadioButton secretButton;
    private final ToggleGroup toggleGroup;
    private final ButtonType applyButton;
    private final ButtonType cancelButton;


    public GithubSettingsDialog() {
        super();
        personalAccessTokenField = new PasswordTextField();
        personalAccessTokenField.setPrefWidth(250);
        publicButton = new RadioButton("Public");
        secretButton = new RadioButton("Secret");
        toggleGroup = new ToggleGroup();
        publicButton.setToggleGroup(toggleGroup);
        secretButton.setToggleGroup(toggleGroup);


        applyButton = ButtonType.APPLY;
        cancelButton = ButtonType.CANCEL;

        this.getDialogPane().setContent(new VBox(personalAccessTokenField, new HBox(secretButton, publicButton)));
        this.getDialogPane().getButtonTypes().addAll(applyButton, cancelButton);

        setResultConverter(new Callback<ButtonType, GithubSettingsResult>() {
            @Override
            public GithubSettingsResult call(ButtonType buttonType) {
                if(buttonType == applyButton) {
                    RadioButton selectedButton = (RadioButton) toggleGroup.getSelectedToggle();
                    return  new GithubSettingsResult(personalAccessTokenField.getPassword(), selectedButton.getText());
                } else {
                    return null;
                }
            }
        });

    }

}
