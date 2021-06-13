export default class User {
    constructor(fullname, primary_email, password, roles) {
      this.fullname = fullname;
      this.primary_email = primary_email;
      this.password = password;
      this.roles = roles;
    }
  }