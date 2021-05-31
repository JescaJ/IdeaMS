<template>
  <div>
    <div v-if="!submitted">
      <CCard>
        <CCardHeader> <strong>Basic Form</strong> Elements </CCardHeader>
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
            <!-- <CInput
              label="Category ID"
              name="category_id"
              type="number"
              id="category_id"
              v-model="category_id"
              horizontal
            /> -->
            <CSelect
              label="Category"
              name="category_id"
              id="category_id"
              horizontal
              :options="categories"
              :value.sync="selectedValue"
              placeholder="Please select"
            />
            <CInput
              label="Global User ID"
              name="global_user_id"
              type="number"
              id="global_user_id"
              v-model="global_user_id"
              horizontal
            />
            <!-- <CSelect
              label="Global User ID"
              name="global_user_id"
              id="global_user_id"
              horizontal
              :options="optionsUsers"
              v-model="global_user_id"
              placeholder="Please select"
            /> -->
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
      <button class="btn btn-success" v-on:click="newIdea">Add</button>
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
        optionsUsers: [ 6,7],
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/list_category")
      .then((response) => {
        //  console.log(response[0]['idea_title'])
        const results = response.data.map((category) => {
          return {
            value: category.category_id,
            label: category.category_name,
          };
        });
        this.categories = results;
        console.log(results);
      })
      .catch((error) => console.log(error));
  },
  methods: {
    /* eslint-disable no-console */
    saveIdea() {
      const idea = {
        idea_id: this.idea_id,
        idea_title: this.idea_title,
        idea_description: this.idea_description,
        category_id: this.selectedValue,
        global_user_id: this.global_user_id,
      };
      console.log(idea);
      axios
        .post("http://localhost:8080/process_add_idea", idea)
        .then((response) => {
          //   console.log(response);
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
    /* eslint-enable no-console */
  },
};
</script>