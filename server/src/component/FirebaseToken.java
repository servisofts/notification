package component;

import org.json.JSONArray;
import org.json.JSONObject;
import Server.SSSAbstract.SSSessionAbstract;
import Servisofts.SPGConect;
import Servisofts.SUtil;

public class FirebaseToken {
    
    public static final String COMPONENT = "firebase_token";

    public static void onMessage(JSONObject obj, SSSessionAbstract session) {
        switch (obj.getString("type")) {
            case "getByKey":
                getByKey(obj, session);
                break;
            case "getAll":
                getAll(obj, session);
                break;
            case "registro":
                registro(obj, session);
                break;
            case "editar":
                editar(obj, session);
                break;
        }
    }

    public static JSONObject getAll(String key_servicio) {
        try {
            String consulta = "select get_all('" + COMPONENT + "', 'key_servicio', '"+key_servicio+"') as json";
            return SPGConect.ejecutarConsultaObject(consulta);
        } catch (Exception e) {
            return null;
        }
    }

    public static void vencer(){
        try {
                String consulta = "select vencer_token() as json";
            SPGConect.ejecutar(consulta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject get(String token) {
        try {
            String consulta = "select get_by_key('" + COMPONENT + "', 'token', '"+token+"') as json";
            return SPGConect.ejecutarConsultaObject(consulta);
        } catch (Exception e) {
            return null;
        }
    }

    public static void getAll(JSONObject obj, SSSessionAbstract session) {
        try {
            String consulta = "select get_all('" + COMPONENT + "', 'key_Servicio', '"+obj.getString("key_servicio")+"') as json";
            JSONObject data = SPGConect.ejecutarConsultaObject(consulta);
            obj.put("data", data);
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static void getByKey(JSONObject obj, SSSessionAbstract session) {
        try {
            String consulta = "select get_by_key('" + COMPONENT + "', '"+obj.getString("key")+"') as json";
            JSONObject data = SPGConect.ejecutarConsultaObject(consulta);
            obj.put("data", data);
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static void registro(JSONObject obj, SSSessionAbstract session) {
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

            Firebase.send(firebase.getString("token"), "PRobando", "Tranquilo solo es una prueba");

            obj.put("data", firebaseToken);
            obj.put("estado", "exito");

            
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getLocalizedMessage());
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
            obj.put("error", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}
