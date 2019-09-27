import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RunningAccountComponent } from './running-account.component';

describe('RunningAccountComponent', () => {
  let component: RunningAccountComponent;
  let fixture: ComponentFixture<RunningAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RunningAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RunningAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
