<template>
  <div class="d-flex align-items-center min-vh-100">
    <CContainer fluid>
      <CRow class="justify-content-center">
        <CCol md="6">
          <CCard class="mx-4 mb-0">
            <CCardBody class="p-4">
              <CForm>
                <h1>Register</h1>
                <p class="text-muted">Create your account</p>
                <div class="row">
                <CSelect
                  label="Prefix"
                  name="prefix_id"
                  id="prefix_id"
                  horizontal
                  :options="prefixes"
                  :value.sync="prefixValue"
                  placeholder="Please select"
                  class="col"
                />
                <CInput
                  placeholder="First Name"
                  autocomplete="firstname"
                  class="col"
                >
                  <template #prepend-content><CIcon name="cil-user"/></template>
                </CInput>
                </div>
                <div class="row">
                <CInput
                  placeholder="Middle Name"
                  autocomplete="middlename"
                  class="col"
                >
                  <template #prepend-content><CIcon name="cil-user"/></template>
                </CInput>
                <CInput
                  placeholder="Last Name"
                  autocomplete="lastname"
                  class="col"
                >
                  <template #prepend-content><CIcon name="cil-user"/></template>
                </CInput>
                </div>
                
                <CSelect
                  label="Gender"
                  name="gender_id"
                  id="gender_id"
                  horizontal
                  :options="genders"
                  :value.sync="genderValue"
                  placeholder="Please select"
                />
                <div class="row">
                <CInput
                  placeholder="Email"
                  autocomplete="email"
                  class="col"
                  prepend="@"
                />
                <CInput
                  placeholder="Password"
                  type="password"
                  autocomplete="new-password"
                  class="col"
                >
                  <template #prepend-content><CIcon name="cil-lock-locked"/></template>
                </CInput>
                </div>
                <!-- <CInput
                  placeholder="Repeat password"
                  type="password"
                  autocomplete="new-password"
                  class="mb-4"
                >
                  <template #prepend-content><CIcon name="cil-lock-locked"/></template>
                </CInput> -->
                <CButton color="success" block>Create Account</CButton>
              </CForm>
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
import axios from 'axios';

export default {
  name: 'Register',


  data(){
    return{
      genders:[],
      prefixes:[],
      prefixValue:"",
      genderValue:"",
    };
    
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/list_prefix")
      .then((response) => {
        const prefixResults = response.data.map((prefix) => {
          return {
            value: prefix.prefix_id,
            label: prefix.prefix_name,
          };
        });
        this.prefixes = prefixResults;
        // console.log(prefixResults);
      })
      .catch((error) => console.log(error));

      //for the gender
      axios
      .get("http://localhost:8080/list_gender")
      .then((response) => {
        const genderResults = response.data.map((gender) => {
          return {
            value: gender.gender_id,
            label: gender.gender_name,
          };
        });
        this.genders = genderResults;
        // console.log(genderResults);
      })
      .catch((error) => console.log(error));
  },
  
}
</script>
