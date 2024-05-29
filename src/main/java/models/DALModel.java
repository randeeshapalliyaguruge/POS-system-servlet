package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DALModel {

    protected String[] fillable;

    public HashMap<String, Object> create(HashMap<String, Object> data) {

        for (String key : data.keySet()) {
            if (!key.equals("id") && !key.equals("created_at") && !key.equals("updated_at")) {

                boolean fillable = false;

                for (String f : this.fillable) {
                    if (f.equals(key)) {
                        fillable = true;
                    }
                }

                if (!fillable) {
                    throw new IllegalArgumentException("The key " + key + " is not fillable.");
                }
            }
        }

        String columns = "";

        for (String key : data.keySet()) {
            columns += key + ", ";
        }

        columns = columns.substring(0, columns.length() - 2);

        String values = "";

        for (Object value : data.values()) {
            if (value instanceof String) {
                values += "'" + value + "', ";
            } else {
                values += value + ", ";
            }
        }

        values = values.substring(0, values.length() - 2);

        String sql = "INSERT INTO " + getTable() + "(" + columns + ") VALUES(" + values + ")";

        Connection conn = Database.getInstance().getConnection();

        try (java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();

            // get the last inserted id
            ResultSet rs = pstmt.getGeneratedKeys();


            if (rs.next()) {
                return get(rs.getInt(1));
            }

            System.out.println("Product created successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            Database.closeConnection();
            System.out.println("Connection closed for transaction.");
        }

        return null;
    }

    public HashMap<String, Object> update(int id, HashMap<String, Object> data) {

        for (String key : data.keySet()) {
            if (!key.equals("id") && !key.equals("created_at") && !key.equals("updated_at")) {

                boolean fillable = false;

                for (String f : this.fillable) {
                    if (f.equals(key)) {
                        fillable = true;
                    }
                }

                if (!fillable) {
                    throw new IllegalArgumentException("The key " + key + " is not fillable.");
                }

            }
        }

        String columns = "";

        for (String key : data.keySet()) {
            columns += key + " = ?, ";
        }

        columns = columns.substring(0, columns.length() - 2);

        String sql = "UPDATE " + getTable() + " SET " + columns + " WHERE id = ?";

        Connection conn = Database.getInstance().getConnection();

        try (java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int i = 1;
            for (Object value : data.values()) {
                pstmt.setObject(i, value);
                i++;
            }

            pstmt.setInt(i, id);

            pstmt.executeUpdate();

            return get(id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            Database.closeConnection();
            System.out.println("Connection closed for transaction.");
        }

        return null;
    }

    public void delete(int id) {
        String sql = "DELETE FROM " + getTable() + " WHERE id = ?";
        try (Connection conn = Database.getInstance().getConnection();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            Database.closeConnection();
            System.out.println("Connection closed for transaction.");
        }
    }

    public HashMap<String, Object> get(int id) {
        // SQL query to select a product by id
        String sql = "SELECT * FROM " + getTable() + " WHERE id = " + id;

        try {
            Connection conn = Database.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                HashMap<String, Object> model = new HashMap<>();

                model.put("id", rs.getInt("id"));

                for (String f : this.fillable) {
                    model.put(f, rs.getObject(f));
                }

                return model;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            Database.closeConnection();
            System.out.println("Connection closed for transaction.");
        }

        return null;
    }

    public String getTable() {
        return this.getClass().getSimpleName().toLowerCase() + "s";
    }

    // Method to execute a query and return a single result as HashMap
    public HashMap<String, Object> getOne(String sql) throws SQLException {
        try {
            Connection conn = Database.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                HashMap<String, Object> result = new HashMap<>();
                for (String column : fillable) {
                    result.put(column, rs.getObject(column));
                }
                return result;
            }

        } catch (SQLException e) {
            throw new SQLException("Error executing SQL query: " + e.getMessage());

        } finally {
            Database.closeConnection();
            System.out.println("Connection closed for transaction.");
        }

        return null;
    }

}
