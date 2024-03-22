import { SModel } from "servisofts-model";
import servicio from "./servicio";
import Usuario from "servisofts-rn-usuario";

import notification from "./notification";
const Model = {
    ...servicio,
    ...notification,
    // ...empresa,
}


export default {
    ...Model,
    ...SModel.declare(Model)
}