package org.fasttrackit.persistence;

import org.fasttrackit.transfer.SaveToDoItemRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
