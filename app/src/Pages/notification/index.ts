import { SPage } from "servisofts-component";
import list from "./list";
import table from "./table";
import _new from "./new";
import profile from "./profile/index";
import edit from "./edit";
import _delete from "./delete";
import Model from "../../Model";
const model = Model.notification;

export const Parent = {
    name: "notification",
    path: `/notification`,
    model
}
export default SPage.combinePages(Parent.name, {
    "": list,
    "list": list,
    "table": table,
    "new": _new,
    ...profile,
    "edit": edit,
    "delete": _delete
})
