import DPA, { connect } from 'servisofts-page';
import { Parent } from '.';
import { SNavigation, SView } from 'servisofts-component';
import Model from '../../Model';

class index extends DPA.new {
    constructor(props) {
        super(props, {
            Parent: Parent,
            params: ["key_servicio"],
            excludes: ["key", "fecha_on", "key_usuario", "key_servicio", "estado"]
        });
    }
    // $allowAccess() {
    //     return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "new" })
    // }

    $inputs() {
        let a = super.$inputs();
        a["key_server"].type = "textArea";
        return a;
    }
    $onSubmit(data) {
        data.key_servicio = this.$params.key_servicio
        Parent.model.Action.registro({
            data: data,
            key_usuario: "",
            // key_usuario: Model.usuario.Action.getKey()
        }).then((resp) => {
            this.$submitFile(resp.data.key);
            SNavigation.goBack();
        }).catch(e => {
            console.error(e);

        })
    }
}

export default connect(index);