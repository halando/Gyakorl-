import { Kolcsonzesek } from "./Kolcsonzesek";
export interface Kolcsonzok {
    id: number;
    nev: string;
    szulIdo: string;
    kolcsonzesek: Kolcsonzesek[];
}