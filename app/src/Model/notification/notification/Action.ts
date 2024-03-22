import { SAction } from "servisofts-model";
export default class Action extends SAction {

    getAll(extra?: { key_servicio: any } | undefined) {
        let reducer = this._getReducer();
        if (extra?.key_servicio != reducer.key_servicio) {
            reducer.data = null;
            reducer.key_servicio = extra?.key_servicio;
        }
        return super.getAll(extra)
    }

}