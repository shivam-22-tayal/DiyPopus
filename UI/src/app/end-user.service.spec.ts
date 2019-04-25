import { TestBed } from '@angular/core/testing';

import { EndUserService } from './end-user.service';

describe('EndUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EndUserService = TestBed.get(EndUserService);
    expect(service).toBeTruthy();
  });
});
