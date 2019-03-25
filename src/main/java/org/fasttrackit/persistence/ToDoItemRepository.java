package org.fasttrackit.persistence;

import org.fasttrackit.domain.ToDoItem;
import org.fasttrackit.transfer.SaveToDoItemRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoItemRepository {

    public void createToDoItem(SaveToDoItemRequest request) throws SQLException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO to_do_items (description, started, done, deadline) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, request.getDescription());
            preparedStatement.setBoolean(2, request.isStarted());
            preparedStatement.setBoolean(3, request.isDone());
            preparedStatement.setDate(4, request.getDeadline());

            preparedStatement.executeUpdate();
        }
    }

    public List<ToDoItem> getToDoItems() throws SQLException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, description, started, done, deadline FROM to_do_items"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            List<ToDoItem> response = new ArrayList<>();

            while (resultSet.next()) {
                ToDoItem toDoItem = new ToDoItem();
                toDoItem.setId(resultSet.getLong("id"));
                toDoItem.setDescription(resultSet.getString("description"));
                toDoItem.setDone(resultSet.getBoolean("done"));
                toDoItem.setStarted(resultSet.getBoolean("started"));
                toDoItem.setDeadline(resultSet.getDate("deadline"));

                response.add(toDoItem);
            }

            return response;
        }
    }
}
