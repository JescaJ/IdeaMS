import axios from 'axios';

const API_URL = 'http://localhost:8080';

class AuthService {
  login(user) {
    return axios
      .post("http://localhost:8080/signin", {
        primary_email: user.primary_email,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post("http://localhost:8080/process_register", {
        full_name: user.full_name,
        primary_email: user.primary_email,
      password: user.password
    });
  }
}

export default new AuthService();