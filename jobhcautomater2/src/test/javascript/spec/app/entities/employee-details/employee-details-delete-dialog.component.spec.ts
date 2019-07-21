/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { EmployeeDetailsDeleteDialogComponent } from 'app/entities/employee-details/employee-details-delete-dialog.component';
import { EmployeeDetailsService } from 'app/entities/employee-details/employee-details.service';

describe('Component Tests', () => {
    describe('EmployeeDetails Management Delete Component', () => {
        let comp: EmployeeDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<EmployeeDetailsDeleteDialogComponent>;
        let service: EmployeeDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [EmployeeDetailsDeleteDialogComponent]
            })
                .overrideTemplate(EmployeeDetailsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeeDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeeDetailsService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
