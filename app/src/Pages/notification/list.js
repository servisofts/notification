import DPA, { connect } from 'servisofts-page';
import { Parent } from "."
import Model from '../../Model';

class index extends DPA.list {
    constructor(props) {
        super(props, {
            Parent: Parent,
            params: ["key_servicio"],
            excludes: ["key", "estado",],
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
    $getData() {
        var data = Parent.model.Action.getAll({ key_servicio: this.$params.key_servicio });
        return data;
    }
}
export default connect(index);