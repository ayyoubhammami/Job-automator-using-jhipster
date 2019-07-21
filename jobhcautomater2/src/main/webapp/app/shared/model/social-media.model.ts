import { ICompaign } from 'app/shared/model//compaign.model';

export const enum SocialMediaType {
    Facebook = 'Facebook',
    Linkedin = 'Linkedin',
    Twitter = 'Twitter',
	Email = 'Email'
}

export interface ISocialMedia {
    id?: number;
    value?: SocialMediaType;
    compaigns?: ICompaign[];
}

export class SocialMedia implements ISocialMedia {
    constructor(public id?: number, public value?: SocialMediaType, public compaigns?: ICompaign[]) {}
}
