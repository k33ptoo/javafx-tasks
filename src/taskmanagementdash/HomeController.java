/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanagementdash;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    
    String JSON_URL = "https://jsonplaceholder.typicode.com/todos";
    private ObservableList<TaskModel> listOfTasks;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if (event.getSource() == btnEX) {
            System.exit(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(fetchList);
         
        fetchList.setOnSucceeded((WorkerStateEvent event) -> {
            listOfTasks = FXCollections.observableArrayList(fetchList.getValue());
            
            int size = listOfTasks.size();
            lblName11.setText("Today(" + size + ")");
            lblName111.setText("Upcoming(" + 0 + ")");
            
            try { //load task items to vbox
                Node[] nodes = new Node[size];
                for (int i = 0; i < nodes.length; i++) {

                    //load specific item
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskItem.fxml"));
                    TaskItemController controller = new TaskItemController();
                    loader.setController(controller);
                    nodes[i] = loader.load();                    
                    vTaskItems.getChildren().add(nodes[i]);
                    controller.setTask(listOfTasks.get(i));
                    
                }
                for (int i = 0; i < nodes.length; i++) {
                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("TaskItem.fxml"));
                        //vTaskItemsupcoming.getChildren().add(nodes[i]);
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e) {
            }
        });
        
    }
    
    private final Task<List<TaskModel>> fetchList = new Task() {
        @Override
        protected List<TaskModel> call() throws Exception {
            List<TaskModel> list = null;
            try {
                list = new Gson().fromJson(readUrl(JSON_URL), new TypeToken<List<TaskModel>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
    };
    
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        
    }
    
}
