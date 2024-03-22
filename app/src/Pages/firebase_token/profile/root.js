import { SHr, SIcon, SImage, SList, SLoad, SNavigation, SText, SView } from 'servisofts-component';
import DPA, { connect } from 'servisofts-page';
import { Parent } from ".."
import Item from '../Item';
import { MenuButtom } from 'servisofts-rn-roles_permisos';
import Model from '../../../Model';

class index extends DPA.profile {
    constructor(props) {
        super(props, {
            Parent: Parent,
            params: ["key_servicio"],
            excludes: ["key", "key_usuario", "key_servicio",],
            item: Item
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
        return Parent.model.Action.getByKey(this.pk, { key_servicio:this.$params.key_servicio });
    }


    $footer() {
        return <SView col={"xs-12"} >
            <SHr />
            <SView col={"xs-12"} row>
                <MenuButtom label="Test notify" icon={<SIcon name='Alert' />} onPress={() => {
                    Model.notification.Action.registro({
                        data: {
                            descripcion: "Test desc",
                            observacion: "Test obs",
                            token: this.data?.token
                        }
                    })
                }} />

            </SView>
        </SView>
    }



}
export default connect(index);