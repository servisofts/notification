import { SPage } from "servisofts-component";

import Model from "../../Model";
const model = Model.servicio;
import list from './list'
import profile from './profile'
export const Parent = {
    name: "servicio",
    path: `/servicio`,
    model
}
export default SPage.combinePages(Parent.name, {
    "": list,
    profile
})
