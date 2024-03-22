import { SHr, SIcon, SImage, SList, SLoad, SNavigation, SText, SView } from 'servisofts-component';
import DPA, { connect } from 'servisofts-page';
import { Parent } from ".."
import { MenuButtom } from 'servisofts-rn-roles_permisos';

class index extends DPA.profile {
    constructor(props) {
        super(props, {
            Parent: Parent,
            excludes: ["key", "key_usuario", "key_servicio",],

        });
        this.state = {
        }
    }
    $allowEdit() {
        return true // return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "edit" })
    }
    $allowDelete() {
        return true //return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "delete" })
    }
    $allowAccess() {
        return true //return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "ver" })
    }
    $getData() {
        return Parent.model.Action.getByKey(this.pk);
    }

    $footer() {
        return <SView col={"xs-12"} >
            <SHr />
            <SView col={"xs-12"} row>
                <MenuButtom label="Tokens" url='/firebase_token' params={{ key_servicio: this.data?.key_servicio }} icon={<SIcon name='Alert' />} />
                <SView width={8} />
                <MenuButtom label="Notificar" url='/notification' icon={<SIcon name='Add' />} />
            </SView>
        </SView>
    }

}
export default connect(index);