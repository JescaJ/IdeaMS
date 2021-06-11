<template>
  <div>
    <CCardBody>
      <CDataTable
        :items="categoryIdea"
        :hover="hover"
        :fields="fields"
        :small="small"
        :items-per-page="small ? 10 : 7"
        pagination
        @row-clicked="viewingIdea"
      >
        <CIcon name="cil-grid" /> Simple Table
        <template  #update="{ item }">
          <td v-if="showAminBoard" class="py-2">
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
          <td v-if="showAminBoard" class="py-2">
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
        <h4>You Updated successfully!</h4>
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
        <h4>Deleted successfully!</h4>
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
      :title="myViewIdea.idea_title"
      :show.sync="viewingModal"
      :no-close-on-backdrop="true"
      :centered="true"
      size="lg"
    >
      <form @submit.prevent="addComment" v-if="!commented" style="width: 700px; margin: 0 auto">
        <input type="hidden" :value="myViewIdea.idea_id" />
        <div class="m-3">
          <div class="form-group row">
            <label class="col-form-label col-4">Idea Title</label>
            <div class="col-8">
              <input
                type="text"
                readonly
                class="form-control"
                :value="myViewIdea.idea_title"
              />
            </div>
          </div>
          <div class="form-group row">
            <label class="col-form-label col-4">Description</label>
            <div class="col-8">
              <input
                type="text"
                readonly
                class="form-control"
                :value="myViewIdea.idea_description"
              />
            </div>
          </div>
          <!-- 					<div class="form-group row">			  -->
          <!-- 						<label class="col-form-label col-4">Add Note</label> -->
          <!-- 						<div class="col-8"> -->
          <!-- 							<textarea rows="5" cols="35" class="form-control" th:value="*{note.note_content}" name="note_content" ></textarea> -->
          <!-- 						</div> -->
          <!--  					</div>	 -->

          <div class="form-group row">
            <div class="center-align">
              <div class="row">
                <div class="column image">
                  <!-- <img src=".../assets" alt="Flowers in Chania" /> -->
                  <CImg
                      src=".../assets/icons/IDMSlogo.png"
                      block
                      shape= "rounded-circle"
                    />
                  <i class="fa fa-image fa-5x fa-pull-left"></i>
                </div>
                <div class="column text" >
                  <!-- {{comments}} -->
                  <ul id="example-1">
                    <li v-for="comm in comments" :key="comm.note_content">
                      {{ comm.note_content }}
                    </li>
                  </ul>
                  Written by Ndagire Jesca
                </div>
              </div>
            </div>
          </div>
          <br />
          <CButton color="primary" @click="isShow = !isShow">Add Comment </CButton>
          <br />
          <div v-if="isShow" class="form-group row hidden-form">
            <label class="col-form-label col-4">Comment</label>
            <div class="col-8">
              <textarea
                rows="5"
                cols="35"
                class="form-control"
                v-model="note_content"
              ></textarea>
            </div>
            <div>
              <br />
              <CButton
                data-dismiss="modal"
                type="submit"
                size="sm"
                color="success"
                style="float: right"
                ><CIcon name="cil-check-circle" /> Submit</CButton
              >
            </div>
          </div>
        </div>
      </form>
      <div v-else>
        <h4>Comment Saved!</h4>
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
  props: {
    small: Boolean,
    hover: Boolean,
  },
  data() {
    return {
      isShow: false,
      idea_id: "",
      idea_title: "",
      idea_description: "",
      category_id: "",
      global_user_id: "",
      note_content: "",
      update: false,
      submitted: false,
      commented:false,
      ideas: [],
      categoryIdea:[],
      ideaUser:[],
      comments:[],
      myIdea: "",
      myIdeaDelete: "",
      myViewIdea: "",
      note_content:"",
      fields: [
        { key: "idea_id" },
        { key: "idea_title" },
        { key: "idea_description" },
        { key: "category_name", label: "Category" },
        { key: "full_name", label: "Created By" },
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
      viewingModal: false,
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ADMIN');
      }

      return false;
    },
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
    viewingIdea(item) {
      this.viewingModal = true;
      this.myViewIdea = {
        idea_id: this.idea_id,
        idea_title: this.idea_title,
        idea_description: this.idea_description,
        category_id: this.category_id,
        global_user_id: this.global_user_id,
      };
      this.myViewIdea = item;
      // console.log(this.myViewIdea);
    },
    addComment() {
      const myComment = {
        note_content: this.note_content,
        global_user_id: this.currentUser.global_user_id,
        idea_id:this.myViewIdea.idea_id
      };
      console.log(myComment)
      axios
        .post("http://localhost:8080/add/comment", myComment)
        .then((response) => {})
        .catch((e) => {
          console.log(e);
        });
      this.commented = true;
    }
  },

  // this is the function for returning all the ideas
  mounted: function () {
    axios
      .get("http://localhost:8080/list_ideas/")
      .then((response) => {
        this.ideas = response.data;
        // console.log(this.ideas)
      })
      .catch((error) => console.log(error));

      axios.get("http://localhost:8080/list_users/")
      .then((response)=>{
        this.myusers = response.data
        this.ideaUser = this.ideas.map((item, i) => Object.assign({}, item, this.myusers[i]));
        // console.log(this.ideaUser)
      })
      .catch((error)=> console.log(error));

      axios.get("http://localhost:8080/list_category/")
      .then((response)=>{
        this.mycategories = response.data
        this.categoryIdea = this.ideas.map((item, i) => Object.assign({}, item, this.mycategories[i])
        );
        // console.log(this.categoryIdea);
        // console.log(i);
      })
      .catch((error)=> console.log(error));
      
      axios.get("http://localhost:8080/list_comments/")
      .then((response)=>{
        this.comments = response.data
      })
      .catch((error)=> console.log(error));
  },
};
</script>

