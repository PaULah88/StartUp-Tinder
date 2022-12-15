import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpEntrepreneurComponent } from './pop-up-entrepreneur.component';

describe('PopUpEntrepreneurComponent', () => {
  let component: PopUpEntrepreneurComponent;
  let fixture: ComponentFixture<PopUpEntrepreneurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopUpEntrepreneurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopUpEntrepreneurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
