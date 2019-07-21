import { ICandidature } from 'app/shared/model//candidature.model';

export interface IPost {
    id?: number;
    ref?: string;
    title?: string;
    desciption?: string;
    photoContentType?: string;
    photo?: any;
    posts?: ICandidature[];
}

export class Post implements IPost {
    constructor(
        public id?: number,
        public ref?: string,
        public title?: string,
        public desciption?: string,
        public photoContentType?: string,
        public photo?: any,
        public posts?: ICandidature[]
    ) {}
}
