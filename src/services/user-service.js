import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080';

class UserService {
  getPublicContent() {
    // return axios.get(API_URL + 'list_ideas');
    return axios.get("http://localhost:8080/list_ideas/");
  }

  getUserBoard() {
    // return axios.get(API_URL + 'list_ideas', { headers: authHeader() });
    return axios.get("http://localhost:8080/list_ideas/", { headers: authHeader() });
  }

//   getModeratorBoard() {
//     return axios.get(API_URL + 'mod', { headers: authHeader() });
//   }

  getAdminBoard() {
    // return axios.get(API_URL + 'list_users', { headers: authHeader() });
    return axios.get("http://localhost:8080/list_users/", { headers: authHeader() });
  }
}

export default new UserService();