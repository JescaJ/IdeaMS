<template>
  <!-- <div class="col-md-12">
    <br />
    <h3 style="text-align: center">User Registration</h3>
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <CForm name="form" @submit.prevent="handleRegister">
        <div v-if="!successful">
          <CSelect
            label="Prefix"
            name="prefix_id"
            id="prefix_id"
            horizontal
            :options="prefixes"
            :value.sync="prefixSelectedValue"
            placeholder="Please select"
          />
          <CSelect
            label="Gender"
            name="gender_id"
            id="gender_id"
            horizontal
            :options="genders"
            :value.sync="genderSelectedValue"
            placeholder="Please select"
          />
          <div class="form-group">
            <label for="first_name">First Name</label>
            <input
              v-model="first_name"
              v-validate="'required|min:3|max:20'"
              type="text"
              class="form-control"
              name="first_name"
            />
            <div
              v-if="submitted && errors.has('first_name')"
              class="alert-danger"
            >
              {{ errors.first("First Name") }}
            </div>
          </div>
          <div class="form-group">
            <label for="last_name">Last Name</label>
            <input
              v-model="last_name"
              v-validate="'required|min:3|max:20'"
              type="text"
              class="form-control"
              name="last_name"
            />
            <div
              v-if="submitted && errors.has('last_name')"
              class="alert-danger"
            >
              {{ errors.first("last_name") }}
            </div>
          </div>

          <div class="form-group">
            <label for="primary_email">Email</label>
            <input
              v-model="primary_email"
              v-validate="'required|email|max:50'"
              type="email"
              class="form-control"
              name="primary_email"
            />
            <div
              v-if="submitted && errors.has('primary_email')"
              class="alert-danger"
            >
              {{ errors.first("primary_email") }}
            </div>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input
              v-model="password"
              v-validate="'required|min:6|max:40'"
              type="password"
              class="form-control"
              name="password"
            />
            <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >
              {{ errors.first("password") }}
            </div>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block">Sign Up</button>
          </div>
        </div>
      </CForm>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
      </div>
    </div> -->

  <!-- <template> -->
  <div class="d-flex align-items-center min-vh-100">
    <CContainer fluid>
      <CRow class="justify-content-center">
        <CCol md="6">
          <CCard class="mx-4 mb-0">
            <CCardBody class="p-4">
              <img
                id="profile-img"
                src="//ssl.gstatic.com/accounts/ui/avatar_1x.png"
                class="profile-img-card"
              />
              <CForm @submit.prevent="handleRegister">
                <div v-if="!successful">
                  <h1 style="text-align: center">Register</h1>
                  <p class="text-muted" style="text-align: center">
                    Create your account today!
                  </p>

                  <div class="row">
                    <CSelect
                      label="Prefix"
                      name="prefix_id"
                      id="prefix_id"
                      horizontal
                      :options="prefixes"
                      :value.sync="prefixSelectedValue"
                      placeholder="Please select"
                      class="col"
                    />
                    <CSelect
                      label="Gender"
                      name="gender_id"
                      id="gender_id"
                      horizontal
                      :options="genders"
                      :value.sync="genderSelectedValue"
                      placeholder="Please select"
                    />
                  </div>
                  <div class="row">
                    <CInput
                      placeholder="First Name"
                      autocomplete="first_name"
                      v-model="first_name"
                      class="col"
                      name="first_name"
                    >
                      <template #prepend-content
                        ><CIcon name="cil-user"
                      /></template>
                    </CInput>

                    <CInput
                      placeholder="Last Name"
                      autocomplete="last_name"
                      v-model="last_name"
                      class="col"
                      name="last_name"
                    >
                      <template #prepend-content
                        ><CIcon name="cil-user"
                      /></template>
                    </CInput>
                  </div>
                  <!-- <div class="row">
                    <CInput
                      placeholder="Last Name"
                      autocomplete="last_name"
                      v-model="last_name"
                      class="col"
                      v-validate="'required|min:3|max:20'"
                    >
                      <template #prepend-content
                        ><CIcon name="cil-user"
                      /></template>
                    </CInput>
                  </div> -->

                  <div class="row">
                    <CInput
                      placeholder="Email"
                      autocomplete="primary_email"
                      v-model="primary_email"
                      class="col"
                      prepend="@"
                      name="primary_email"
                    />
                    <CInput
                      placeholder="Password"
                      type="password"
                      v-model="password"
                      class="col"
                      name="password"
                    >
                      <template #prepend-content
                        ><CIcon name="cil-lock-locked"
                      /></template>
                      <!-- <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >
              {{ errors.first("password") }}
            </div> -->
                    </CInput>
                    <CButton type="submit" color="success" block
                      >Create Account</CButton
                    >
                  </div>
                </div>
              </CForm>
              <div>
                <div
                  v-if="message"
                  class="alert"
                  :class="successful ? 'alert-success' : 'alert-danger'"
                >
                  {{ message }}
                </div>
                <div v-if="message">
                  <a href="/login"> <CButton color="primary">Login</CButton></a>
                </div>
              </div>
            </CCardBody>
            <!-- <CCardFooter class="p-4">
              <CRow>
                <CCol col="6">
                  <CButton block color="facebook">
                    Facebook
                  </CButton>
                </CCol>
                <CCol col="6">
                  <CButton block color="twitter">
                    Twitter
                  </CButton>
                </CCol>
              </CRow>
            </CCardFooter> -->
          </CCard>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
import User from "../models/user";
import axios from "axios";

export default {
  name: "Register",
  data() {
    return {
      // user: new User("", "", "", "","",""),
      submitted: false,
      successful: false,
      message: "",
      genders: [],
      prefixes: [],
      first_name: "",
      last_name: "",
      primary_email: "",
      password: "",
      genderSelectedValue: "",
      prefixSelectedValue: "",
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/profile");
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
      })
      .catch((error) => console.log(error));
  },

  methods: {
    handleRegister() {
      const user = {
        first_name: this.first_name,
        last_name: this.last_name,
        primary_email: this.primary_email,
        password: this.password,
        prefix_id: this.prefixSelectedValue,
        gender_id: this.genderSelectedValue,
      };
      this.message = "";
      this.submitted = true;
      this.$validator.validate().then((isValid) => {
        if (isValid) {
          this.$store.dispatch("auth/register", user).then(
            (data) => {
              this.message = data.message;
              this.successful = true;
            },
            (error) => {
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
      });
    },
  },
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>