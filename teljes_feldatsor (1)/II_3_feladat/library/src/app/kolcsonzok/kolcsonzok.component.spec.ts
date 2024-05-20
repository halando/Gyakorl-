import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KolcsonzokComponent } from './kolcsonzok.component';

describe('KolcsonzokComponent', () => {
  let component: KolcsonzokComponent;
  let fixture: ComponentFixture<KolcsonzokComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KolcsonzokComponent]
    });
    fixture = TestBed.createComponent(KolcsonzokComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
