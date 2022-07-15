package cryptocurrency.demo.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Timer;
import java.util.TimerTask;

public class CheckTimer extends TimerTask {
    private static final Logger logger = LogManager.getLogger();
    private static Connection connection;

    public void run() {
        UpdateDatabase updateDatabase = new UpdateDatabase();
        updateDatabase.update(CryptocurrencyVerification.verification(), connection);
    }


    public static void startTimer(){
        if (connection == null) {
            String url = "jdbc:mysql://127.0.0.1:3306/cryptocurrency_mydb";
            String username = "root";
            String password = "bkj85vso";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                connection = DriverManager.getConnection(url, username, password);

            } catch (Exception e) {
                logger.log(Level.WARN, e);
            }
        }
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new CheckTimer(), 0, 60 * 1000);
    }
}
