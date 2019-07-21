/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { InternalFilterDetailComponent } from 'app/entities/internal-filter/internal-filter-detail.component';
import { InternalFilter } from 'app/shared/model/internal-filter.model';

describe('Component Tests', () => {
    describe('InternalFilter Management Detail Component', () => {
        let comp: InternalFilterDetailComponent;
        let fixture: ComponentFixture<InternalFilterDetailComponent>;
        const route = ({ data: of({ internalFilter: new InternalFilter(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [InternalFilterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InternalFilterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InternalFilterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.internalFilter).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
