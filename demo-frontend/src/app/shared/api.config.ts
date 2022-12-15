import { environment } from '../../environments/environment';

export const API_CONFIG = {
  authUrl: environment.authBaseUrl,
  login: environment.authBaseUrl + '/oauth/token',
  logout: environment.authBaseUrl + '/logout',
  getAllProfiles: environment.adminBaseUrl + '/getAllProfiles',
  getAllSections: environment.adminBaseUrl + '/getAllSections',

  // Contacts API
  getContacts: environment.contactsBaseUrl + '/getContacts',
  getContact: environment.contactsBaseUrl + '/getContact',
  createContact: environment.contactsBaseUrl + '/createContact',
  editContact: environment.contactsBaseUrl + '/editContact',
  deleteContact: environment.contactsBaseUrl + '/deleteContact',

  // Entrepreneur API
  urlBaseEntrepreneur: environment.adminBaseUrl + '/entrepreneur/getEntrepreneurs',

  //invester API
  getInvestorsPage: environment.investorBaseUrl + '/getInvestors/',
  getInvestor: environment.investorBaseUrl +'/getInvestor/',
  createInvestor:environment.investorBaseUrl +'/createInvestor',
  editInvestor:environment.investorBaseUrl +'/editInvestor/',
  deleteInvestor:environment.investorBaseUrl +'/deleteInvestor/',

  //RangeInvester API
  urlBaseRange: environment.adminBaseUrl + '/rangeInvester/getRangeInvestors',
  
  //ProfessionalProfile API
  urlProfessionalProfile: environment.adminBaseUrl + '/professionalProfile/',
  getPPage: environment.adminBaseUrl + '/professionalProfile/page-query/',

  //Startup API
  getStartupsPage: environment.startupBaseUrl + '/getStartups/',
  getStartup: environment.startupBaseUrl +'/getStartup/',
  createStartup:environment.startupBaseUrl +'/createStartup',
  editStartup:environment.startupBaseUrl +'/editStartup/',
  deleteStartup:environment.startupBaseUrl +'/deleteStartup/',

  //BusinessSector API
  urlBaseBusinessSector: environment.adminBaseUrl + '/businessSector/getBusinessSectors',

  //StartupState API
  urlBaseStartUpState: environment.adminBaseUrl + '/startupState/getStartupStates',

  //User API
  getUsersPage: environment.userBaseUrl + '/getUsers',
  getUser: environment.userBaseUrl +'/getUser',
  createUser:environment.userBaseUrl +'/createUser',
  editUser:environment.userBaseUrl +'/editUser'
};
