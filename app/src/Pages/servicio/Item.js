import DPA, { connect } from 'servisofts-page';
import { Parent } from "."
import { SButtom, SHr, SIcon, SImage, SList, SLoad, SNavigation, SPage, SText, STheme, SView } from 'servisofts-component';

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
        const { certificado, descripcion, estado, ip, ip_public, nombre, puerto, puerto_ws, puerto_http, puerto_arduino } = this.data;
        return <SView col={"xs-12"}>
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
}
export default connect(index);