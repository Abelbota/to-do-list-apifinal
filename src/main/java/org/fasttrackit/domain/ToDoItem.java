package org.fasttrackit.domain;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class ToDoItem {

    private long id;
    private String description;
    private boolean started;
    private boolean done;
    private Date deadline;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("test1", "test2");

        for (String a : list) {
            System.out.println(a);
        }
    }
}
