<template>
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

                  <CRow>
                    <CSelect
                      label="Prefix"
                      horizontal
                      required
                      :options="prefixes"
                      :value.sync="prefixSelectedValue"
                      placeholder="Please select"
                    />
                    <CSelect
                      label="Gender"
                      horizontal
                      required
                      :options="genders"
                      :value.sync="genderSelectedValue"
                      placeholder="Please select"
                    />
                  </CRow>
                  <CRow>
                    <CInput
                      placeholder="First Name"
                      v-model="first_name"
                      required
                      :is-valid="validator"
                    >
                      <template #prepend-content
                        ><CIcon name="cil-user"
                      /></template>
                    </CInput>

                    <CInput
                      placeholder="Last Name"
                      v-model="last_name"
                      required
                    >
                      <template #prepend-content
                        ><CIcon name="cil-user"
                      /></template>
                    </CInput>
                  </CRow>
                  <CRow>
                    <CInput
                      placeholder="Email"
                      type="email"
                      v-model="primary_email"
                      prepend="@"
                      required
                    />
                    <CInput
                      placeholder="Password"
                      type="password"
                      v-model="password"
                      required
                    >
                      <template #prepend-content
                        ><CIcon name="cil-lock-locked"
                      /></template>
                    </CInput>
                    <CButton type="submit" color="success" block
                      >Create Account</CButton
                    >
                  </CRow>
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
    // validator(val){
    //   return val ? val.length == 0 : false
    // },

    handleRegister() {
      const user = {
        first_name: this.first_name,
        last_name: this.last_name,
        primary_email: this.primary_email,
        password: this.password,
        prefix_id: this.prefixSelectedValue,
        gender_id: this.genderSelectedValue,
      };
      console.log(user);
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