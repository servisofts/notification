import { SAction } from "servisofts-model";
import SSocket from 'servisofts-socket'
export default class Action extends SAction {

    getAllHabilitados() {
        var reducer = this._getReducer();
        var data = reducer.data;
        if (!SSocket.api.servicio) return;
        if (!data) {
            if (reducer.estado == "cargando") return null;
            console.log("pidiedo:" + SSocket.api.servicio + "api");
            SSocket.sendHttp(SSocket.api.servicio + "api", {
                ...this.model.info,
                type: "getAllHabilitados",
                estado: "cargando",
            })
            return null;
        }
        return data;
    }

    getByKey(key: any, extra: {}, _default: any) {
        let data = this.getAllHabilitados();
        if (!data) return null;
        return data[key]
    }
}