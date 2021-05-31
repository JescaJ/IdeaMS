<template>
  <div>
    <!-- <div> -->
    <!-- <h1>You've gat this. Stay focussed</h1> -->
    <!-- <b-table striped hover :items="items"></b-table> -->
    <!--<CTableWrapper-->
    <CCardBody>
      <CDataTable :items="ideas" :fields="fields" :small="small" :items-per-page="small ? 10 : 7" pagination>
        <CIcon name="cil-grid" /> Simple Table
        <!-- <div class="card-header-actions">
                <a 
                    href="https://coreui.io/vue/docs/components/nav" 
                    class="card-header-action" 
                    rel="noreferrer noopener" 
                    target="_blank"
                >
                    <small class="text-muted">docs</small>
                </a>
            </div>  -->
        <template #update="{ item }">
          <td class="py-2">
            <!-- <a :href="item.idea_id"> -->
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
      title="Delete Idea"
      :show.sync="deleteModal"
      
    >
    <CForm @submit.prevent="deleteIdea(myIdeaDelete)" v-if="!submitted">
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
  name: "Ideas",
  components: { CTableWrapper },
  props:{
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
      myIdea: "",
      myIdeaDelete:"",
      fields: [
        { key: "idea_id" },
        { key: "idea_title" },
        { key: "idea_description" },
        { key: "category_id" },
        { key: "global_user_id" },
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
    viewIdea(item) {
      this.myIdea = {
        idea_id: this.idea_id,
        idea_title: this.idea_title,
        idea_description: this.idea_description,
        category_id: this.category_id,
        global_user_id: this.global_user_id,
      };
      this.myIdea = item;
      //   console.log(this.myIdea);
    },
    saveUpdatedIdea(myIdea) {
    //   console.log(myIdea);
      axios
        .put(`http://localhost:8080/ideas/save/${myIdea.idea_id}`, myIdea)
        .then((response) => {
            // console.log(response);
        })
        .catch((e) => {
          console.log(e);
        });
      this.update = true;
    },
    confirmDelete(item){
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
        // console.log(myIdeaDelete)
        axios
        .delete(`http://localhost:8080/idea/delete/${myIdeaDelete.idea_id}`)
        .then((response) => {
            // console.log(response);
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
      .get("http://localhost:8080/list_ideas/")
      .then((response) => {
        //  console.log(response[0]['idea_title'])
        this.ideas = response.data;
      })
      .catch((error) => console.log(error));
  },
};
</script>
