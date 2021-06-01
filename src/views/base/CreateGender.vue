<template>
  <div>
    <div v-if="!submitted">
      <CCard>
        <CCardHeader> <strong>Basic Form</strong> Elements </CCardHeader>
        <CCardBody>
          <CForm @submit.prevent="saveGender">
            <CInput
              label="Gender Name"
              name="gender_name"
              id="gender_name"
              v-model="gender_name"
              horizontal
            />
            <CTextarea
              label="Gender Description"
              horizontal
              rows="9"
              name="gender_description"
              id="gender_description"
              v-model="gender_description"
            />
            <CButton type="submit" size="sm" color="success"
              ><CIcon name="cil-check-circle" /> Submit</CButton
            >
            <CButton type="reset" size="sm" color="danger"
              ><CIcon name="cil-ban" /> Reset</CButton
            >
          </CForm>
        </CCardBody>
      </CCard>
    </div>
    <div v-else>
      <h3>You submitted successfully!</h3>
      <button class="btn btn-success" v-on:click="newGender">Add</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CreateGender",
  data() {
    return {
      gender_id: "",
      gender_name: "",
      gender_description: "",
      submitted: false,
    };
  },
  methods: {
    saveGender() {
      const gender = {
        gender_id: this.gender_id,
        gender_name: this.gender_name,
        gender_description: this.gender_description,
      };
      console.log(gender);
      axios
        .post("http://localhost:8080/process_gender", gender)
        .then((response) => {
        })
        .catch((e) => {
          console.log(e);
        });

      this.submitted = true;
    },
    newGender() {
      this.submitted = false;
      this.gender = {};
    },
  },
};
</script>