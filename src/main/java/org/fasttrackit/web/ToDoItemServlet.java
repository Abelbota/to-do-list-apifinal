package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.domain.ToDoItem;
import org.fasttrackit.service.ToDoItemService;
import org.fasttrackit.transfer.MarkItemDoneRequest;
import org.fasttrackit.transfer.SaveToDoItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/to-do-items")
public class ToDoItemServlet extends HttpServlet {

    private ToDoItemService toDoItemService = new ToDoItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        ObjectMapper objectMapper = new ObjectMapper();
        SaveToDoItemRequest request = objectMapper.readValue(req.getReader(), SaveToDoItemRequest.class);

        try {
            toDoItemService.createToDoItem(request);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500, "There was an error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        String id = req.getParameter("id");

        ObjectMapper objectMapper = new ObjectMapper();
        MarkItemDoneRequest request = objectMapper.readValue(req.getReader(), MarkItemDoneRequest.class);

        try {
            toDoItemService.markToDoItemDone(Long.valueOf(id), request);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500, "There was an error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        String id = req.getParameter("id");

        try {
            toDoItemService.deleteToDoItem(Long.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500, "There was an error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        try {
            List<ToDoItem> toDoItems = toDoItemService.getToDoItems();

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(toDoItems);

            resp.getWriter().print(jsonResponse);
            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500, "There was an error: " + e.getMessage());
        }
    }

    //for preflight requests
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
    }
}
