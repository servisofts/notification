
import Servisofts.Servisofts;
import component.Task;

public class App {
    public static void main(String[] args) {
        try {
            Servisofts.ManejadorCliente = ManejadorCliente::onMessage;
            Servisofts.Manejador = Manejador::onMessage;
            new Task();
            Servisofts.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}