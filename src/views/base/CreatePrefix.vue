<template>
  <div>
    <div v-if="!submitted">
      <CCard>
        <CCardHeader> <strong>Basic Form</strong> Elements </CCardHeader>
        <CCardBody>
          <CForm @submit.prevent="savePrefix">
            <CInput
              label="Prefix Name"
              name="prefix_name"
              id="prefix_name"
              v-model="prefix_name"
              horizontal
            />
            <CTextarea
              label="Prefix Description"
              horizontal
              rows="9"
              name="prefix_description"
              id="prefix_description"
              v-model="prefix_description"
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
      <button class="btn btn-success" v-on:click="newPrefix">Add</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CreatePrefix",
  data() {
    return {
      prefix_id: "",
      prefix_name: "",
      prefix_description: "",
      submitted: false,
    };
  },
  methods: {
    savePrefix() {
      const prefix = {
        prefix_id: this.prefix_id,
        prefix_name: this.prefix_name,
        prefix_description: this.prefix_description,
      };
      console.log(prefix);
      axios
        .post("http://localhost:8080/process_prefix", prefix)
        .then((response) => {
        })
        .catch((e) => {
          console.log(e);
        });

      this.submitted = true;
    },
    newPrefix() {
      this.submitted = false;
      this.prefix = {};
    },
  },
};
</script>