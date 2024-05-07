import Servisofts.SConsole;
import component.Enviroment;
import component.FirebaseServer;
import component.FirebaseToken;
import component.Notification;
import component.NotificationDefault;

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
            case FirebaseServer.COMPONENT: FirebaseServer.onMessage(obj, session); break;
            case FirebaseToken.COMPONENT: FirebaseToken.onMessage(obj, session); break;
            case Notification.COMPONENT: Notification.onMessage(obj, session); break;
            case NotificationDefault.COMPONENT: NotificationDefault.onMessage(obj, session); break;
            case Enviroment.COMPONENT: Enviroment.onMessage(obj, session); break;
        }
    }
}
