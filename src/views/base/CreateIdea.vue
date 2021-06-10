<template>
  <div>
    <div v-if="!submitted">
      <CCard>
        <!-- <CCardHeader> <strong>Basic Form</strong> Elements </CCardHeader> -->
        <CCardBody>
          <CForm @submit.prevent="saveIdea">
            <CInput
              label="Idea Title"
              name="idea_title"
              id="idea_title"
              v-model="idea_title"
              horizontal
            />
            <CTextarea
              label="Description"
              horizontal
              rows="9"
              name="idea_description"
              id="idea_description"
              v-model="idea_description"
            />
            <CSelect
              label="Category"
              name="category_id"
              id="category_id"
              horizontal
              :options="categories"
              :value.sync="selectedValue"
              placeholder="Please select"
            />
            <CButton type="submit" size="sm" color="success" @click="warningModal = true"
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
      <CModal
      title="Update Idea"
      :show.sync="warningModal"
      :no-close-on-backdrop="true"
      :centered="true"
      size="lg"
      >
      <h3>You submitted successfully!</h3>
      <!-- <button class="btn btn-success" v-on:click="newIdea">Add</button> -->
      </CModal>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CreateIdea",
  data() {
    return {
      categories: [],
      selectedValue: "",
      idea_id: "",
      idea_title: "",
      idea_description: "",
      global_user_id: "",
      submitted: false,
      warningModal: false,
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/list_category")
      .then((response) => {
        const results = response.data.map((category) => {
          return {
            value: category.category_id,
            label: category.category_name,
          };
        });
        this.categories = results;
      })
      .catch((error) => console.log(error));
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  methods: {
    saveIdea() {
      const idea = {
        idea_id: this.idea_id,
        idea_title: this.idea_title,
        idea_description: this.idea_description,
        category_id: this.selectedValue,
        global_user_id: this.currentUser.global_user_id,
      };
      console.log(idea);
      axios
        .post("http://localhost:8080/process_add_idea", idea)
        .then((response) => {
        })
        .catch((e) => {
          console.log(e);
        });

      this.submitted = true;
    },
    newIdea() {
      this.submitted = false;
      this.idea = {};
    },
  },
};
</script>