/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { AffectationComponent } from 'app/entities/affectation/affectation.component';
import { AffectationService } from 'app/entities/affectation/affectation.service';
import { Affectation } from 'app/shared/model/affectation.model';

describe('Component Tests', () => {
    describe('Affectation Management Component', () => {
        let comp: AffectationComponent;
        let fixture: ComponentFixture<AffectationComponent>;
        let service: AffectationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [AffectationComponent],
                providers: []
            })
                .overrideTemplate(AffectationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AffectationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AffectationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Affectation(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.affectations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
