package cryptocurrency.demo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CryptocurrencyVerification {

    public static List<Map<String, String>> verification() {

        List<Map<String, String>> cryptocurrency = new ArrayList<>();
        String httpRequest = "https://api.coinlore.net/api/ticker/?id=90,80,48543";
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(httpRequest).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(1000);


            connection.connect();
            StringBuilder string = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    string.append(line);
                    string.append("\n");
                }
                line = string.toString();
                line = line.replaceAll("\n", "");

                String mass[] = line.split("\\s*},\\{\\s*");

                for (int i = 0; i < mass.length; i++) {
                    mass[i] = mass[i].replaceAll("\\{|\\}|\\[|\\]|\"", "");
                }

                for (int i = 0; i < mass.length; i++) {
                    String newMass[] = mass[i].split("\\s*\\,\\s*");
                    Map<String, String> map = new HashMap<>();

                    for (String st : newMass) {
                        String elements[] = st.split("\\s*\\:\\s*");
                        if (elements[0].equals("id")
                                || elements[0].equals("symbol")
                                || elements[0].equals("name")
                                || elements[0].equals("price_usd")) {
                            map.put(elements[0], elements[1]);
                        }
                    }

                    cryptocurrency.add(map);
                }

            } else {
                cryptocurrency = null;
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return cryptocurrency;
    }
}
