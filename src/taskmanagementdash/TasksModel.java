/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanagementdash;

import javafx.scene.image.Image;

/**
 *
 * @author Too
 */
public class TasksModel {
    
    private String taskName;
    private String taskStatus;
    private Image icon;

    public TasksModel(String taskName, String taskStatus, Image icon) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.icon = icon;
    }
    
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
    
    
    
    
}
