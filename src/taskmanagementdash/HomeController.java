/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanagementdash;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 *
 * @author Too
 */
public class HomeController implements Initializable {

    private Label label;
    @FXML
    private TextField tfSearch;
    @FXML
    private Label lblProjectCount;
    @FXML
    private Label lblName;
    @FXML
    private Label lblGreeting;
    @FXML
    private Label lblName1;
    @FXML
    private Label lblGreeting1;
    @FXML
    private Label lblName11;
    @FXML
    private Label lblName111;
    @FXML
    private VBox vTaskItems;
    @FXML
    private Button btnEX;
    @FXML
    private VBox vTaskItemsupcoming;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnEX) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<TasksModel> model = new ArrayList<>();
        model.add(new TasksModel("Fix bug on issue #87", "InComplete", new Image(getClass().getResourceAsStream("/icons/icons8_checked_filled_24px.png"))));
        model.add(new TasksModel("Fix bug on issue #84", "InComplete", new Image(getClass().getResourceAsStream("/icons/icons8_checked_filled_24px.png"))));
        model.add(new TasksModel("Fix Label - Bug ", "Complete", new Image(getClass().getResourceAsStream("/icons/icons8_checked_filled_24px_1.png"))));
        model.add(new TasksModel("Fix bug 7", "Complete", new Image(getClass().getResourceAsStream("/icons/icons8_checked_filled_24px_1.png"))));
        model.add(new TasksModel("Fix bug on issue #87", "InComplete", new Image(getClass().getResourceAsStream("/icons/icons8_checked_filled_24px.png"))));
        model.add(new TasksModel("Remove Button on FXML Project 23", "Complete", new Image(getClass().getResourceAsStream("/icons/icons8_checked_filled_24px_1.png"))));

        //load task items to vbox       
        Node[] nodes = new Node[model.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
              //load specific item
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskItem.fxml"));
                TaskItemController controller = new TaskItemController();
                loader.setController(controller);
                nodes[i] = loader.load();
                vTaskItems.getChildren().add(nodes[i]);
                controller.setTask(model.get(i));
                
            } catch (Exception e) {
            }
        }

        for (int i = 0; i < nodes.length; i++) {
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource("TaskItem.fxml"));
                vTaskItemsupcoming.getChildren().add(nodes[i]);
            } catch (Exception e) {
            }
        }

    }

}
