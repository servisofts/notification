package component;
import org.json.JSONObject;

public class Util {

    public static String getStringJSONObject(JSONObject obj, String key) {
        if(obj == null || obj.isEmpty()) {
            return null;
        }

        if(obj.has(key) && !obj.isNull(key)) {
            return obj.getString(key);
        }

        return null;
    }
}
