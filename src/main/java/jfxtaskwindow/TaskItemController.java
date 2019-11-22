/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxtaskwindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.Constants;

/**
 * FXML Controller class
 *
 * @author Too
 */
public class TaskItemController implements Initializable {

    @FXML
    private ImageView iconSelect;

    @FXML
    private Label lblTaskName;

    @FXML
    private Button btnInfo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTask(TasksModel model) {
        ContextMenu menu = new ContextMenu();
        System.out.println(model.toString());
        lblTaskName.setText(model.getTitle());

        if (model.getCompleted()) {
            btnInfo.setText("Complete");
            iconSelect.setImage(new Image(getClass().getResourceAsStream(Constants.ICON_CHECK_FILL)));
            menu.getItems().add(new MenuItem("Set Task InComplete"));
        } else {
            btnInfo.setText("InComplete");
            iconSelect.setImage(new Image(getClass().getResourceAsStream(Constants.ICON_CHECK_UNFILL)));
            menu.getItems().add(new MenuItem("Set Task Complete"));
        }

        lblTaskName.setContextMenu(menu);
    }

}
