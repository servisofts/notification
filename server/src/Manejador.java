import Servisofts.SConsole;
import component.FirebaseServer;
import component.FirebaseToken;

import org.json.JSONObject;
import Server.SSSAbstract.SSSessionAbstract;

public class Manejador {
    public static void onMessage(JSONObject obj, SSSessionAbstract session) {
        if (session != null) {
            SConsole.log(session.getIdSession(), "\t|\t", obj.getString("component"), obj.getString("type"));
        } else {
            SConsole.log("NoSocketSession", "\t|\t", obj.getString("component"), obj.getString("type"));
        }
        if (obj.isNull("component")) {
            return;
        }
        switch (obj.getString("component")) {
            case FirebaseServer.COMPONENT:
                FirebaseServer.onMessage(obj, session);
                break;
            case FirebaseToken.COMPONENT:
                FirebaseToken.onMessage(obj, session);
                break;
        }
    }
}
