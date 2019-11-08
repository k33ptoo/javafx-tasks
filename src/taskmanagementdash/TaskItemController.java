/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanagementdash;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
    }

    public void setTask(TasksModel model) {
        lblTaskName.setText(model.getTaskName());
        btnInfo.setText(model.getTaskStatus());
        iconSelect.setImage(model.getIcon());
        
        ContextMenu menu = new ContextMenu();
        if (model.getTaskStatus().equalsIgnoreCase("InComplete")) {
            menu.getItems().add(new MenuItem("Set Task Complete"));
        } else {
            menu.getItems().add(new MenuItem("Set Task InComplete"));
        }
        
        lblTaskName.setContextMenu(menu);
    }

}
