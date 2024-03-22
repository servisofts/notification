import { SPage } from "servisofts-component";

import root from "./root";
import enviroment from "./enviroment";
export default SPage.combinePages("ajustes",
    {
        "": root,
        ...enviroment,
    }
)