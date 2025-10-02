import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResidenzaFormComponent } from './residenza-form.component';

describe('ResidenzaFormComponent', () => {
  let component: ResidenzaFormComponent;
  let fixture: ComponentFixture<ResidenzaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResidenzaFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ResidenzaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
