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
package com.laynezcoder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 *
 * @author LaynezCoder
 */
public class Main extends Application {

    private final String LOGIN = "/com/laynezcoder/fxml/main-view.fxml";

    private final String ICON = "/resources/com/laynezcoder/images/icon.png";

    private final String TITLE = "Npm Login";

    @Override
    public void start(Stage stage) {
        try {
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            Parent root = FXMLLoader.load(getClass().getResource(LOGIN));
            stage.getIcons().add(new Image(ICON));
            Scene scene = new Scene(root);
            stage.setTitle(TITLE);
            stage.setScene(scene);
            stage.show();

            root.setOnKeyPressed(ev -> {
                if (ev.getCode().equals(KeyCode.F11)) {
                    stage.setFullScreen(!stage.isFullScreen());
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
