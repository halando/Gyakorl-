import { Component, Input, OnInit } from '@angular/core';
import { BaseService } from '../base.service';//importáljuk a base.service-t



@Component({
  selector: 'app-kolcsonzesek',
  templateUrl: './kolcsonzesek.component.html',
  styleUrls: ['./kolcsonzesek.component.css']
})
export class KolcsonzesekComponent implements OnInit {
  @Input() id: number | undefined;// ez fogadja a szülő komponenstől az aktuális kölcsönző id-jét

  kolcsonzesek: any;
  ujIro: string | undefined;
  ujMufaj: string | undefined;
  ujCim: string | undefined;

  iro: string | undefined;
  mufaj: string | undefined;
  cim: string | undefined;

  constructor(private baseService: BaseService) {
  }
  ngOnInit() {

    if (this.id !== undefined) {
      this.baseService.getKolcsonzesek(this.id).subscribe(kolcsonzesek => {
        this.kolcsonzesek = kolcsonzesek;
      });// betöltjük a kölcsönzött könyveket
    }
  }

  mentes() {
    if (this.id !== undefined) {
      const kolcsonzes = {
        id: 0,
        kolcsonzoId: (this.id).toString() || "0",
        iro: this.ujIro || '',
        mufaj: this.ujMufaj || '',
        cim: this.ujCim || ''
      }//a html-től kapott adatokat mentjük az api-val
      this.baseService.createKolcsonzes(kolcsonzes).subscribe();
      this.ujratoltes(this.id);// hogy egyből lássuk a változást
    }
  }

  javitas(index: any) {
    const row = this.kolcsonzesek[index];//a könyveknek az a sora amit javítani akarunk
    const kId = row.id;
    this.baseService.updateKolcsonzes(kId, row).subscribe();
  }

  torles(index: any) {
    const id = this.kolcsonzesek[index].id;
    this.baseService.deleteKolcsonzes(id).subscribe();
    this.ujratoltes(this.id);
  }

  ujratoltes(id: any) {//ez tölti újra a könyveket
    this.baseService.getKolcsonzesek(id).subscribe(kolcsonzesek => {
      this.kolcsonzesek = kolcsonzesek;
    });
    this.ujIro = '';
    this.ujMufaj = '';
    this.ujCim = '';
  }
}
