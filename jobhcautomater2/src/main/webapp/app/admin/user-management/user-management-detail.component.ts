import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { User } from 'app/core';
import { EmployeeDetailsService } from 'app/entities/employee-details';
import { FreelancerDetailsService } from 'app/entities/freelancer-details';

@Component({
    selector: 'jhi-user-mgmt-detail',
    templateUrl: './user-management-detail.component.html'
})
export class UserMgmtDetailComponent implements OnInit {
    user: User;
    employee: any;
    freelancer: any;
    constructor(
        private route: ActivatedRoute,
        private employeeDetailsService: EmployeeDetailsService,
        private freelancerDetailsService: FreelancerDetailsService,
        private router: Router
    ) {}

    ngOnInit() {
        this.route.data.subscribe(({ user }) => {
            this.user = user.body ? user.body : user;
        });
    }

    detailEmployee(idUser: any) {
        this.employeeDetailsService.getEmployeeByUser(idUser).subscribe(response => {
            console.log(response);
            this.employee = response;

            console.log('method 2');
            this.router.navigate(['employee-details', this.employee.id, 'view']);

            console.log('role user=');
            console.log(this.user.id);
        });
    }
    detailFreelancer(idUser: any) {
        this.freelancerDetailsService.getFreelancerByUser(idUser).subscribe(response => {
            console.log(response);
            this.freelancer = response;

            console.log('method 2');
            this.router.navigate(['freelancer-details', this.freelancer.id, 'view']);

            console.log('role user=');
            console.log(this.user.id);
        });
    }

    detailUser(id: any) {
        for (let i = 0; i < this.user.authorities.length; i++) {
            if (this.user.authorities[i] === 'ROLE_EMPLOYEE') {
                this.detailEmployee(id);
            } else if (this.user.authorities[i] === 'ROLE_FREELANCER') {
                this.detailFreelancer(id);
            }
        }
    }
}
