/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxtaskwindow;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Too
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TasksModel {
    
    private String title;
    private Boolean completed;

    public TasksModel(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }
    
}
