import { Moment } from 'moment';

export const enum Country {
    AALAND_ISLANDS = 'AALAND_ISLANDS',
    AFGHANISTAN = 'AFGHANISTAN',
    ALBANIA = 'ALBANIA',
    ALGERIA = 'ALGERIA',
    AMERICAN_SAMOA = 'AMERICAN_SAMOA',
    ANDORRA = 'ANDORRA',
    ANGOLA = 'ANGOLA',
    ANGUILLA = 'ANGUILLA',
    ANTARCTICA = 'ANTARCTICA',
    ANTIGUA_AND_BARBUDA = 'ANTIGUA_AND_BARBUDA',
    ARGENTINA = 'ARGENTINA',
    ARMENIA = 'ARMENIA',
    ARUBA = 'ARUBA',
    AUSTRALIA = 'AUSTRALIA',
    AUSTRIA = 'AUSTRIA',
    AZERBAIJAN = 'AZERBAIJAN',
    BAHAMAS = 'BAHAMAS',
    BAHRAIN = 'BAHRAIN',
    BANGLADESH = 'BANGLADESH',
    BARBADOS = 'BARBADOS',
    BELARUS = 'BELARUS',
    BELGIUM = 'BELGIUM',
    BELIZE = 'BELIZE',
    BENIN = 'BENIN',
    BERMUDA = 'BERMUDA',
    BHUTAN = 'BHUTAN',
    BOLIVIA = 'BOLIVIA',
    BOSNIA_AND_HERZEGOWINA = 'BOSNIA_AND_HERZEGOWINA',
    BOUVET_ISLAND = 'BOUVET_ISLAND',
    BRAZIL = 'BRAZIL',
    BRITISH_INDIAN_OCEAN_TERRITORY = 'BRITISH_INDIAN_OCEAN_TERRITORY',
    BRUNEI_DARUSSALAM = 'BRUNEI_DARUSSALAM',
    BULGARIA = 'BULGARIA',
    BURKINA_FASO = 'BURKINA_FASO',
    BURUNDI = 'BURUNDI',
    CAMBODIA = 'CAMBODIA',
    CAMEROON = 'CAMEROON',
    CANADA = 'CANADA',
    CAPE_VERDE = 'CAPE_VERDE',
    CAYMAN_ISLANDS = 'CAYMAN_ISLANDS',
    CENTRAL_AFRICAN_REPUBLIC = 'CENTRAL_AFRICAN_REPUBLIC',
    CHAD = 'CHAD',
    CHILE = 'CHILE',
    CHINA = 'CHINA',
    CHRISTMAS_ISLAND = 'CHRISTMAS_ISLAND',
    COCOS_KEELING_ISLANDS = 'COCOS_KEELING_ISLANDS',
    COLOMBIA = 'COLOMBIA',
    COMOROS = 'COMOROS',
    CONGO = 'CONGO',
    COOK_ISLANDS = 'COOK_ISLANDS',
    COSTA_RICA = 'COSTA_RICA',
    COTE_DIVOIRE = 'COTE_DIVOIRE',
    CROATIA = 'CROATIA',
    CUBA = 'CUBA',
    CYPRUS = 'CYPRUS',
    CZECH_REPUBLIC = 'CZECH_REPUBLIC',
    DENMARK = 'DENMARK',
    DJIBOUTI = 'DJIBOUTI',
    DOMINICA = 'DOMINICA',
    DOMINICAN_REPUBLIC = 'DOMINICAN_REPUBLIC',
    EAST_TIMOR = 'EAST_TIMOR',
    ECUADOR = 'ECUADOR',
    EGYPT = 'EGYPT',
    EL_SALVADOR = 'EL_SALVADOR',
    EQUATORIAL_GUINEA = 'EQUATORIAL_GUINEA',
    ERITREA = 'ERITREA',
    ESTONIA = 'ESTONIA',
    ETHIOPIA = 'ETHIOPIA',
    FALKLAND_ISLANDS = 'FALKLAND_ISLANDS',
    FAROE_ISLANDS = 'FAROE_ISLANDS',
    FIJI = 'FIJI',
    FINLAND = 'FINLAND',
    FRANCE = 'FRANCE',
    FRANCE_METROPOLITAN = 'FRANCE_METROPOLITAN',
    FRENCH_GUIANA = 'FRENCH_GUIANA',
    FRENCH_POLYNESIA = 'FRENCH_POLYNESIA',
    FRENCH_SOUTHERN_TERRITORIES = 'FRENCH_SOUTHERN_TERRITORIES',
    GABON = 'GABON',
    GAMBIA = 'GAMBIA',
    GEORGIA = 'GEORGIA',
    GERMANY = 'GERMANY',
    GHANA = 'GHANA',
    GIBRALTAR = 'GIBRALTAR',
    GREECE = 'GREECE',
    GREENLAND = 'GREENLAND',
    GRENADA = 'GRENADA',
    GUADELOUPE = 'GUADELOUPE',
    GUAM = 'GUAM',
    GUATEMALA = 'GUATEMALA',
    GUINEA = 'GUINEA',
    GUINEA_BISSAU = 'GUINEA_BISSAU',
    GUYANA = 'GUYANA',
    HAITI = 'HAITI',
    HEARD_AND_MC_DONALD_ISLANDS = 'HEARD_AND_MC_DONALD_ISLANDS',
    HONDURAS = 'HONDURAS',
    HONG_KONG = 'HONG_KONG',
    HUNGARY = 'HUNGARY',
    ICELAND = 'ICELAND',
    INDIA = 'INDIA',
    INDONESIA = 'INDONESIA',
    IRAN_ISLAMIC_REPUBLIC_OF = 'IRAN_ISLAMIC_REPUBLIC_OF',
    IRAQ = 'IRAQ',
    IRELAND = 'IRELAND',
    ISRAEL = 'ISRAEL',
    ITALY = 'ITALY',
    JAMAICA = 'JAMAICA',
    JAPAN = 'JAPAN',
    JORDAN = 'JORDAN',
    KAZAKHSTAN = 'KAZAKHSTAN',
    KENYA = 'KENYA',
    KIRIBATI = 'KIRIBATI',
    KOREA_DEMOCRATIC_PEOPLES_REPUBLIC_OF = 'KOREA_DEMOCRATIC_PEOPLES_REPUBLIC_OF',
    KOREA_REPUBLIC_OF = 'KOREA_REPUBLIC_OF',
    KUWAIT = 'KUWAIT',
    KYRGYZSTAN = 'KYRGYZSTAN',
    LAO_PEOPLES_DEMOCRATIC_REPUBLIC = 'LAO_PEOPLES_DEMOCRATIC_REPUBLIC',
    LATVIA = 'LATVIA',
    LEBANON = 'LEBANON',
    LESOTHO = 'LESOTHO',
    LIBERIA = 'LIBERIA',
    LIBYAN_ARAB_JAMAHIRIYA = 'LIBYAN_ARAB_JAMAHIRIYA',
    LIECHTENSTEIN = 'LIECHTENSTEIN',
    LITHUANIA = 'LITHUANIA',
    LUXEMBOURG = 'LUXEMBOURG',
    MACAU = 'MACAU',
    MACEDONIA_THE_FORMER_YUGOSLAV_REPUBLIC_OF = 'MACEDONIA_THE_FORMER_YUGOSLAV_REPUBLIC_OF',
    MADAGASCAR = 'MADAGASCAR',
    MALAWI = 'MALAWI',
    MALAYSIA = 'MALAYSIA',
    MALDIVES = 'MALDIVES',
    MALI = 'MALI',
    MALTA = 'MALTA',
    MARSHALL_ISLANDS = 'MARSHALL_ISLANDS',
    MARTINIQUE = 'MARTINIQUE',
    MAURITANIA = 'MAURITANIA',
    MAURITIUS = 'MAURITIUS',
    MAYOTTE = 'MAYOTTE',
    MEXICO = 'MEXICO',
    MICRONESIA_FEDERATED_STATES_OF = 'MICRONESIA_FEDERATED_STATES_OF',
    MOLDOVA_REPUBLIC_OF = 'MOLDOVA_REPUBLIC_OF',
    MONACO = 'MONACO',
    MONGOLIA = 'MONGOLIA',
    MONTSERRAT = 'MONTSERRAT',
    MOROCCO = 'MOROCCO',
    MOZAMBIQUE = 'MOZAMBIQUE',
    MYANMAR = 'MYANMAR',
    NAMIBIA = 'NAMIBIA',
    NAURU = 'NAURU',
    NEPAL = 'NEPAL',
    NETHERLANDS = 'NETHERLANDS',
    NETHERLANDS_ANTILLES = 'NETHERLANDS_ANTILLES',
    NEW_CALEDONIA = 'NEW_CALEDONIA',
    NEW_ZEALAND = 'NEW_ZEALAND',
    NICARAGUA = 'NICARAGUA',
    NIGER = 'NIGER',
    NIGERIA = 'NIGERIA',
    NIUE = 'NIUE',
    NORFOLK_ISLAND = 'NORFOLK_ISLAND',
    NORTHERN_MARIANA_ISLANDS = 'NORTHERN_MARIANA_ISLANDS',
    NORWAY = 'NORWAY',
    OMAN = 'OMAN',
    PAKISTAN = 'PAKISTAN',
    PALAU = 'PALAU',
    PANAMA = 'PANAMA',
    PAPUA_NEW_GUINEA = 'PAPUA_NEW_GUINEA',
    PARAGUAY = 'PARAGUAY',
    PERU = 'PERU',
    PHILIPPINES = 'PHILIPPINES',
    PITCAIRN = 'PITCAIRN',
    POLAND = 'POLAND',
    PORTUGAL = 'PORTUGAL',
    PUERTO_RICO = 'PUERTO_RICO',
    QATAR = 'QATAR',
    REUNION = 'REUNION',
    ROMANIA = 'ROMANIA',
    RUSSIAN_FEDERATION = 'RUSSIAN_FEDERATION',
    RWANDA = 'RWANDA',
    SAINT_KITTS_AND_NEVIS = 'SAINT_KITTS_AND_NEVIS',
    SAINT_LUCIA = 'SAINT_LUCIA',
    SAINT_VINCENT_AND_THE_GRENADINES = 'SAINT_VINCENT_AND_THE_GRENADINES',
    SAMOA = 'SAMOA',
    SAN_MARINO = 'SAN_MARINO',
    SAO_TOME_AND_PRINCIPE = 'SAO_TOME_AND_PRINCIPE',
    SAUDI_ARABIA = 'SAUDI_ARABIA',
    SENEGAL = 'SENEGAL',
    SEYCHELLES = 'SEYCHELLES',
    SIERRA_LEONE = 'SIERRA_LEONE',
    SINGAPORE = 'SINGAPORE',
    SLOVAKIA = 'SLOVAKIA',
    SLOVENIA = 'SLOVENIA',
    SOLOMON_ISLANDS = 'SOLOMON_ISLANDS',
    SOMALIA = 'SOMALIA',
    SOUTH_AFRICA = 'SOUTH_AFRICA',
    SPAIN = 'SPAIN',
    SRI_LANKA = 'SRI_LANKA',
    ST_HELENA = 'ST_HELENA',
    ST_PIERRE_AND_MIQUELON = 'ST_PIERRE_AND_MIQUELON',
    SUDAN = 'SUDAN',
    SURINAME = 'SURINAME',
    SVALBARD_AND_JAN_MAYEN_ISLANDS = 'SVALBARD_AND_JAN_MAYEN_ISLANDS',
    SWAZILAND = 'SWAZILAND',
    SWEDEN = 'SWEDEN',
    SWITZERLAND = 'SWITZERLAND',
    SYRIAN_ARAB_REPUBLIC = 'SYRIAN_ARAB_REPUBLIC',
    TAIWAN_PROVINCE_OF_CHINA = 'TAIWAN_PROVINCE_OF_CHINA',
    TAJIKISTAN = 'TAJIKISTAN',
    TANZANIA_UNITED_REPUBLIC_OF = 'TANZANIA_UNITED_REPUBLIC_OF',
    THAILAND = 'THAILAND',
    TOGO = 'TOGO',
    TOKELAU = 'TOKELAU',
    TONGA = 'TONGA',
    TRINIDAD_AND_TOBAGO = 'TRINIDAD_AND_TOBAGO',
    TUNISIA = 'TUNISIA',
    TURKEY = 'TURKEY',
    TURKMENISTAN = 'TURKMENISTAN',
    TURKS_AND_CAICOS_ISLANDS = 'TURKS_AND_CAICOS_ISLANDS',
    TUVALU = 'TUVALU',
    UGANDA = 'UGANDA',
    UKRAINE = 'UKRAINE',
    UNITED_ARAB_EMIRATES = 'UNITED_ARAB_EMIRATES',
    UNITED_KINGDOM = 'UNITED_KINGDOM',
    UNITED_STATES = 'UNITED_STATES',
    UNITED_STATES_MINOR_OUTLYING_ISLANDS = 'UNITED_STATES_MINOR_OUTLYING_ISLANDS',
    URUGUAY = 'URUGUAY',
    UZBEKISTAN = 'UZBEKISTAN',
    VANUATU = 'VANUATU',
    VATICAN_CITY_STATE = 'VATICAN_CITY_STATE',
    VENEZUELA = 'VENEZUELA',
    VIETNAM = 'VIETNAM',
    VIRGIN_ISLANDS_BRITISH = 'VIRGIN_ISLANDS_BRITISH',
    VIRGIN_ISLANDS_US = 'VIRGIN_ISLANDS_US',
    WALLIS_AND_FUTUNA_ISLANDS = 'WALLIS_AND_FUTUNA_ISLANDS',
    WESTERN_SAHARA = 'WESTERN_SAHARA',
    YEMEN = 'YEMEN',
    YUGOSLAVIA = 'YUGOSLAVIA',
    ZAIRE = 'ZAIRE',
    ZAMBIA = 'ZAMBIA',
    ZIMBABWE = 'ZIMBABWE'
}

export const enum PaiementMethod {
    VERIFIED = 'VERIFIED',
    NOTVERIFIED = 'NOTVERIFIED'
}

export const enum JobType {
    HOURLY = 'HOURLY',
    FIXED = 'FIXED',
    CONTRACT = 'CONTRACT',
    OTHER = 'OTHER'
}

export const enum ExperienceLevel {
    EntryLevel = 'EntryLevel',
    Intermediate = 'Intermediate',
    Expert = 'Expert'
}

export const enum CategoryProject {
    WEB = 'WEB',
    MOBILE = 'MOBILE'
}

export const enum Source {
    UPWORK = 'UPWORK'
}

export const enum Statut {
    OPEN = 'OPEN',
    AWARDE = 'AWARDE',
    CLOSED = 'CLOSED',
    EXPIRED = 'EXPIRED'
}

export interface IInternalFilter {
    id?: number;
    internalFilterTitle?: string;
    internalFilterDescription?: string;
    description?: string;
    datePosted?: Moment;
    country?: Country;
    ratingClient?: number;
    paiementMethod?: PaiementMethod;
    jobType?: JobType;
    experienceLevel?: ExperienceLevel;
    clientHiresNumber?: number;
    clientHistoryInfoIsPrevious?: boolean;
    numberOfWantedHiring?: number;
    numberOfProposal?: number;
    categoryProject?: CategoryProject;
    sourceSite?: Source;
    linkForDetails?: string;
    statutOfOffer?: Statut;
    minBudget?: number;
    maxBudget?: number;
    minHoursPerWeek?: number;
    maxHoursPerWeek?: number;
    minProjectLenghtWithMonthUnit?: number;
    maxProjectLenghtWithMonthUnit?: number;
}

export class InternalFilter implements IInternalFilter {
    constructor(
        public id?: number,
        public internalFilterTitle?: string,
        public internalFilterDescription?: string,
        public description?: string,
        public datePosted?: Moment,
        public country?: Country,
        public ratingClient?: number,
        public paiementMethod?: PaiementMethod,
        public jobType?: JobType,
        public experienceLevel?: ExperienceLevel,
        public clientHiresNumber?: number,
        public clientHistoryInfoIsPrevious?: boolean,
        public numberOfWantedHiring?: number,
        public numberOfProposal?: number,
        public categoryProject?: CategoryProject,
        public sourceSite?: Source,
        public linkForDetails?: string,
        public statutOfOffer?: Statut,
        public minBudget?: number,
        public maxBudget?: number,
        public minHoursPerWeek?: number,
        public maxHoursPerWeek?: number,
        public minProjectLenghtWithMonthUnit?: number,
        public maxProjectLenghtWithMonthUnit?: number
    ) {
        this.clientHistoryInfoIsPrevious = false;
    }
}