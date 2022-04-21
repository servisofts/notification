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
            String apiKeyDevice = "coz2_LcJSmOuUjMMxDslQp:APA91bHju6aeTprbd7S1E52hd_kERRTFKRSZlN4W4_CakXVNHB7uyFrNR4zdFOwXzcWKx7SXUT42259FPf1ThmJQag90YuYnOk3qqaIyFQiP9J61F4Mm6PoTyPlmN7C8mZ-lVoaknz-r";

            JSONObject message = new JSONObject();
            message.put("to", apiKeyDevice);
            // message.put("tokens", new JSONArray().put((Api)));
            message.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", "Servisofts");
            notification.put("body", "Bienvenido a servisofts notifications!!");
            message.put("notification", notification);
           // message.put("data", notification);

            JSONObject webPush = new JSONObject();
            webPush.put("fcm_options", new JSONObject().put("link", "https://localhost:3000"));
            //message.put("webpush", webPush);

            Firebase.send(apiKeyServer, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}