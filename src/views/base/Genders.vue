<template>
  <div>
    <CCardBody>
      <CDataTable :items="genders" :fields="fields" :small="small" :items-per-page="small ? 10 : 7" pagination>
        <CIcon name="cil-grid" /> Simple Table
        <template #update="{ item }">
          <td class="py-2">
            <CButton
              color="primary"
              variant="outline"
              square
              size="sm"
              @click="viewGender(item), (warningModal = true)"
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
              @click="confirmDelete(item), (deleteModal=true)"
            >
              Delete
            </CButton>
          </td>
        </template>
      </CDataTable></CCardBody
    >
     
    <!-- the modal for update -->

    <CModal
      title="Update Gender"
      :show.sync="warningModal"
      :no-close-on-backdrop="true"
      :centered="true"
      size="lg"
      ><CForm @submit.prevent="saveUpdatedGender(myGender)" v-if="!update">
        <CInput type="hidden" :value="myGender.gender_id" />
        <CInput
          label="Prefix Name"
          name="prefix_name"
          :placeholder="myGender.gender_name"
          id="idea_title"
          v-model="myGender.gender_name"
          horizontal
        />
        <CTextarea
          label="Gender Description"
          horizontal
          rows="9"
          name="gender_description"
          id="gender_description"
          :placeholder="myGender.gender_description"
          v-model="myGender.gender_description"
        />
        <CButton data-dismiss="modal" type="submit" size="sm" color="success" style="float: right;"
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
    <CModal
      title="Delete Gender"
      :show.sync="deleteModal"
      
    >
    <CForm @submit.prevent="deleteGender(myGenderDelete)" v-if="!submitted">
     <p> Are you sure you want to delete this item?</p>
      <CButton type="submit" size="sm" color="danger" style="float: right;"
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
import CTableWrapper from "./Table.vue";
import axios from "axios";

export default {
  name: "Genders",
  components: { CTableWrapper },
  props:{
        small: Boolean,
  },
  data() {
    return {
      gender_id: "",
      gender_name: "",
      gender_description: "",
      update: false,
      submitted: false,
      genders: [],
      myGender: "",
      myGenderDelete:"",
      fields: [
        { key: "gender_id" },
        { key: "gender_name" },
        { key: "gender_description" },
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
  methods: {
    viewGender(item) {
      this.myGender = {
        gender_id: this.gender_id,
        gender_name: this.gender_name,
        gender_description: this.gender_description,
      };
      this.myGender = item;
    },
    saveUpdatedGender(myGender) {
      axios
        .put(`http://localhost:8080/gender/save/${myGender.gender_id}`, myGender)
        .then((response) => {
        })
        .catch((e) => {
          console.log(e);
        });
      this.update = true;
    },
    confirmDelete(item){
        this.myGenderDelete = {
        gender_id: this.gender_id,
        gender_name: this.gender_name,
        gender_description: this.gender_description,
      };
      this.myGenderDelete = item;
    },
    deleteGender(myGenderDelete) {
        axios
        .delete(`http://localhost:8080/gender/delete/${myGenderDelete.gender_id}`)
        .then((response) => {
        })
        .catch((e) => {
          console.log(e);
        });
      this.submitted = true;
    },
  },

    // this is the function for returning all the ideas
  mounted: function () {
    axios
      .get("http://localhost:8080/list_gender/")
      .then((response) => {
        this.genders = response.data;
      })
      .catch((error) => console.log(error));
  },
};
</script>
