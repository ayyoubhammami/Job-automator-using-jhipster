export interface ISource {
    id?: number;
    name?: string;
    url?: string;
    publicKey?: string;
    tokenApi?: string;
    searchingUrl?: string;
}

export class Source implements ISource {
    constructor(
        public id?: number,
        public name?: string,
        public url?: string,
        public publicKey?: string,
        public tokenApi?: string,
        public searchingUrl?: string
    ) {}
}
