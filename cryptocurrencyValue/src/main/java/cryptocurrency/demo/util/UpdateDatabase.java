package cryptocurrency.demo.util;

import cryptocurrency.demo.entity.Cryptocurrency;
import cryptocurrency.demo.entity.Users;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Primary
@Service
@RequiredArgsConstructor
public class UpdateDatabase {
    private static final Logger logger = LogManager.getLogger();
    private static final String UPDATE = "UPDATE cryptocurrency SET price_usd = ?  WHERE cryptocurrency_id=?";
    private static final String FIND_ALL_USERS = "SELECT user_id, username, cryptocurrency_id, price_usd FROM users";

    public void update(List<Map<String, String>> newCrypto, Connection connection){
        List<Users> users = new ArrayList<>();

            try{
                Statement userStatement = connection.createStatement();
                ResultSet resultSet = userStatement.executeQuery(FIND_ALL_USERS);
                users = createList(resultSet);

                for(Map<String, String> crypto: newCrypto){
                    PreparedStatement statement = connection.prepareStatement(UPDATE);
                    statement.setDouble(1, Double.parseDouble(crypto.get("price_usd")));
                    statement.setLong(2,Long.parseLong(crypto.get("id")));
                    statement.executeUpdate();

                    for (Users user: users){
                        if(user.getCryptocurrency_id() == Long.parseLong(crypto.get("id"))){
                            double number = percentageСalculation(user.getPrice_usd(), Double.parseDouble(crypto.get("price_usd")));
                            if(number >= 0.01){
                                StringBuilder massage = new StringBuilder(crypto.get("symbol"))
                                        .append(", ")
                                        .append(user.getUsername())
                                        .append(", ")
                                        .append(number);
                                logger.log(Level.WARN, massage);
                            }
                        }
                    }
                }
            }
            catch (Exception e){
                logger.log(Level.WARN, e);
            }
    }

    private List<Users> createList(ResultSet resultSet){
        List<Users> users = new ArrayList<>();

        try{
            while (resultSet.next()) {
                Long user_id = resultSet.getLong(1);
                String username = resultSet.getString(2);
                Long cryptocurrency_id = resultSet.getLong(3);
                Double price_usd = resultSet.getDouble(4);

                users.add(new Users(user_id, username, cryptocurrency_id, price_usd));
            }

        }
        catch (Exception e){
            logger.log(Level.WARN, e);
        }

        return users;
    }

    private Double percentageСalculation(Double user, Double crypto){
        double number = 0;

        if (user > crypto){
            number = 1-(crypto/user);
        }
        if (user < crypto){
            number = 1-(user/crypto);
        }

        return number;
    }


}
