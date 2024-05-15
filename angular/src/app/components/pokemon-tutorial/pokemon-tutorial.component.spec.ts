import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonTutorialComponent } from './pokemon-tutorial.component';

describe('PokemonTutorialComponent', () => {
  let component: PokemonTutorialComponent;
  let fixture: ComponentFixture<PokemonTutorialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokemonTutorialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokemonTutorialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
