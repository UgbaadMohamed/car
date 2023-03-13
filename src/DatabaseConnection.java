import java.sql.*;

public class DatabaseConnection {

    public static final String database_url = "jdbc:mysql://localhost:3306/Kailua_rental";
    public static java.sql.Connection con;

    public void connectingSQL(String statement, String[] msg, String[]column) {
        try {
            con = DriverManager.getConnection(database_url, "root", "Seasam10");
            Statement s = con.createStatement();
            String sql = statement;

            ResultSet rs = s.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    for (int i = 0; i < column.length; i++) {
                        System.out.println(msg[i] + rs.getString(column[i]));
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception:" + e.getMessage());
            System.exit(1);
        }

    }

    public void executeDML(String statement, String msg){
        String query = statement;
        try  {
            con = DriverManager.getConnection(database_url, "root", "Seasam10");
            Statement s = con.createStatement();
            s.executeUpdate(query);
            System.out.println(msg);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
