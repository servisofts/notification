package component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import Servisofts.SPGConect;
import Servisofts.SUtil;

public class Firebase {

    public static boolean _send(String apiKeyServer, JSONObject message) {
        try {

            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Length", Integer.toString(message.toString().getBytes().length));
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("charset", "UTF-16");
            con.setRequestProperty("Authorization", "key=" + apiKeyServer);

            con.setUseCaches(false);
            con.setDoOutput(true);

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.write(message.toString().getBytes());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-16"));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            System.out.println(content.toString());
            return true;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }

    /*
     * public static void send(String key_servicio, String title, String body){
     * try{
     * JSONObject fb_token = FirebaseToken.getAll(key_servicio);
     * String tokens_[] = JSONObject.getNames(fb_token);
     * 
     * JSONObject fb_server = FirebaseServer.getByKey(key_servicio);
     * fb_server = fb_server.getJSONObject(JSONObject.getNames(fb_server)[0]);
     * 
     * JSONObject message = new JSONObject();
     * message.put("priority", "high");
     * message.put("contentAvailable", true);
     * //
     * 
     * JSONObject notification = new JSONObject();
     * notification.put("title", title);
     * notification.put("body", body);
     * notification.put("sound", "default");
     * 
     * message.put("notification", notification);
     * 
     * for (int i = 0; i < tokens_.length; i++) {
     * message.put("to", fb_token.getJSONObject(tokens_[i]).getString("token") );
     * Firebase._send(fb_server.getString("key_server"),message);
     * }
     * }catch(Exception e){
     * e.printStackTrace();
     * }
     * }
     */

    public static void send(String token, String title, String body, JSONObject dta) {
        try {

            JSONObject firebaseToken = FirebaseToken.get(token);
            firebaseToken = firebaseToken.getJSONObject(JSONObject.getNames(firebaseToken)[0]);
            String key_servicio = firebaseToken.getString("key_servicio");
            JSONObject fb_server = FirebaseServer.getByKey(key_servicio);
            fb_server = fb_server.getJSONObject(JSONObject.getNames(fb_server)[0]);

            JSONObject message = new JSONObject();
            message.put("priority", "high");
            message.put("contentAvailable", true);
            //

            JSONObject notification = new JSONObject();
            notification.put("title", title);
            notification.put("body", body);
            notification.put("sound", "default");
            // notification.put("image",
            // "https://ruddy.ibrokers.cloud/imagesAdmin/6340999");

            message.put("notification", notification);
            message.put("to", token);
            message.put("data", dta);

            JSONObject data = new JSONObject();
            data.put("key", SUtil.uuid());
            data.put("estado", 1);
            data.put("descripcion", title);
            data.put("observacion", body);
            data.put("fecha_on", SUtil.now());
            data.put("key_firebase_token", firebaseToken.getString("key"));
            data.put("key_servicio", firebaseToken.getString("key_servicio"));
            data.put("data", dta);
            if (firebaseToken.has("key_usuario") && !firebaseToken.isNull("key_usuario")
                    && firebaseToken.getString("key_usuario").length() > 0) {
                data.put("key_usuario", firebaseToken.getString("key_usuario"));
            }

            SPGConect.insertArray("notification", new JSONArray().put(data));

            Firebase._send(fb_server.getString("key_server"), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(String token, String title, String body, JSONObject dta, String url_image) {
        try {

            JSONObject firebaseToken = FirebaseToken.get(token);
            firebaseToken = firebaseToken.getJSONObject(JSONObject.getNames(firebaseToken)[0]);
            String key_servicio = firebaseToken.getString("key_servicio");
            JSONObject fb_server = FirebaseServer.getByKey(key_servicio);
            fb_server = fb_server.getJSONObject(JSONObject.getNames(fb_server)[0]);

            JSONObject message = new JSONObject();
            message.put("priority", "high");
            message.put("contentAvailable", true);
            //

            JSONObject notification = new JSONObject();
            notification.put("title", title);
            notification.put("body", body);
            notification.put("sound", "default");
            notification.put("image", url_image);

            message.put("notification", notification);
            message.put("to", token);
            message.put("data", dta);

            JSONObject data = new JSONObject();
            data.put("key", SUtil.uuid());
            data.put("estado", 1);
            data.put("descripcion", title);
            data.put("observacion", body);
            data.put("fecha_on", SUtil.now());
            data.put("key_firebase_token", firebaseToken.getString("key"));
            data.put("key_servicio", firebaseToken.getString("key_servicio"));
            data.put("data", dta);
            if (firebaseToken.has("key_usuario") && !firebaseToken.isNull("key_usuario")
                    && firebaseToken.getString("key_usuario").length() > 0) {
                data.put("key_usuario", firebaseToken.getString("key_usuario"));
            }

            SPGConect.insertArray("notification", new JSONArray().put(data));

            Firebase._send(fb_server.getString("key_server"), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendAll(String key_usuario, String title, String body, JSONObject dta, String url_image,
            JSONObject tokens) {
        try {

            JSONObject data = new JSONObject();
            data.put("key", SUtil.uuid());
            data.put("estado", 1);
            data.put("descripcion", title.replaceAll("'", "''"));
            data.put("observacion", body.replaceAll("'", "''"));
            data.put("fecha_on", SUtil.now());
            data.put("key_usuario", key_usuario);
            data.put("url_image", url_image);
            // data.put("key_servicio", firebaseToken.getString("key_servicio"));
            data.put("data", dta.getJSONObject("data"));

            SPGConect.insertArray("notification", new JSONArray().put(data));

            String token;

            JSONObject token_object = null;
            JSONObject fb_server = null;
            JSONObject firebaseToken = null;
            for (int i = 0; i < JSONObject.getNames(tokens).length; i++) {
                token_object = tokens.getJSONObject(JSONObject.getNames(tokens)[i]);
                token = token_object.getString("token");

                firebaseToken = FirebaseToken.get(token);
                firebaseToken = firebaseToken.getJSONObject(JSONObject.getNames(firebaseToken)[0]);

                String key_servicio = firebaseToken.getString("key_servicio");

                fb_server = FirebaseServer.getByKey(key_servicio);
                fb_server = fb_server.getJSONObject(JSONObject.getNames(fb_server)[0]);

                JSONObject notToken = new JSONObject();
                notToken.put("key", UUID.randomUUID().toString());
                notToken.put("estado", 1);
                notToken.put("fecha_on", SUtil.now());
                notToken.put("key_notification", data.getString("key"));
                notToken.put("key_firebase_token", firebaseToken.getString("key"));
                if (firebaseToken.has("key_usuario") && !firebaseToken.isNull("key_usuario")) {
                    notToken.put("key_usuario", firebaseToken.getString("key_usuario"));
                }

                SPGConect.insertArray("notification_token", new JSONArray().put(notToken));

                //

                JSONObject notification = new JSONObject();
                notification.put("title", title);
                dta.getJSONObject("data").put("title", title);

                notification.put("body", body);
                dta.getJSONObject("data").put("body", body);

                notification.put("sound", "default");
                dta.getJSONObject("data").put("sound", "default");

                notification.put("android_channel_id", "default_channel_id");

                JSONObject apns = new JSONObject();

                if (url_image.length() > 0) {
                    notification.put("image", url_image);
                    dta.getJSONObject("data").put("image", url_image);

                    JSONObject fcm_options = new JSONObject().put("image", url_image);
                    apns.put("fcm_options", fcm_options);
                    JSONObject payload = new JSONObject().put("aps", new JSONObject().put("mutable-content", 1));
                    apns.put("payload", payload);
                }

                // JSONObject android_n = new JSONObject();

                // android_n.put("click_action", "android.intent.action.MAIN");
                // JSONObject android = new JSONObject();
                // android.put("priority", "HIGH");
                // android.put("notification", android_n);
                // message.put("android", android);

                // if (dta.getJSONObject("data").has("deepLink")) {
                // notification.put("click_action",
                // dta.getJSONObject("data").getString("deepLink"));
                // notification.put("link", dta.getJSONObject("data").getString("deepLink"));
                // notification.put("click_action", "OPEN_ACTIVITY_1");
                // }

                JSONObject message = new JSONObject();
                message.put("priority", "high");

                message.put("content_available", true);
                message.put("mutable_content", true);
                if (!token_object.has("platform") || !token_object.getString("platform").equals("android")) {
                    message.put("notification", notification);
                    message.put("apns", apns);
                }

                message.put("to", token);
                message.put("data", dta.getJSONObject("data"));

                Firebase._send(fb_server.getString("key_server"), message);
                System.out.println(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
