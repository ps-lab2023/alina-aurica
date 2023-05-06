import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GiftCardPageComponent } from './gift-card-page.component';

describe('GiftCardPageComponent', () => {
  let component: GiftCardPageComponent;
  let fixture: ComponentFixture<GiftCardPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GiftCardPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GiftCardPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
