import { Component } from '@angular/core';
import { BaseService } from '../base.service';//importáljuk a base.service-t
import { Kolcsonzok } from 'src/Kolcsonzok';//importáljuk a Kolcsonzok osztályt ami tartalmazza az adatok formátumát

@Component({
  selector: 'app-kolcsonzok',
  templateUrl: './kolcsonzok.component.html',
  styleUrls: ['./kolcsonzok.component.css']
})
export class KolcsonzokComponent {

  kolcsonzok: Kolcsonzok[] = [];//tömb a kölcsönzők tárolására
  visible = false;

  constructor(private baseService: BaseService) {//konstruktorban hiávjuk meg a base.service-t
    this.baseService.getKolcsonzok().subscribe(kolcsonzok => {//az apin keresztül visszaadja az adatokat
      this.kolcsonzok = kolcsonzok;//és eltárolja a tömbbe
    })
  }

  kolcsonzesek(id: number) {//ez végzi a kölcsönzések megjelenítést/elrejtést
    this.visible = true;
    var div = document.querySelector("#toggle" + id);
    if (div) {
      div.classList.toggle("d-none");
    }
  }
}
