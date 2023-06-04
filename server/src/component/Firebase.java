package component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import Servisofts.SPGConect;
import Servisofts.SUtil;

public class Firebase {
    
    public static boolean _send(String apiKeyServer, JSONObject message){
        try{

            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Length", Integer.toString(message.toString().getBytes().length));
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "key="+apiKeyServer);

            con.setUseCaches(false);
            con.setDoOutput(true);

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(message.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            System.out.println(content);
            return true;
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }

    /*public static void send(String key_servicio, String title, String body){
        try{
            JSONObject fb_token = FirebaseToken.getAll(key_servicio);
            String tokens_[] = JSONObject.getNames(fb_token);

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

            message.put("notification", notification);

            for (int i = 0; i < tokens_.length; i++) {
                message.put("to",  fb_token.getJSONObject(tokens_[i]).getString("token") );  
                Firebase._send(fb_server.getString("key_server"),message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

    public static void send(String token, String title, String body){
        try{
        

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

            message.put("notification", notification);
            message.put("to",  token);  

            JSONObject data = new JSONObject();
            data.put("key", SUtil.uuid());
            data.put("estado", 1);
            data.put("descripcion", title);
            data.put("observacion", body);
            data.put("fecha_on", SUtil.now());
            data.put("key_firebase_token", firebaseToken.getString("key"));
            data.put("key_servicio", firebaseToken.getString("key_servicio"));
            if(firebaseToken.has("key_usuario") && !firebaseToken.isNull("key_usuario") && firebaseToken.getString("key_usuario").length()>0){
                data.put("key_usuario", firebaseToken.getString("key_usuario"));
            }
            
            SPGConect.insertArray("notification", new JSONArray().put(data));

            Firebase._send(fb_server.getString("key_server"),message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
