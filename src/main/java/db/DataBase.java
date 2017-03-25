package db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MHSL on 28.02.2017.
 *
 * This class serves for access to DB.
 */
public class DataBase {
    
    private static Connection connection;
    
    public static Connection getConnection() {
        try {
            InitialContext context = new InitialContext();
            DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/polish");
            connection = source.getConnection();
        } catch (NamingException e) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
        }
        return connection;
    }
}
