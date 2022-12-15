import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpInversorComponent } from './pop-up-inversor.component';

describe('PopUpInversorComponent', () => {
  let component: PopUpInversorComponent;
  let fixture: ComponentFixture<PopUpInversorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopUpInversorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopUpInversorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
