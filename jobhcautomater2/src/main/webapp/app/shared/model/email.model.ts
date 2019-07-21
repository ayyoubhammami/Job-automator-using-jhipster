export const enum EmailingCategory {
    JOB = 'JOB',
    NEWS = 'NEWS'
}

export interface IEmail {
    id?: number;
    name?: string;
    profession?: string;
    email?: string;
    category?: EmailingCategory;
}

export class Email implements IEmail {
    constructor(
        public id?: number,
        public name?: string,
        public profession?: string,
        public email?: string,
        public category?: EmailingCategory
    ) {}
}
