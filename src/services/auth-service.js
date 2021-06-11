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
    console.log(user)
    return axios.post("http://localhost:8080/process_register", {
        first_name: user.first_name,    
        last_name: user.last_name,
        full_name: user.first_name +" " + user.last_name,
        primary_email: user.primary_email,
        password: user.password,
        prefix_id: user.prefix_id,
        gender_id: user.gender_id
    });
  }
}

export default new AuthService();