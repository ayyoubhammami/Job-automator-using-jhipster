export interface IProfilCondidate {
    id?: number;
    firstName?: string;
    lastName?: string;
    phoneNumber?: string;
    email?: string;
    facebook?: string;
    linkedIn?: string;
}

export class ProfilCondidate implements IProfilCondidate {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public phoneNumber?: string,
        public email?: string,
        public facebook?: string,
        public linkedIn?: string
    ) {}
}
