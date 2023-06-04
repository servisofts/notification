package component;

import org.json.JSONObject;
import Servisofts.SPGConect;

public class Task extends Thread {

    private boolean isRun;

    public Task() {
        this.isRun = true;
        this.start();

    }

    @Override
    public void run() {
        while (isRun) {
            try {
                JSONObject enviroment = Enviroment.getAll(new JSONObject(), null);
                Thread.sleep(Integer.parseInt(enviroment.getJSONObject("main_thread_await").getString("value")));
                
                timeoutToken();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void timeoutToken() {
        try {
            FirebaseToken.vencer();
           System.out.println("vERNCER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
