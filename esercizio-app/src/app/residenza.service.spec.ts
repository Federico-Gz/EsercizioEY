import { TestBed } from '@angular/core/testing';

import { ResidenzaService } from './residenza.service';

describe('ResidenzaService', () => {
  let service: ResidenzaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResidenzaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
