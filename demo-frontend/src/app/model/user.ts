import { Profile } from "./profile";

export class User{
    
    id: number;
    nif: any;
    name: string;
    surname1: string;
    surname2: string;
    login: string;
    password: any;
    profiles: Profile[];

}