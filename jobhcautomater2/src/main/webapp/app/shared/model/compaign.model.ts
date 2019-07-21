import { ISocialMedia } from 'app/shared/model//social-media.model';

export interface ICompaign {
    id?: number;
    ref?: string;
    title?: string;
    desciption?: string;
    imgContentType?: string;
    img?: any;
    socialMedias?: ISocialMedia[];
}

export class Compaign implements ICompaign {
    constructor(
        public id?: number,
        public ref?: string,
        public title?: string,
        public desciption?: string,
        public imgContentType?: string,
        public img?: any,
        public socialMedias?: ISocialMedia[]
    ) {}
}
