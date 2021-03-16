/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laynezcoder.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author LaynezCoder
 */
public class LoginController implements Initializable {

    private double x, y;

    @FXML
    private ImageView logo;
    
    @FXML
    private Hyperlink link;
    
    @FXML
    private TabPane tabPane;

    private final String LOGO = "/resources/com/laynezcoder/images/logo.png";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLogo();
        redirectToLoginView();
    }

    private void setLogo() {
        logo.setImage(new Image(LOGO, 200, 500, true, true));
    }
    
    private void redirectToLoginView() {
        link.setOnMouseClicked(ev -> {
            tabPane.getSelectionModel().select(0);
        });
    }

    @FXML
    private void dragged(MouseEvent ev) {
        Stage stage = (Stage) logo.getScene().getWindow();
        stage.setX(ev.getScreenX() - x);
        stage.setY(ev.getScreenY() - y);
    }

    @FXML
    private void pressed(MouseEvent ev) {
        x = ev.getSceneX();
        y = ev.getSceneY();
    }   
}
