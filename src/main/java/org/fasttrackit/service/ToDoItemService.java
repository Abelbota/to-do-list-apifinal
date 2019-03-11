package org.fasttrackit.service;

import org.fasttrackit.persistence.ToDoItemRepository;
import org.fasttrackit.transfer.SaveToDoItemRequest;

import java.sql.SQLException;

public class ToDoItemService {

    private ToDoItemRepository toDoItemRepository = new ToDoItemRepository();

    public void createToDoItem(SaveToDoItemRequest request) throws SQLException {
        System.out.println("Creating to do item: " + request);
        toDoItemRepository.createToDoItem(request);
    }
}
