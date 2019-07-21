import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmployeeDetails } from 'app/shared/model/employee-details.model';
import { EmployeeDetailsService } from './employee-details.service';

@Component({
    selector: 'jhi-employee-details-delete-dialog',
    templateUrl: './employee-details-delete-dialog.component.html'
})
export class EmployeeDetailsDeleteDialogComponent {
    employeeDetails: IEmployeeDetails;

    constructor(
        private employeeDetailsService: EmployeeDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.employeeDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'employeeDetailsListModification',
                content: 'Deleted an employeeDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-employee-details-delete-popup',
    template: ''
})
export class EmployeeDetailsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ employeeDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EmployeeDetailsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.employeeDetails = employeeDetails;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
