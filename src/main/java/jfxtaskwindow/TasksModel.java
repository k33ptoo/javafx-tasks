/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxtaskwindow;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Too
 */
@Getter
@Setter
@NoArgsConstructor
public class TasksModel {
    
    private String taskName;
    private String taskStatus;
    private Image icon;

    public TasksModel(String taskName, String taskStatus, Image icon) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.icon = icon;
    }
    
}
