import DPA, { connect } from 'servisofts-page';
import { Parent } from "."
import Model from '../../Model';
import Item from './Item';
import { SNavigation } from 'servisofts-component';

class index extends DPA.list {
    constructor(props) {
        super(props, {
            Parent: Parent,
            item: Item,
            params: ["key_servicio"],
            excludes: ["key", "fecha_on", "estado",],
            onRefresh: (resolve) => {
                Parent.model.Action.CLEAR();
                if (resolve) resolve()
            }
        });
    }
    $allowNew() {
        // return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "new" });
        return true //
    }
    $allowTable() {
        return true //return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "table" });
    }
    $allowAccess() {
        return true //return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "ver" })
    }
    $filter(data) {
        return data.estado != 0
    }
    $order() {
        return [{ key: "ultima_conexion", order: "desc" }]
    }
    $onSelect(obj) {
        SNavigation.navigate("/firebase_token/profile", { pk: obj.key, key_servicio: this.$params.key_servicio });
    }
    $getData() {
        var data = Parent.model.Action.getAll({ key_servicio: this.$params.key_servicio });
        return data;
    }
}
export default connect(index);