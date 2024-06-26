import DPA, { connect } from 'servisofts-page';
import { Parent } from '.';
import { SNavigation, SPopup } from 'servisofts-component';
import Model from '../../../Model';

class index extends DPA.new {
    constructor(props) {
        super(props, {
            Parent: Parent,
            excludes: ["fecha_on", "key_usuario", "estado", "lat", "lng"]
        });
    }
    $allowAccess() {
        return true;
        return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "new" })
    }
    $onSubmit(data) {
        Parent.model.Action.registro({
            data: data,
            key_usuario: "",
        }).then((resp) => {
            SNavigation.goBack();
        }).catch(e => {
            console.error(e);

        })
    }
}

export default connect(index);