package it.uniupo.reti2;

import utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class TaskDao {

    /**
     * Get all tasks from the DB
     * @return a list of task, or an empty list if no tasks are available
     */
    public List<Task> getAllTasks() {
        final String sql = "SELECT id, descrizione, completato FROM tasks";

        List<Task> tasks = new LinkedList<>();

        try {
            Connection conn = DBConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Task t = new Task(rs.getInt("id"), rs.getString("descrizione"), rs.getInt("completato"));
                tasks.add(t);
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Get a single task from the DB
     * @param id of the task to retrieve
     * @return the task, or null if not found
     */
    public Task getTask(int id)
    {
        Task task = null;
        final String sql = "SELECT descrizione, completato FROM tasks WHERE id = ?";

        try {
            Connection conn = DBConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                task = new Task(id, rs.getString("descrizione"), rs.getInt("completato"));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    /**
     * Add a new task into the DB
     * @param newTask the task to be added
     */
    public void addTask(Task newTask) {
        final String sql = "INSERT INTO tasks(descrizione, completato) VALUES (?, ?)";

        try {
            Connection conn = DBConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, newTask.getDescription());
            st.setInt(2, newTask.getCompleted());

            st.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int id) {

        final String sql = "DELETE FROM tasks WHERE id=?";

        try {
            Connection conn = DBConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1,id);
            st.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeDescriptionTask(Task newTask) {

        final String sql = "UPDATE tasks SET descrizione = ? WHERE id = ?";

        try {
            Connection conn = DBConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, newTask.getDescription());
            st.setInt(2, newTask.getId());

            st.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeCompleted(Task taskTmp) {
        final String sql = "UPDATE tasks SET completato = ? WHERE id = ?";

        try {
            Connection conn = DBConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, taskTmp.getCompleted());
            st.setInt(2, taskTmp.getId());

            st.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a task that contains a substring in its description
     *
     * @param substring the task that must change description
     */
    public List<Task> getTaskFromString(String substring) {
        final String sql = "SELECT id, descrizione, completato FROM tasks WHERE descrizione LIKE '%'||?||'%'";

        List<Task> tasks = new LinkedList<>();

        try {
            Connection conn = DBConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, substring);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Task t = new Task(rs.getInt("id"), rs.getString("descrizione"), rs.getInt("completato"));
                tasks.add(t);
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

}
