import { TestBed } from '@angular/core/testing';

import { EndUserLinkService } from './end-user-link.service';

describe('EndUserLinkService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EndUserLinkService = TestBed.get(EndUserLinkService);
    expect(service).toBeTruthy();
  });
});
