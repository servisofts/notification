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
            <SPage title={'Ajustes'}>
                <SHr height={32} />
                {/* <MenuPages path={"/ajustes/"} > */}
                <SView col={"xs-12"} row>
                    <MenuButtom label="Enviroments" url='/ajustes/enviroment' icon={<SIcon name='Ajustes' />} />
                    <MenuButtom label={STheme.getTheme() == "default" ? "Dark" : "Withe"} icon={STheme.getTheme() == "default" ? <SIcon name='Moon' /> : <SIcon name='Sun' fill={"#fff"} />} onPress={() => {
                        STheme.change()
                    }} />
                </SView>
                {/* </MenuPages> */}
            </SPage>
        );
    }
}
const initStates = (state) => {
    return { state }
};
export default connect(initStates)(index);