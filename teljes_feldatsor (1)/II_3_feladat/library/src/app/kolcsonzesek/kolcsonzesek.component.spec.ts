import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KolcsonzesekComponent } from './kolcsonzesek.component';

describe('KolcsonzesekComponent', () => {
  let component: KolcsonzesekComponent;
  let fixture: ComponentFixture<KolcsonzesekComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KolcsonzesekComponent]
    });
    fixture = TestBed.createComponent(KolcsonzesekComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
