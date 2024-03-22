import { SModel } from "servisofts-model";
import Action from "./Action";
import Reducer from "./Reducer";

export default new SModel<Action, Reducer>({
    info: {
        component: "firebase_token"
    },
    Columns: {

        "key": { type: "text", pk: true },
        "key_usuario": { type: "text", fk: "usuario" },
        "fecha_on": { type: "timestamp", label: "Fecha de creacion" },
        "ultima_conexion": { type: "timestamp", label: "Fecha de conexion" },
        "estado": { type: "integer" },
        "token": { type: "text", notNull: true, },
        "app": { type: "text", notNull: true, },
        "platform": { type: "text",},
        "key_servicio": { type: "text", notNull: true, },
        "descripcion": { type: "text", },
    },
    Action,
    Reducer,
});