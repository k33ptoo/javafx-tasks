/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxtaskwindow;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Cleanup;

/**
 *
 * @author Too
 */
public class HomeController implements Initializable {

    @FXML
    private Label lblToday;
    @FXML
    private Label lblUpcoming;
    @FXML
    private VBox vTaskItems;

    String JSON_URL = "https://jsonplaceholder.typicode.com/todos";
    private ObservableList<TasksModel> listOfTasks;
    
    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(fetchList);

        fetchList.setOnSucceeded((event) -> {

            listOfTasks = FXCollections.observableArrayList(fetchList.getValue());
            int size = listOfTasks.size();
            lblToday.setText("Today(" + size + ")");
            lblUpcoming.setText("Upcoming(" + 0 + ")");

            try { //load task items to vbox
                Node[] nodes = new Node[size];
                for (int i = 0; i < nodes.length; i++) {
                    //load specific item
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskItem.fxml"));
                    TaskItemController controller = new TaskItemController();
                    loader.setController(controller);
                    nodes[i] = loader.load();
                    vTaskItems.getChildren().add(nodes[i]);
                    controller.setTask(listOfTasks.get(i));
                }

                for (int i = 0; i < nodes.length; i++) {
                    try {
                        nodes[i] = FXMLLoader.load(getClass().getResource("/fxml/TaskItem.fxml"));
                        //vTaskItemsupcoming.getChildren().add(nodes[i]);
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e) {
                System.err.println("Error Creating Tasks...");
                System.err.println(e.getMessage());
            }
        });
    }

    private final Task<List<TasksModel>> fetchList = new Task() {

        @Override
        protected List<TasksModel> call() throws Exception {
            List<TasksModel> list = null;
            try {
                String url = readUrl(JSON_URL);
                System.out.println(url);
                list = new Gson().fromJson(url, new TypeToken<List<TasksModel>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

    };

    private static String readUrl(String urlString) throws Exception {

        @Cleanup
        BufferedReader reader = null;

        URL url = new URL(urlString);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder buffer = new StringBuilder();
        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1) {
            buffer.append(chars, 0, read);
        }

        return buffer.toString();
    }

}
