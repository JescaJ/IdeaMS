// import axios from 'axios'

//     const state= {
//         token: null,
//         user: null
//     };

//     const getters= {

//     };

//     const mutations= {
//         SET_TOKEN(state, token){
//             state.token = token
//         }
//     };

//     const actions= {
//         async signin({dispatch}, credentials) {
//             try{
//                 const response = await axios.post('http://localhost:8080/authenticate', credentials)
//                 console.log(response.config.data)
//                 return dispatch('attempt', response.data) // my token is in data
//             }
//             catch(e){
//                 console.log(e)
//             }
//         },

//         async attempt({commit}, token){
//             commit('SET_TOKEN', token)
//             try{
//                 // let response = axios
//             }
//             catch(e){

//             }
//             // commit('SET_USER', )
//         }
//     };

//     export default{
//         state,
//         getters,
//         mutations,
//         actions
//     }

