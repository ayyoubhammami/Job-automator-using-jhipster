/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { SourceComponent } from 'app/entities/source/source.component';
import { SourceService } from 'app/entities/source/source.service';
import { Source } from 'app/shared/model/source.model';

describe('Component Tests', () => {
    describe('Source Management Component', () => {
        let comp: SourceComponent;
        let fixture: ComponentFixture<SourceComponent>;
        let service: SourceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [SourceComponent],
                providers: []
            })
                .overrideTemplate(SourceComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SourceComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SourceService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Source(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sources[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
