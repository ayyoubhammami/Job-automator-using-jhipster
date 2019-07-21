/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { EmployeeDetailsUpdateComponent } from 'app/entities/employee-details/employee-details-update.component';
import { EmployeeDetailsService } from 'app/entities/employee-details/employee-details.service';
import { EmployeeDetails } from 'app/shared/model/employee-details.model';

describe('Component Tests', () => {
    describe('EmployeeDetails Management Update Component', () => {
        let comp: EmployeeDetailsUpdateComponent;
        let fixture: ComponentFixture<EmployeeDetailsUpdateComponent>;
        let service: EmployeeDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [EmployeeDetailsUpdateComponent]
            })
                .overrideTemplate(EmployeeDetailsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmployeeDetailsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeDetailsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EmployeeDetails(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.employeeDetails = entity;
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
                    const entity = new EmployeeDetails();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.employeeDetails = entity;
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
