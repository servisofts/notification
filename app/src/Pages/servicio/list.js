import DPA, { connect } from 'servisofts-page';
import { Parent } from "."
import Model from '../../Model';
import Item from './Item';
import { SNavigation, SThread } from 'servisofts-component';
import SSocket from 'servisofts-socket'
class index extends DPA.list {
    constructor(props) {
        super(props, {
            Parent: Parent,
            item: Item,
            excludes: ["key", "fecha_on", "estado",],
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

    $onSelect(obj) {
        SNavigation.navigate("/servicio/profile", { pk: obj.key });
    }
    $getData() {
        this.data = Parent.model.Action.getAllHabilitados();
        return this.data;
    }
}
export default connect(index);