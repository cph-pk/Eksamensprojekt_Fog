package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* Creates a connection to the database
*
 */
public class Connector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static Connection singleton;

    /**
     *
     * @param con
     */
    public static void setConnection( Connection con ) {
        singleton = con;
    }

    /**
     *
     * @return singleton
     * @throws ClassNotFoundException Thrown when an application tries to load in a class, but no definition for the class with the specified name could be found.
     * @throws SQLException An exception that provides information on a database access error or other errors
     */
    public static Connection connection() throws ClassNotFoundException, SQLException {
        if ((singleton == null) || singleton.isClosed()) {
            setDBCredentials();
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            singleton = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return singleton;
    }


    public static void setDBCredentials() {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null){
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            URL = System.getenv("JDBC_CONNECTION_STRING");
            USERNAME = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
        } else {
            // Localhost
            URL = "jdbc:mysql://localhost:3306/fogdb?serverTimezone=CET"; //&useSSL=false
            USERNAME = "root";
            PASSWORD = "4354";
        }
    }

}
