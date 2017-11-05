//package debug;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//
//class DatabaseConnectivityTest {
//
//    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
//        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
//        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/myDB", "me", "password");
//        ResultSet rs = connection.prepareStatement("select * from sys.sysschemas s").executeQuery();
//        ResultSetMetaData metaData = rs.getMetaData();
//        int columnCount = metaData.getColumnCount();
//        for (int i = 1; i <= columnCount; i++) {
//            System.out.println(metaData.getColumnName(i));
//        }
//        while (rs.next()) {
//            for (int i = 1; i <= columnCount; i++) {
//                System.out.println(rs.getObject(i));
//            }
//        }
//
//    }
//
//}
