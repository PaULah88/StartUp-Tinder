import { BusinessSector } from "./businessSector";
import { RangeInvester } from "./rangeInvester";
import { StartupState } from "./startupState";

export class Inversor {
    id?: number;
    name?: string;
    email?: string;
    idInvesterRange?: RangeInvester;
    idBusinessSector?: BusinessSector;
    idStartUpState?: StartupState;
  }
