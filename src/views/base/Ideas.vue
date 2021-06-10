<template>
  <div>
    <CCardBody>
      <CDataTable :items="ideas" :fields="fields" :small="small" :items-per-page="small ? 10 : 7" pagination @row-clicked="viewingIdea, (viewingModal = true)">
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

        <!-- the modal for viewing -->

    <CModal
      title="title"
      :show.sync="viewingModal"
      :no-close-on-backdrop="true"
      :centered="true"
      size="lg"
      ><CForm>
        <CInput type="hidden" :value="myViewIdea.idea_id" />
        <CInput
          label="Idea Title"
          name="idea_title"
          :placeholder="myViewIdea.idea_title"
          id="idea_title"
          v-model="myViewIdea.idea_title"
          horizontal
        />
        <CTextarea
          label="Description"
          horizontal
          rows="9"
          name="idea_description"
          id="idea_description"
          :placeholder="myViewIdea.idea_description"
          v-model="myViewIdea.idea_description"
        />
        <CButton data-dismiss="modal" type="submit" size="sm" color="success" style="float: right;"
          ><CIcon name="cil-check-circle" /> Submit</CButton
        >
      </CForm>
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
      myViewIdea:"",
      fields: [
        { key: "idea_id" },
        { key: "idea_title" },
        { key: "idea_description" },
        { key: "category_id", label:"Category"},
        { key: "global_user_id", label:"Created By"},
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
      viewingModal:false
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
    },
    saveUpdatedIdea(myIdea) {
      axios
        .put(`http://localhost:8080/ideas/save/${myIdea.idea_id}`, myIdea)
        .then((response) => {
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
        axios
        .delete(`http://localhost:8080/idea/delete/${myIdeaDelete.idea_id}`)
        .then((response) => {
        })
        .catch((e) => {
          console.log(e);
        });
      this.submitted = true;
    },
    viewingIdea: function(item){
      console.log(item)
      this.myViewIdea = {
        idea_id: this.idea_id,
        idea_title: this.idea_title,
        idea_description: this.idea_description,
        category_id: this.category_id,
        global_user_id: this.global_user_id,
      };
      this.myViewIdea = item;
      console.log(this.myViewIdea)
    }
  },

    // this is the function for returning all the ideas
  mounted: function () {
    axios
      .get("http://localhost:8080/list_ideas/")
      // .then(response=> response.json())
      .then((response) => {
        this.ideas = response.data;
        // console.log(data)

        // const results = (response.data).map((idea) => {
        //   return {
        //     idea_id: idea.idea_id,
        //     idea_title: idea.idea_title,
        //     idea_description:idea.idea_description,
        //     // created_by:idea.idea.ideaUserMapping.first_name,
        //     // category_name:idea.ideaCategoryMapping.category_name
        //   };
        // });
        // this.ideas = results;
        // console.table(results)
      })
      .catch((error) => console.log(error));
  },
};
</script>

