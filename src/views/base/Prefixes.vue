<template>
  <div>
    <CCardBody>
      <CDataTable :items="prefixes" :fields="fields" :small="small" :items-per-page="small ? 10 : 7" pagination>
        <CIcon name="cil-grid" /> Simple Table
        <template #update="{ item }">
          <td class="py-2">
            <CButton
              color="primary"
              variant="outline"
              square
              size="sm"
              @click="viewPrefix(item), (warningModal = true)"
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
      title="Update Prefix"
      :show.sync="warningModal"
      :no-close-on-backdrop="true"
      :centered="true"
      size="lg"
      ><CForm @submit.prevent="saveUpdatedPrefix(myPrefix)" v-if="!update">
        <CInput type="hidden" :value="myPrefix.idea_id" />
        <CInput
          label="Prefix Name"
          name="prefix_name"
          :placeholder="myPrefix.prefix_name"
          id="idea_title"
          v-model="myPrefix.prefix_name"
          horizontal
        />
        <CTextarea
          label="Prefix Description"
          horizontal
          rows="9"
          name="prefix_description"
          id="prefix_description"
          :placeholder="myPrefix.prefix_description"
          v-model="myPrefix.prefix_description"
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
      title="Delete Prefix"
      :show.sync="deleteModal"
      
    >
    <CForm @submit.prevent="deletePrefix(myPrefixDelete)" v-if="!submitted">
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
  name: "Prefixes",
  components: { CTableWrapper },
  props:{
        small: Boolean,
  },
  data() {
    return {
      prefix_id: "",
      prefix_name: "",
      prefix_description: "",
      update: false,
      submitted: false,
      prefixes: [],
      myPrefix: "",
      myPrefixDelete:"",
      fields: [
        { key: "prefix_id" },
        { key: "prefix_name" },
        { key: "prefix_description" },
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
    viewPrefix(item) {
      this.myPrefix = {
        prefix_id: this.prefix_id,
        prefix_name: this.prefix_name,
        prefix_description: this.prefix_description,
      };
      this.myPrefix = item;
    },
    saveUpdatedPrefix(myPrefix) {
      axios
        .put(`http://localhost:8080/prefix/save/${myPrefix.prefix_id}`, myPrefix)
        .then((response) => {
        })
        .catch((e) => {
          console.log(e);
        });
      this.update = true;
    },
    confirmDelete(item){
        this.myPrefixDelete = {
        prefix_id: this.prefix_id,
        prefix_name: this.prefix_name,
        prefix_description: this.prefix_description,
      };
      this.myPrefixDelete = item;
    },
    deletePrefix(myPrefixDelete) {
        axios
        .delete(`http://localhost:8080/prefix/delete/${myPrefixDelete.prefix_id}`)
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
      .get("http://localhost:8080/list_prefix/")
      .then((response) => {
        this.prefixes = response.data;
      })
      .catch((error) => console.log(error));
  },
};
</script>
