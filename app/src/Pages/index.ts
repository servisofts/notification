import { SPage, SPageListProps } from 'servisofts-component';

import Root from './root';
import ajustes from './ajustes';
import servicio from './servicio';
import firebase_server from './firebase_server';
import firebase_token from './firebase_token';
import notification from "./notification"
export default SPage.combinePages("/", {
    "": Root,
    ...servicio,
    ...ajustes,
    ...firebase_server,
    ...firebase_token,
    ...notification
});