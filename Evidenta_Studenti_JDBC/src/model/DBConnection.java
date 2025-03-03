package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;


    public class DBConnection {

    private final String DB_Name = "jdbc:mariadb://localhost:3306/studenti";
    private final String username="root";
    private final String password ="";
    public static Logger logger= Logger.getAnonymousLogger();
    private static Connection connection = null;
    private static DBConnection instance = null;

    private DBConnection(){
        try {
            // below two lines are used for connectivity.
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DB_Name,
                    username, password);

            if(connection!=null){
                logger.info("Conexiune cu succes!");
            }

        }catch(Exception e){
            logger.info(e.getMessage());
        }
    } // end conexion

    public static DBConnection getInstance(){
        if(instance == null){
            //System.out.println("Creare instanta");
            instance = new DBConnection();
        }
        return instance;
    }

        public Connection getConnection() {
            return connection;
        }
    }