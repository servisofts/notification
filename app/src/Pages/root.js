import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SHr, SIcon, SPage, SText, STheme, SView } from 'servisofts-component';
import { MenuButtom, MenuPages } from 'servisofts-rn-roles_permisos';
class index extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }


    render() {
        return (
            <SPage title={''}>
                <SHr height={32} />
                <SView col={"xs-12"} row>
                    <MenuButtom label="Servicios" url='/servicio' icon={<SIcon name='Cheque' />} />
                    <MenuButtom label="Ajustes" url='/ajustes' icon={<SIcon name='Ajustes' />} />
                </SView>
            </SPage>
        );
    }
}
const initStates = (state) => {
    return { state }
};
export default connect(initStates)(index);