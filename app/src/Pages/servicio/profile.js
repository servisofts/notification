import { SHr, SIcon, SImage, SList, SLoad, SNavigation, SText, SView } from 'servisofts-component';
import DPA, { connect } from 'servisofts-page';
import { Parent } from "."
import { MenuButtom } from 'servisofts-rn-roles_permisos';
import Item from './Item';
class index extends DPA.profile {
    constructor(props) {
        super(props, {
            Parent: Parent,
            item:Item,
            excludes: ["key", "key_usuario", "key_servicio",],

        });
        this.state = {
        }
    }
    $allowEdit() {
        return false // return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "edit" })
    }
    $allowDelete() {
        return false //return Model.usuarioPage.Action.getPermiso({ url: Parent.path, permiso: "delete" })
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
                <MenuButtom label="Api Key" url='/firebase_server' params={{ key_servicio: this.pk }} icon={<SIcon name='Ajustes' />} />
                <SView width={8} />
                <MenuButtom label="Tokens" url='/firebase_token' params={{ key_servicio: this.pk }} icon={<SIcon name='Tranfer' />} />
                <SView width={8} />
                <MenuButtom label="Notificaciones" url='/notification' params={{ key_servicio: this.pk }} icon={<SIcon name='Profanity' />} />
            </SView>
        </SView>
    }



}
export default connect(index);