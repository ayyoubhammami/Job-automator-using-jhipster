/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { InternalFilterUpdateComponent } from 'app/entities/internal-filter/internal-filter-update.component';
import { InternalFilterService } from 'app/entities/internal-filter/internal-filter.service';
import { InternalFilter } from 'app/shared/model/internal-filter.model';

describe('Component Tests', () => {
    describe('InternalFilter Management Update Component', () => {
        let comp: InternalFilterUpdateComponent;
        let fixture: ComponentFixture<InternalFilterUpdateComponent>;
        let service: InternalFilterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [InternalFilterUpdateComponent]
            })
                .overrideTemplate(InternalFilterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InternalFilterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InternalFilterService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new InternalFilter(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.internalFilter = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new InternalFilter();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.internalFilter = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
