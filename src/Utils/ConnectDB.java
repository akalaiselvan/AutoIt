package Utils;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.sql.*;

public class ConnectDB {
    private static String connectionUrl = "jdbc:sqlserver://localhost:3781;" +"databaseName=rpos7;";

        public  String getValueFromQuery(String query) throws FileNotFoundException {
        String printcolumnValue="";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            Connection connection = DriverManager.getConnection(connectionUrl, "sa", "Fiduciary");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                int columnsNumber = rsmd.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                      String columnValue = rs.getString(i);
                      printcolumnValue=printcolumnValue + columnValue + System.lineSeparator();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return printcolumnValue;
    }

    public static void db() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            Connection connection= DriverManager.getConnection(connectionUrl,"sa","Fiduciary");
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery("select * from med_shop_mast");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(47);
                    String val = rs.getString("msm_Rcver");
                    System.out.print(columnValue + " " + rsmd.getColumnName(47));
                    Log.writelog("i want" + val);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public  void updateQuery(String sql) {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            Connection connection= DriverManager.getConnection(connectionUrl,"sa","Fiduciary");
            Statement st = connection.createStatement();
            int rs =st.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
