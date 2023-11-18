import java.sql.*;

public class BackEnd {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "AKakhilesh#29";
    private static final Connection con;

    static {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void getTableData(String tableName) throws SQLException {
        String sql = "SELECT * FROM "+tableName+" limit 10";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet resultSet = pst.executeQuery();

// ------------------------------------------------------------------------------------
//        while (resultSet.next()) {
//            int id = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            String college = resultSet.getString("college");
//            String branch = resultSet.getString("branch");
//
//
//            System.out.println("ID: " + id);
//            System.out.println("Name: " + name);
//            System.out.println("College: " + college);
//            System.out.println("Branch: " + branch);
//            System.out.println("--------------------");
//        }
// -------------------------------------------------------------------------------------
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Iterate through the ResultSet and print the entire row
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                System.out.println(columnName + ": " + value);
            }
            System.out.println("--------------------");
        }
    }

    public static void main(String[] args) throws SQLException {
        if (con != null) {
            System.out.println("Connection Successful");
            getTableData("sem2_results");
        }
        else {
            System.out.println("Connection Unsuccessful");
        }
    }
}