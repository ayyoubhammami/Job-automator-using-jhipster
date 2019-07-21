/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { EmployeeDetailsDetailComponent } from 'app/entities/employee-details/employee-details-detail.component';
import { EmployeeDetails } from 'app/shared/model/employee-details.model';

describe('Component Tests', () => {
    describe('EmployeeDetails Management Detail Component', () => {
        let comp: EmployeeDetailsDetailComponent;
        let fixture: ComponentFixture<EmployeeDetailsDetailComponent>;
        const route = ({ data: of({ employeeDetails: new EmployeeDetails(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [EmployeeDetailsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EmployeeDetailsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeeDetailsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.employeeDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
