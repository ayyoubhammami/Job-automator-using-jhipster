import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFreelancerDetails } from 'app/shared/model/freelancer-details.model';
import { FreelancerDetailsService } from './freelancer-details.service';

@Component({
    selector: 'jhi-freelancer-details-delete-dialog',
    templateUrl: './freelancer-details-delete-dialog.component.html'
})
export class FreelancerDetailsDeleteDialogComponent {
    freelancerDetails: IFreelancerDetails;

    constructor(
        private freelancerDetailsService: FreelancerDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.freelancerDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'freelancerDetailsListModification',
                content: 'Deleted an freelancerDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-freelancer-details-delete-popup',
    template: ''
})
export class FreelancerDetailsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ freelancerDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FreelancerDetailsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.freelancerDetails = freelancerDetails;
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
