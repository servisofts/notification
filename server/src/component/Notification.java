package component;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import Servisofts.SPGConect;
import Servisofts.SUtil;
import Server.SSSAbstract.SSSessionAbstract;

public class Notification {
    public static final String COMPONENT = "notification";

    public static void onMessage(JSONObject obj, SSSessionAbstract session) {
        switch (obj.getString("type")) {
            case "getAll":
                getAll(obj, session);
                break;
            case "getByKey":
                getByKey(obj, session);
                break;
            case "registro":
                registro(obj, session);
                break;
            case "send":
                send(obj, session);
                break;
            case "registroAll":
                registroAll(obj, session);
                break;
            case "editar":
                editar(obj, session);
                break;
        }
    }

    public static void getAll(JSONObject obj, SSSessionAbstract session) {
        try {
            String consulta;
            if(obj.has("key_servicio")){
                consulta = "select get_all('" + COMPONENT + "', 'key_servicio', '"+obj.getString("key_servicio")+"' ) as json";
            }else{
                consulta = "select get_all('" + COMPONENT + "', 'key_servicio', '"+obj.getJSONObject("servicio").getString("key")+"','key_usuario', '"+obj.getString("key_usuario")+"' ) as json";
            }

            if(obj.has("key_usuario") && obj.has("limit")){
                consulta = "select get_all_limit('"+obj.getString("key_usuario")+"','"+obj.getJSONObject("servicio").getString("key")+"', '"+obj.get("limit")+"' ) as json";
            }

            if(obj.has("key_usuario") && obj.has("limit") && obj.has("offset")){
                consulta = "select get_all_limit('"+obj.getString("key_usuario")+"','"+obj.getJSONObject("servicio").getString("key")+"', '"+obj.get("limit")+"', '"+obj.get("offset")+"' ) as json";
            }

            JSONObject data = SPGConect.ejecutarConsultaObject(consulta);
            obj.put("data", data);
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getByKey(JSONObject obj, SSSessionAbstract session) {
        try {
            String consulta = "select get_by_key('" + COMPONENT + "', '" + obj.getString("key") + "') as json";
            JSONObject data = SPGConect.ejecutarConsultaObject(consulta);
            obj.put("data", data);
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void registro(JSONObject obj, SSSessionAbstract session) {
        try {
            JSONObject data = obj.getJSONObject("data");
            JSONObject dta = new JSONObject();
            if(data.has("data") && !data.isNull("data")){
                dta = data.getJSONObject("data");
            }
            Firebase.send(data.getString("token"), data.getString("descripcion"), data.getString("observacion"),dta);
            obj.put("data", data);
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void sendAll(JSONObject obj, SSSessionAbstract session) {
        try {
            JSONObject data = obj.getJSONObject("data");
            JSONObject dta = new JSONObject();
            if(data.has("data") && !data.isNull("data")){
                dta = data.getJSONObject("data");
            }
            Firebase.send(data.getString("token"), data.getString("descripcion"), data.getString("observacion"),dta);
            obj.put("data", data);
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void send(JSONObject obj, SSSessionAbstract session) {
        try {

            JSONObject data = obj.getJSONObject("data");

            final JSONObject tokens;
                
            if(obj.has("key_usuario") && !obj.isNull("key_usuario"))
                tokens = FirebaseToken.getAllUsuario(obj.getString("key_usuario"));
            else if(obj.has("tags") && !obj.isNull("tags"))
                tokens = FirebaseToken.getByTags(obj.getJSONObject("tags"), obj.getJSONObject("servicio").getString("key"));
            else
                tokens = FirebaseToken.getAll(obj.getJSONObject("servicio").getString("key"));
                
            new Thread(() -> {

                String token;
                
                if(!tokens.isEmpty()){
                    for (int i = 0; i < JSONObject.getNames(tokens).length; i++) {
                        token = tokens.getJSONObject(JSONObject.getNames(tokens)[i]).getString("token");
                        JSONObject dta = new JSONObject();
                        if(data.has("data") && !data.isNull("data")){
                            dta = data.getJSONObject("data");
                        }
                        if(data.has("url_image") && !data.isNull("url_image")){
                            Firebase.send(token, data.getString("descripcion"), data.getString("observacion"), dta,data.getString("url_image"));
                        }else{
                            Firebase.send(token, data.getString("descripcion"), data.getString("observacion"),dta);
                        }
                    }
                }
            }).start();;
            
            

            
            obj.put("data", tokens.isEmpty()?0:JSONObject.getNames(tokens).length);
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void registroAll(JSONObject obj, SSSessionAbstract session) {
        try {
            JSONObject firebase = obj.getJSONObject("firebase");

            JSONObject firebaseToken = FirebaseToken.get(firebase.getString("token"));
            
            if(firebaseToken==null || firebaseToken.isEmpty()){
                firebaseToken = firebase;
                firebaseToken.put("key", SUtil.uuid());
                firebaseToken.put("key_servicio", obj.getJSONObject("servicio").getString("key"));
                firebaseToken.put("estado", 1);
                firebaseToken.put("fecha_on", SUtil.now());
                //data.put("key_usuario", obj.getString("key_usuario"));
                SPGConect.insertArray(COMPONENT, new JSONArray().put(firebaseToken));
            }else{
                firebaseToken = firebaseToken.getJSONObject(JSONObject.getNames(firebaseToken)[0]);
            }
            String key_usuario="";
            if(firebase.has("key_usuario") && !firebase.isNull("key_usuario")){
                key_usuario = firebase.getString("key_usuario");
                firebaseToken.put("key_usuario", key_usuario);
            }else{
                firebaseToken.put("key_usuario", "");
            }

            if(firebase.has("descripcion")){
                firebaseToken.put("descripcion", firebase.getString("descripcion"));
            }

            firebaseToken.put("ultima_conexion", SUtil.now());
            SPGConect.editObject(COMPONENT, firebaseToken);

            Firebase.send(firebase.getString("token"), "PRobando", "Tranquilo solo es una prueba", new JSONObject());

            obj.put("data", firebaseToken);
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void editar(JSONObject obj, SSSessionAbstract session) {
        try {
            JSONObject data = obj.getJSONObject("data");
            SPGConect.editObject(COMPONENT, data);
            obj.put("data", data);
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getMessage());
            e.printStackTrace();
        }
    }
    

}
