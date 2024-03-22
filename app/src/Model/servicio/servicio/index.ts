import { SModel } from "servisofts-model";
import Action from "./Action";
import Reducer from "./Reducer";

export default new SModel<Action, Reducer>({
    info: {
        component: "servicio",
        key_servicio: "5f0df472-82d1-4d81-935e-4b68a354e29f"
    },
    Columns: {
        "descripcion": { type: "text", notNull: true, editable: true },
        "observacion": { type: "text", editable: true },
        "key": { type: "text", pk: true },
        "fecha_on": { type: "timestamp", label: "Fecha de creacion" },
        "estado": { type: "integer" },
        "key_usuario": { type: "text", fk: "usuario" },

    },
    Action,
    Reducer,
});