import React, { Component } from 'react';
import { connect } from 'react-redux';
import { SButtom, SHr, SIcon, SImage, SList, SLoad, SNavigation, SPage, SText, STheme, SView } from 'servisofts-component';
import SSocket from 'servisofts-socket'
import Config from '../../Config';
class index extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    componentDidMount() {
        this.getData();
    }

    handleSelect(obj) {
        SNavigation.navigate("/servicio/profile", { key_servicio: obj.key });
    }
    getData() {
        this.setState({ loading: true })
        SSocket.sendHttpAsync(SSocket.api.servicio + "api", {
            component: "servicio",
            type: "getAllHabilitados",
            estado: "cargando",
            key_servicio: "5f0df472-82d1-4d81-935e-4b68a354e29f",
        }).then(e => {
            
            this.setState({ loading: false, data: e.data })
        }).catch(e => {
            this.setState({ loading: false })
            console.error(e);
        })
    }

    Item(obj) {
        const { certificado, descripcion, estado, ip, ip_public, nombre, puerto, puerto_ws, puerto_http, puerto_arduino } = obj;
        return <SView col={"xs-12"} card style={{ padding: 8 }} onPress={() => {
            this.handleSelect(obj);
        }}>
            <SText fontSize={18} bold>{nombre}</SText>
            <SText color={STheme.color.lightGray}>{ip}</SText>
            <SHr />
            <SView row>
                <SText color={STheme.color.lightGray}>{puerto}</SText>
                <SView width={8} />
                <SText color={STheme.color.lightGray}>{puerto_ws}</SText>
                <SView width={8} />
                <SText color={STheme.color.lightGray}>{puerto_http}</SText>
                <SView width={8} />
                <SText color={STheme.color.lightGray}>{puerto_arduino}</SText>
            </SView>
            {/* <SText>{JSON.stringify(obj)}</SText> */}
        </SView>
    }
    getLista() {
        return <SList
            buscador
            data={this.state.data}
            render={this.Item.bind(this)}
        />
    }

    render() {
        return (
            <SPage title={'Servicios activos para [ ' + Config.socket.name + ' ]'} onRefresh={() => {
                this.getData();
            }}>
                <SView col={"xs-12"} center>
                    <SView col={"xs-11 sm-10 md-8 lg-6 xl-4"} center>
                        {this.getLista()}
                    </SView>
                </SView>
            </SPage>
        );
    }
}
const initStates = (state) => {
    return { state }
};
export default connect(initStates)(index);