import { Moment } from 'moment';
import { IJob } from 'app/shared/model//job.model';
import { IFreelancerDetails } from 'app/shared/model//freelancer-details.model';
import { IEmployeeDetails } from 'app/shared/model//employee-details.model';

export interface IAffectation {
    id?: number;
    date?: Moment;
    job?: IJob;
    freelancerDetails?: IFreelancerDetails[];
    employeeDetails?: IEmployeeDetails[];
}

export class Affectation implements IAffectation {
    constructor(
        public id?: number,
        public date?: Moment,
        public job?: IJob,
        public freelancerDetails?: IFreelancerDetails[],
        public employeeDetails?: IEmployeeDetails[]
    ) {}
}
