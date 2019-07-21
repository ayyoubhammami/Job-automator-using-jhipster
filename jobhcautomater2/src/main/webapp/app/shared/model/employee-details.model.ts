import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IEmployeeDetails {
    id?: number;
    phone1?: string;
    phone2?: string;
    cin?: number;
    delevredDate?: Moment;
    cnss?: number;
    married?: boolean;
    numberOfChildren?: number;
    motorized?: boolean;
    salary?: number;
    hiringDate?: Moment;
    comments?: any;
    user?: IUser;
}

export class EmployeeDetails implements IEmployeeDetails {
    constructor(
        public id?: number,
        public phone1?: string,
        public phone2?: string,
        public cin?: number,
        public delevredDate?: Moment,
        public cnss?: number,
        public married?: boolean,
        public numberOfChildren?: number,
        public motorized?: boolean,
        public salary?: number,
        public hiringDate?: Moment,
        public comments?: any,
        public user?: IUser
    ) {
        this.married = false;
        this.motorized = false;
    }
}
