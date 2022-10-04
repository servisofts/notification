import org.json.JSONArray;
import org.json.JSONObject;

import Servisofts.Servisofts;
import component.Firebase;

public class App {
    public static void main(String[] args) {
        try {
            Servisofts.ManejadorCliente = ManejadorCliente::onMessage;
            Servisofts.Manejador = Manejador::onMessage;
            // Servisofts.initialize();

            String apiKeyServer = "AAAAzkSEhHo:APA91bGT-WaPq-bEMy8XPxaWyaRq7Zzcm2qlHLZ7LZUN772krmb1PJZuEyfX3GUpH9WUcBU7o7n2QXldRy3h-Bx8tTDQ_hs8nQzCks9aWG-QAfNyYr2-ne3yw3bXWLbVqBwrhcWerhzl";
            String apiKeyDevice = "eNbmZNP3Z0vmi2RJIfcOC7:APA91bEXMybv2bZ8cfz3zkfpcHj1xCtQnFg1p095Beyp2L_vIS3GsI4qmQocPkjtsipJpR0Z6iIo8bVrhnVoSbC9goHU2Nk8qe1dDNBaLd5nhPLsRtKLRlaAe0wxmoMw5mk9TKsV9hOb";

            JSONObject message = new JSONObject();
            message.put("to", apiKeyDevice);
            // message.put("tokens", new JSONArray().put((ap)));
            message.put("priority", "high");
            message.put("contentAvailable", true);

            JSONObject notification = new JSONObject();
            notification.put("title", "Servisofts");
            notification.put("body", "Bienvenido a servisofts notifications!!");
            notification.put("sound", "default");
            message.put("notification", notification);
        //    message.put("data", notification);

            // JSONObject webPush = new JSONObject();
            // webPush.put("fcm_options", new JSONObject().put("link", "https://localhost:3000"));
            //message.put("webpush", webPush);

            Firebase.send(apiKeyServer, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}