package component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public static void send(String key_servicio, String title, String body){
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
            

    }
}
