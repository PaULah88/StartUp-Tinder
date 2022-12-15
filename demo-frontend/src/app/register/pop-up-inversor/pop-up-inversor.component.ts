import { Component, OnInit, ViewChild } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { InversoresDataSource } from '../../model/datasource/inversores.datasource';
import { InversorService } from '../../services/inversor.service';
import { AnyPageFilter, AnyField } from '../../model/rest/filter';
import { Inversor } from '../../model/inversor';



@Component({
  selector: 'app-pop-up-inversor',
  templateUrl: './pop-up-inversor.component.html',
  styleUrls: ['./pop-up-inversor.component.scss']
})
export class PopUpInversorComponent implements OnInit {

  //variable MatchBtn
  MatchBtn: boolean = true;
  BubblyButton: boolean =false;

    // INtento de match
    selectedInversor?: Inversor


  dataSource: InversoresDataSource;

  public inversores = [];

  public page : number;

  fields = [
    'id',
    'name',
    'email',
    'idInvesterRange',
    'idBusinessSector',
    'idStartUpState',
  ];

  constructor( private inversorService: InversorService, private translate: TranslateService) { }


  ngOnInit(): void {
    this.dataSource = new InversoresDataSource(this.inversorService);
    const pageFilter = new AnyPageFilter(
      '', this.fields.map((field) => new AnyField(field)),
      0,
      24,
      'name'
    );

    this.dataSource.getInversores(pageFilter);
    this.getConsola(pageFilter);
  }

  getConsola(pageFilter : AnyPageFilter){

    this.inversorService.getInversores(pageFilter).subscribe((response) => {
      this.inversores = response.data;
      console.log('>>>>NOMBRE INVERSOR: ', this.inversores[0].name);

      console.log('>>>> DATA INVERSORES', response.data);


    })

  }

onclick() {
  this.MatchBtn = !this.MatchBtn;
}


onSelected(inversor: Inversor): void {
  this.selectedInversor = inversor;
 console.log('>>>>>Sselec:', inversor);

}


changeProgresValue(row: string) {
  if (row == 'Pre-Seed') {
    return 20;
  } else if (row == 'Seed') {
    return 40;
  } else if (row == 'Early Stage') {
    return 60;
  } else if (row == 'Growth Stage') {
    return 80;
  } else if (row == 'Scaleup') {
    return 90;
  } else {
    return 100;
  }
}


}
