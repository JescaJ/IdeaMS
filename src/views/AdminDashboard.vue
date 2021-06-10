<template>
  <div>
    <TheSidebar />
          <CCard>
            <CCardBody>
              <CRow>
                <CCol sm="12" lg="6">
                  <CRow>
                    <CCol sm="6">
                      <CCallout color="info">
                        <small class="text-muted">New Ideas</small><br />
                        <strong class="h4">3</strong>
                      </CCallout>
                    </CCol>
                    <CCol sm="6">
                      <CCallout color="danger">
                        <small class="text-muted">Total Ideas</small
                        ><br />
                        <strong class="h4">{{ideasNumber}}</strong>
                      </CCallout>
                    </CCol>
                  </CRow>
                </CCol>
                <CCol sm="12" lg="6">
                  <CRow>
                    <CCol sm="6">
                      <CCallout color="warning">
                        <small class="text-muted">New Users</small><br />
                        <strong class="h4">3</strong>
                      </CCallout>
                    </CCol>
                    <CCol sm="6">
                      <CCallout color="success">
                        <small class="text-muted">Total Users</small><br />
                        <strong class="h4">{{usersNumber}}</strong>
                      </CCallout>
                    </CCol>
                  </CRow>
                </CCol>
              </CRow>
              <br />
              <br />
              <CDataTable
                :items="ideas"
                :fields="fields"
                :small="small"
                :items-per-page="small ? 10 : 7"
                pagination
              >
                <CIcon name="cil-grid" /> Simple Table
                <template #update="{ item }">
                  <td class="py-2">
                    <CButton
                      color="primary"
                      variant="outline"
                      square
                      size="sm"
                      @click="viewIdea(item), (warningModal = true)"
                    >
                      Update
                    </CButton>
                  </td>
                </template>
                <template #delete="{ item }">
                  <td class="py-2">
                    <CButton
                      color="primary"
                      variant="outline"
                      square
                      size="sm"
                      @click="confirmDelete(item), (deleteModal = true)"
                    >
                      Delete
                    </CButton>
                  </td>
                </template>
              </CDataTable>
            </CCardBody>
          </CCard>

    <!-- the modal for update -->

    <CModal
      title="Update Idea"
      :show.sync="warningModal"
      :no-close-on-backdrop="true"
      :centered="true"
      size="lg"
      ><CForm @submit.prevent="saveUpdatedIdea(myIdea)" v-if="!update">
        <CInput type="hidden" :value="myIdea.idea_id" />
        <CInput
          label="Idea Title"
          name="idea_title"
          :placeholder="myIdea.idea_title"
          id="idea_title"
          v-model="myIdea.idea_title"
          horizontal
        />
        <CTextarea
          label="Description"
          horizontal
          rows="9"
          name="idea_description"
          id="idea_description"
          :placeholder="myIdea.idea_description"
          v-model="myIdea.idea_description"
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
        <h3>You Updated successfully!</h3>
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

    <!-- modal for delete -->
    <CModal title="Delete Idea" :show.sync="deleteModal">
      <CForm @submit.prevent="deleteIdea(myIdeaDelete)" v-if="!submitted">
        <p>Are you sure you want to delete this item?</p>
        <CButton type="submit" size="sm" color="danger" style="float: right"
          ><CIcon name="cil-check-circle" /> Delete</CButton
        >
      </CForm>
      <div v-else>
        <h3>Deleted successfully!</h3>
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
import UserService from "../services/user-service";
import CTableWrapper from "../views/base/Table";
import axios from "axios";

import TheSidebar from "../containers/TheSidebar";
import TheFooter from "../containers/TheFooter";

export default {
  name: "User",
  components: {
    TheSidebar,
    TheFooter,
    CTableWrapper,
  },
  props: {
    small: Boolean,
  },
  data() {
    return {
      idea_id: "",
      idea_title: "",
      idea_description: "",
      category_id: "",
      global_user_id: "",
      update: false,
      submitted: false,
      ideas: [],
      ideasNumber: '',
      usersNumber:'',
      myIdea: "",
      myIdeaDelete: "",
      fields: [
        { key: "idea_id" },
        { key: "idea_title" },
        { key: "idea_description" },
        { key: "category_id", label: "Category" },
        { key: "global_user_id", label: "Created By" },
        {
          key: "update",
          label: "",
          _style: "width:1%",
          sorter: false,
          filter: false,
        },
        {
          key: "delete",
          label: "",
          _style: "width:1%",
          sorter: false,
          filter: false,
        },
      ],

      warningModal: false,
      deleteModal: false,
    };
  },
  mounted() {
    UserService.getAdminBoard().then(
      (response) => {
        this.content = response.data;
      },
      (error) => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
    axios
      .get("http://localhost:8080/list_ideas/")
      .then((response) => {
        var currentDate = new Date();
            console.log(currentDate);
        var formatted_date = new Date().toJSON().slice(0,10);
            console.log(formatted_date);
        this.ideas = response.data;
        this.ideasNumber = this.ideas.length
        // this.ideas.forEach((idea,index) => {
        //   console.log(index)
        //   console.log(idea.create_date)
        //   // var create_date_formatted = idea.create_date.toJSON().slice(0,10);
        // });
      })
      .catch((error) => console.log(error));

    axios
      .get("http://localhost:8080/list_users/")
      .then((response) => {
        this.usersNumber = response.data.length
      })
      .catch((error) => console.log(error));
  },
  methods: {
    viewIdea(item) {
      this.myIdea = {
        idea_id: this.idea_id,
        idea_title: this.idea_title,
        idea_description: this.idea_description,
        category_id: this.category_id,
        global_user_id: this.global_user_id,
      };
      this.myIdea = item;
    },
    saveUpdatedIdea(myIdea) {
      axios
        .put(`http://localhost:8080/ideas/save/${myIdea.idea_id}`, myIdea)
        .then((response) => {})
        .catch((e) => {
          console.log(e);
        });
      this.update = true;
    },
    confirmDelete(item) {
      this.myIdeaDelete = {
        idea_id: this.idea_id,
        idea_title: this.idea_title,
        idea_description: this.idea_description,
        category_id: this.category_id,
        global_user_id: this.global_user_id,
      };
      this.myIdeaDelete = item;
    },
    deleteIdea(myIdeaDelete) {
      axios
        .delete(`http://localhost:8080/idea/delete/${myIdeaDelete.idea_id}`)
        .then((response) => {})
        .catch((e) => {
          console.log(e);
        });
      this.submitted = true;
    },
  },
};
</script>