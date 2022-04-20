import org.json.JSONObject;

import Servisofts.Servisofts;
import component.Firebase;

public class App {
    public static void main(String[] args) {
        try {
            Servisofts.ManejadorCliente = ManejadorCliente::onMessage;
            Servisofts.Manejador = Manejador::onMessage;
            Servisofts.initialize();

            String apiKeyServer = "AAAAzkSEhHo:APA91bGT-WaPq-bEMy8XPxaWyaRq7Zzcm2qlHLZ7LZUN772krmb1PJZuEyfX3GUpH9WUcBU7o7n2QXldRy3h-Bx8tTDQ_hs8nQzCks9aWG-QAfNyYr2-ne3yw3bXWLbVqBwrhcWerhzl";
            String apiKeyDevice = "fS0YqmxHGU83NCo6C2izVc:APA91bGEBE7u3jZ3inaSSwah69oLmJGvFgYqQQYr1zO6My63WRoQeAYTyScFDFNkuy7GWu1t23ofKP127Pq5biLFTah7diMM3z8MSYZ7MbWNtMwL5VAzbnuYjIyUiZv92_c9P9_GSH_2";

            JSONObject message = new JSONObject();
            message.put("to", apiKeyDevice);
            message.put("priority", "high");

            
            JSONObject notification = new JSONObject();
            notification.put("title", "Servisofts");
            notification.put("body", "Bienvenido a servisofts notifications!!");
            message.put("notification", notification);
            message.put("data", notification);

            Firebase.send(apiKeyServer, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}