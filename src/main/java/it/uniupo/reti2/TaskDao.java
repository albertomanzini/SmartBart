package it.uniupo.reti2;

import it.uniupo.reti2.restAPI.Passenger;
import it.uniupo.reti2.utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TaskDao {


    public void addBooking(Passenger passenger) {
        final String sql = "INSERT INTO BART(traveler_name, bicycle, train_id) VALUES (?, ?, ?)";

        try {
            Connection conn = DBConnect.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, passenger.getCf());
            st.setInt(2, passenger.getBicycle());
            st.setString(3, passenger.getTrainId());

            st.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
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
*/
}
