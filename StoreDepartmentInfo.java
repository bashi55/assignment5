import java.sql.*;

public class StoreDepartmentInfo {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/departments";
    public static final String USERNAME = "your_username";
    public static final String PASSWORD = "your_password";

    public static void main(String[] args) {

        DepartmentInfo departmentInfo = new DepartmentInfo(10, "Engineering");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "INSERT INTO department (id, name) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, departmentInfo.getId());
            preparedStatement.setString(2, departmentInfo.getName());

            preparedStatement.executeUpdate();

            System.out.println("Department information inserted successfully!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

class DepartmentInfo {
    private int id;
    private String name;

    public DepartmentInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
