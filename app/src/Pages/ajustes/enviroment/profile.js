import DPA, { connect } from 'servisofts-page';
import { Parent } from "."
import { SHr, SList, SLoad, SText, SView } from 'servisofts-component';
import Model from '../../../Model';

class index extends DPA.profile {
    constructor(props) {
        super(props, {
            Parent: Parent,
            excludes: ["key_usuario", "estado", "lat", "lng", "key_empresa"],
            // item: item
        });
    }
    $allowEdit() {
        return true;
        return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "edit" })
    }
    $allowDelete() {
        return true;
        return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "delete" })
    }
    $allowAccess() {
        return true;
        return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "ver" })
    }
    $getData() {
        return Parent.model.Action.getByKey(this.pk);
    }
}
export default connect(index);