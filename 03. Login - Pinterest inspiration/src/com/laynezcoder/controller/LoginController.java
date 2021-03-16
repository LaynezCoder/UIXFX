/**
 * Copyright (c) 2021 LaynezCode
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.laynezcoder.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author LaynezCoder
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField showPassword;

    @FXML
    private FontAwesomeIconView icon;

    private double x, y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showPassword();
    }

    @FXML
    private void dragged(MouseEvent ev) {
        Stage stage = (Stage) password.getScene().getWindow();
        stage.setX(ev.getScreenX() - x);
        stage.setY(ev.getScreenY() - y);
    }

    @FXML
    private void pressed(MouseEvent ev) {
        x = ev.getSceneX();
        y = ev.getSceneY();
    }

    private void showPassword() {
        showPassword.managedProperty().bind(icon.pressedProperty());
        showPassword.visibleProperty().bind(icon.pressedProperty());
        showPassword.textProperty().bindBidirectional(password.textProperty());

        password.managedProperty().bind(icon.pressedProperty().not());
        password.visibleProperty().bind(icon.pressedProperty().not());

        icon.pressedProperty().addListener((o, oldValue, newValue) -> {
            if (newValue) {
                icon.setIcon(FontAwesomeIcon.EYE);
            } else {
                icon.setIcon(FontAwesomeIcon.EYE_SLASH);
            }
        });
    }
}
