import { SModel } from "servisofts-model";
import Action from "./Action";
import Reducer from "./Reducer";

export default new SModel<Action, Reducer>({
    info: {
        component: "firebase_server"
    },
    Columns: {
        
        "key": { type: "text", pk: true },
        "key_usuario": { type: "text", fk: "usuario" },
        "fecha_on": { type: "timestamp", label: "Fecha de creacion" },
        "estado": { type: "integer" },
        "descripcion": { type: "text", notNull: true, editable: true },
        "key_servicio": { type: "text", notNull: true, fk: "servicio" },
        "key_server": { type: "text", notNull: true, editable: true },
        

    },
    Action,
    Reducer,
});