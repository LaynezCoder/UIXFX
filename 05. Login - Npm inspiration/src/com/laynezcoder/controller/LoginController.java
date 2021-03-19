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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author LaynezCoder
 */
public class LoginController implements Initializable {

    private final String IMAGES_PACKAGES = "/resources/com/laynezcoder/images/";
    
    private final String WINKED_EYES = IMAGES_PACKAGES + "winked-eyes.png";
    
    private final String BODY = IMAGES_PACKAGES + "body.png";
    
    private final String EYES = IMAGES_PACKAGES + "eyes.png";

    @FXML
    private AnchorPane blind;

    @FXML
    private ImageView body;

    @FXML
    private ImageView eyes;

    @FXML
    private AnchorPane bearContainer;

    @FXML
    private PasswordField password;

    @FXML
    private Pane background;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBlind();
        createBear();
        setClip();
        eyesAnimation();
        transitionToBlind();
        setAnimation();
    }

    private void createBear() {
        Image bodyImage = new Image(BODY, 200, 200, true, true);
        body.setImage(bodyImage);

        Image eyesImage = new Image(EYES, 62, 35, true, true);
        eyes.setImage(eyesImage);
    }

    private void eyesAnimation() {
        bearContainer.setOnMouseEntered(ev -> {
            Image winkedEyesImage = new Image(WINKED_EYES, 62, 35, true, true);
            eyes.setImage(winkedEyesImage);
        });

        bearContainer.setOnMouseExited(ev -> {
            Image eyesImage = new Image(EYES, 62, 35, true, true);
            eyes.setImage(eyesImage);
        });
    }

    private void setClip() {
        Circle clip = new Circle(90);
        clip.setCenterX(body.getFitWidth() / 2);
        clip.setCenterY(body.getFitWidth() / 2);
        bearContainer.setClip(clip);
    }

    private void initBlind() {
        background.setVisible(true);
        blind.setVisible(true);
        blind.setTranslateY(-155);
    }

    private void transitionToBlind() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.0);
        background.setEffect(colorAdjust);

        TranslateTransition up = new TranslateTransition(Duration.millis(200), blind);
        TranslateTransition down = new TranslateTransition(Duration.millis(200), blind);

        blind.setOnMouseClicked(ev -> {
            blind.requestFocus();
            if (blind.getTranslateY() == -155) {
                darkenBackground(colorAdjust);
                down.setFromY(-155);
                down.setByY(125);
                down.play();
            } else {
                iluminateBackground(colorAdjust);
                up.setFromY(-30);
                up.setByY(-125);
                up.play();
            }
        });

        password.focusedProperty().addListener((o, oldValue, newValue) -> {
            if (blind.getTranslateY() == -30 && !oldValue) {
                return;
            }

            if (newValue) {
                darkenBackground(colorAdjust);
                down.setFromY(-155);
                down.setByY(125);
                down.play();
            } else {
                iluminateBackground(colorAdjust);
                up.setFromY(-30);
                up.setByY(-125);
                up.play();
            }
        });
    }

    private void setAnimation() {
        bearAnimation(body);
        bearAnimation(eyes);
    }

    private void bearAnimation(Node node) {
        TranslateTransition up = new TranslateTransition(Duration.millis(600), node);
        up.setCycleCount(TranslateTransition.INDEFINITE);
        up.setAutoReverse(true);
        up.setByY(-4);
        up.play();

        TranslateTransition down = new TranslateTransition(Duration.millis(1200), node);
        down.setCycleCount(TranslateTransition.INDEFINITE);
        down.setAutoReverse(true);
        down.setByY(3);
        down.play();
    }

    private void darkenBackground(ColorAdjust colorAdjust) {
        Timeline fadeInTimeline = new Timeline(new KeyFrame(Duration.seconds(0),
                new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(colorAdjust.brightnessProperty(), -0.3, Interpolator.LINEAR)
                ));
        fadeInTimeline.setCycleCount(1);
        fadeInTimeline.setAutoReverse(false);
        fadeInTimeline.play();
    }

    private void iluminateBackground(ColorAdjust colorAdjust) {
        Timeline fadeOutTimeline = new Timeline(new KeyFrame(Duration.seconds(0),
                new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(colorAdjust.brightnessProperty(), 0, Interpolator.LINEAR)
                ));
        fadeOutTimeline.setCycleCount(1);
        fadeOutTimeline.setAutoReverse(false);
        fadeOutTimeline.play();
    }
}
