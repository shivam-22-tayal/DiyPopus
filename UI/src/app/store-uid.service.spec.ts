import { TestBed } from '@angular/core/testing';

import { StoreUidService } from './store-uid.service';

describe('StoreUidService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StoreUidService = TestBed.get(StoreUidService);
    expect(service).toBeTruthy();
  });
});
