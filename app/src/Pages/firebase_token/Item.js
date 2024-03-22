import DPA, { connect } from 'servisofts-page';
import { Parent } from "."
import { SButtom, SDate, SHr, SIcon, SImage, SList, SLoad, SNavigation, SPage, SText, STheme, SView } from 'servisofts-component';

import SSocket from 'servisofts-socket'
class index extends DPA.item {
    constructor(props) {
        super(props, {
            Parent: Parent,
            // row:false
        });
    }
    $getData() {
        var data = super.$getData();
        return data;
    }

    $renderContent() {
        let { token, ultima_conexion, key_usuario, descripcion, platform, app } = this.data;
        if (!token) token = ""
        // let rize = 20;
        // if (token.length > rize) {
        //     token = token.substring(0, rize) + ".........";
        // }
        return <SView col={"xs-12"} center>
            <SText fontSize={16} bold>{descripcion}</SText>
            <SHr />
            {/* <SText fontSize={14}>{}</SText> */}
            <SHr />
            <SText fontSize={8} col={"xs-12"} color={STheme.color.lightGray}>{token}</SText>
            <SHr />
            <SView row col={"xs-12"} center>
                <SText color={STheme.color.lightGray}>{platform}</SText>
                <SView width={8} />
                <SText color={STheme.color.lightGray}>{app}</SText>
                <SView width={8} />
                <SView flex />
                <SText color={STheme.color.lightGray} fontSize={10}>{new SDate(ultima_conexion).toString()}</SText>
                <SView width={8} />
                <SText color={STheme.color.lightGray}>{""}</SText>
            </SView>
            {/* <SText>{JSON.stringify(obj)}</SText> */}
            {!key_usuario ? null : <SText fontSize={12}>Usuario activo: {key_usuario}</SText>}
        </SView>
    }
}
export default connect(index);