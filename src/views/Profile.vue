<template>
  <div class="container">
    
    <!-- <header class="jumbotron" style="height:1%; text-align:center;"> -->
      <!-- <h3 style="text-align: center">
        <strong>{{ currentUser.fullname }}</strong> Profile
      </h3> -->
      <CCard>
      <div style="text-align:center;">
        <h2>Profile</h2>
        <br>
        <br>
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
        style="border-radius: 50%;"
      /></div>
    <!-- </header> -->
    <!-- <p>
      <strong>Token:</strong>
      {{currentUser.accessToken.substring(0, 20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
    </p>
    <p>
      <strong>Id:</strong>
      {{currentUser.global_user_id}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.primary_email}}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul> -->
    
    <form
      style="width: 800px; margin: 0 auto; border-radius: 25px"
      data-toggle="validator"
    >
      <input type="hidden" :value="currentUser.global_user_id" />
      <div class="m-3">
        <div class="form-group row">
          <label class="col-form-label col-4"><b>Prefix:</b></label>
          <div class="col-8">
            <!-- <select  class="form-control" readonly :value="currentUser.prefix_id">
              <option th:each="prefix : ${prefixList}" th:value="${prefix.prefix_id}"  th:text="${user.userPrefixMapping.prefix_name}"/>
            </select> -->
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.prefix_id"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-form-label col-4"><b>First Name:</b></label>
          <div class="col-8">
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.first_name"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-form-label col-4"><b>Last Name:</b></label>
          <div class="col-8">
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.last_name"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-form-label col-4"><b>Gender:</b></label>
          <div class="col-8">
            <!-- <select  class="form-control" readonly>
								<option th:each="gender : ${genderList}" th:value="${gender.gender_id}"  th:text="${user.userGenderMapping.gender_name}"/>
							</select> -->
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.gender_id"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-form-label col-4"><b>Email:</b></label>
          <div class="col-8">
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.primary_email"
            />
          </div>
        </div>
      </div>
      <div>
        <button
          type="button"
          class="edit btn btn-primary"
          @click="editProfile(currentUser), (profileModal=true)"
        >
          Edit
        </button>
      </div>
    </form>
    </CCard>
  
    <!-- the modal for update -->

    <CModal
      title="Update Profile"
      :show.sync="profileModal"
      :centered="true"
      size="lg"
      ><CForm @submit.prevent="saveUpdatedProfile(myCurrentUser)" v-if="!update">
        <CInput type="hidden" :value="myCurrentUser.global_user_id" />
        <CInput
          label="First Name"
          name="first_name"
          :placeholder="myCurrentUser.first_name"
          id="first_name"
          v-model="myCurrentUser.first_name"
          horizontal
        />
        <CInput
          label="Last Name"
          name="last_name"
          :placeholder="myCurrentUser.last_name"
          id="last_name"
          v-model="myCurrentUser.last_name"
          horizontal
        />
        <CInput
          label="Primary Email"
          name="primary_email"
          :placeholder="myCurrentUser.primary_email"
          id="primary_email"
          v-model="myCurrentUser.primary_email"
          horizontal
        />
        <CButton
          data-dismiss="modal"
          type="submit"
          size="sm"
          color="success"
          style="float: right"
          ><CIcon name="cil-check-circle" /> Submit</CButton
        >
      </CForm>
      <div v-else>
        <h4>You Updated successfully!</h4>
      </div>
      <template #footer>
        <CButton @click="darkModal = false" color="danger" style="display: none"
          >Discard</CButton
        >
        <CButton
          @click="darkModal = false"
          color="success"
          style="display: none"
          >Accept</CButton
        >
      </template>
    </CModal>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Profile",
  data() {
    return {
      global_user_id: "",
      first_name: "",
      last_name: "",
      primary_email: "",
      myCurrentUser:[],
      update:false,
      profileModal:false,
      genders:[],
      prefixes:[]
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }
    axios
      .get("http://localhost:8080/list_gender")
      .then((response) => {
        const results = response.data.map((gender) => {
          return {
            value: gender.gender_id,
            label: gender.gender_name,
          };
        });
        this.genders = results;
      })
      .catch((error) => console.log(error));
    axios
      .get("http://localhost:8080/list_prefix")
      .then((response) => {
        const results2 = response.data.map((prefix) => {
          return {
            value: prefix.prefix_id,
            label: prefix.prefix_name,
          };
        });
        this.prefixes = results2;
        console.log(this.prefixes)
      })
      .catch((error) => console.log(error));
  },
  methods: {
    editProfile(currentUser) {
      this.myCurrentUser = {
        global_user_id: this.global_user_id,
        first_name: this.first_name,
        last_name: this.last_name,
        primary_email: this.primary_email
      };
      this.myCurrentUser = currentUser;
    },
    saveUpdatedProfile(myCurrentUser) {
      this.currentUser = myCurrentUser
      console.log(this.currentUser)
      axios
        .put(`http://localhost:8080/profile/save/${myCurrentUser.global_user_id}`, myCurrentUser)
        .then((response) => {})
        .catch((e) => {
          console.log(e);
        });
      this.update = true;
    },
  },
};
</script>
