import { BusinessSector } from "./businessSector";
import { Entrepreneur } from "./entrepreneur";
import { StartupState } from "./startupState";

export class Startup {
  id?: number;
  name?: string;
  email?: string;
  description?: string;
  idBusinessSector?: BusinessSector;
  idStartupState?:StartupState;
  anualInvoicing?: number;
  fundationYear?: Date;
  idEntrepreneur?: Entrepreneur;
}
