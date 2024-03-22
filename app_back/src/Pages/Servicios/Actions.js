import { SNavigation, SStorage } from "servisofts-component";
import SSocket from "servisofts-socket";

export default class Actions {
    static getAll(props) {
        var reducer = props.state.servicioReducer;
        var data = reducer.data;
        if (!data) {
            if (reducer.estado == "cargando") return null;

            SSocket.sendHttp(SSocket.api.servicio + "api", {
                component: "servicio",
                type: "getAllHabilitados",
                estado: "cargando",
                key_servicio: "5f0df472-82d1-4d81-935e-4b68a354e29f",
            })
            return null;
        }
        return data;
    }
    static getByKey(key, props) {
        var data = this.getAll(props);
        if (!data) return null;
        return data[key];
    }
    static getByName(key, props) {
        var data = this.getAll(props);
        if (!data) return null;
        return Object.values(data).find(item => item.nombre == key);
    }

}