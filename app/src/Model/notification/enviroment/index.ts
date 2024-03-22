import { SModel } from "servisofts-model";
import Action from "./Action";
import Reducer from "./Reducer";

export default new SModel<Action, Reducer>({
    info: {
        component: "enviroment"
    },
    Columns: {
        "key": { type: "text", pk: true },
        "detalle": { type: "text", editable: true },
        "type": { type: "text", editable: true },
        "value": { type: "text", editable: true },
        "fecha_on": { type: "timestamp", label: "Fecha de registro" },
        "estado": { type: "integer" },
    },
    Action,
    Reducer,
});