/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashingtext;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author erso
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label funLabel;
    @FXML
    private Button startBtn;
    @FXML
    private Button stopBtn;
    @FXML
    private ToggleGroup interval;

    private long selectedInterval = 200;
    private Thread thread;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startBtn.setOnAction(event -> {
            if (thread == null) {
                thread = new Thread(() -> {
                    try {
                        while (true) {
                            Platform.runLater(() -> funLabel.setText(funLabel.getText().trim().length() == 0 ? "Programming is fun" : ""));
                            Thread.sleep(selectedInterval);
                        }
                    } catch (InterruptedException ex) {
                    }
                });
                thread.setDaemon(true);
                thread.start();
            }
        });
        stopBtn.setOnAction(event -> {
            if (thread != null) {
                thread.interrupt();
                thread = null;
                funLabel.setText("Welcome");
            }
        });
        interval.selectedToggleProperty().addListener((observable, oldValue, newValue) -> selectedInterval = Long.parseLong(((ToggleButton) newValue).getText().split(" ")[0]));
    }
}
