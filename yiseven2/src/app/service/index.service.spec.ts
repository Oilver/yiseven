import { TestBed } from '@angular/core/testing';

import { IndexService } from './index.service';

describe('IndexService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IndexService = TestBed.get(IndexService);
    expect(service).toBeTruthy();
  });
});
