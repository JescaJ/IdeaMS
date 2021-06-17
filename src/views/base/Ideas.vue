<template>
  <div>
    <CCard>
      <CCardHeader>
        <slot name="header"> <CIcon name="cil-grid" /> Ideas </slot>
      </CCardHeader>
      <CCardBody>
        <CInput
          class="mr-sm-2 float-right"
          placeholder="Search by title"
          size="sm"
          type="text"
          v-model="search"
        />
                
        <CDataTable
          :items="filteredIdeas"
          :hover="hover"
          :fields="fields"
          :border="border"
          :small="small"
          :items-per-page="small ? 10 : 7"
          pagination
          @row-clicked="viewingIdea">
          <CIcon name="cil-grid" /> Simple Table
          <template #update="{ item }">
            <td v-if="showAdminBoard" class="py-2">
              <CButton
                color="primary"
                variant="outline"
                square
                size="sm"
                @click="viewIdea(item), (warningModal = true)">
                Update
              </CButton>
            </td>
          </template>
          <template #delete="{ item }">
            <td v-if="showAdminBoard" class="py-2">
              <CButton
                color="primary"
                variant="outline"
                square
                size="sm"
                @click="confirmDelete(item), (deleteModal = true)">
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
      size="lg">
      <CForm
        @submit.prevent="addComment"
        v-if="!commented"
        style="width: 700px; margin: 0 auto">
        <CInput type="hidden" :value="myViewIdea.idea_id" />
        <!-- <div class="d-inline p-2 bg-primary text-white">d-inline</div>
        <div class="d-inline p-2 bg-dark text-white">d-inline</div> -->
        <CCard>
        <div class="m-3">
          <div class="form-group row">
            <label class="col-form-label col-4">Idea Category</label>
            <div class="col-8">
              <input
                type="text"
                readonly
                class="form-control"
                :value="myViewIdea.category_name"
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
          <div style="float:right;">
            <!-- {{myViewIdea}} -->
              <CButton @click="viewIdea(myViewIdea), (warningModal = true)" size="sm" shape="pill" color="info" variant='outline' v-if="myViewIdea.created_by == currentUser.fullname">
                Edit
              </CButton>
            </div>
          
          <br />
          <CButton size="sm" shape="pill" color="info" variant='outline' @click="isShow = !isShow"
            >Add Comment
          </CButton>
          <br>
          <br>
                <!-- <CCard> -->
                  <ul>
                    <p
                      v-for="comm in comments"
                      :key="comm.note_content"
                    >
                      <ul v-if="comm.idea_id == myViewIdea.idea_id">
                        <CCard>
                          <div class="row" style="margin:7; padding: 7;">
                            <div class="col-1">
                              <font-awesome-icon icon="user" class=" fa-pull-right"/>
                            </div>
                            <div class="col-6" style="margin: 0; padding: 0;">
                              <p style="margin: 0; padding: 0; font-size: 10px;"><b>{{ comm.commented_by }}</b></p>
                              <p style="margin: 0; padding: 0; font-size: 10px;">{{ comm.note_content}}</p>
                            </div>
                          </div>
                          </CCard>
                      </ul>
                    </p>
                  </ul>
                <!-- </CCard> -->
                
          <br />
          <div v-if="isShow" >
            <div class="col-8">
              <CTextarea
                label="Comment"
                horizontal
                rows="5"
                cols="35"
                v-model="note_content"
              ></CTextarea>
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
        </CCard>
      </CForm>
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
    border: Boolean,
  },
  data() {
    return {
      isShow: false,
      idea_id: "",
      idea_title: "",
      idea_description: "",
      category_id: "",
      global_user_id: "",
      update: false,
      submitted: false,
      commented: false,
      ideas: [],
      myCategories: [],
      ideaUser: [],
      comments: [],
      myIdea: "",
      myIdeaDelete: "",
      myViewIdea: "",
      search:"",
      fields: [
        { key: "idea_title" },
        { key: "idea_description" },
        { key: "category_name" },
        { key: "created_by" },
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
        return this.currentUser.roles.includes("ADMIN");
      }

      return false;
    },
    filteredIdeas(){
      return this.ideas.filter((idea) => {
        return idea.idea_title.match(this.search);
      });
    }
  },
  methods: {
    viewIdea(item) {
      this.viewingModal = false
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
        .then((response) => {
          // Logic to delete local state
          const deletedIndex = this.filteredIdeas.findIndex(p => p.idea_id === myIdeaDelete.idea_id)
          this.filteredIdeas.splice(deletedIndex, 1)
        })
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
      console.log(this.myViewIdea)
      console.log(this.currentUser)
    },
    addComment() {
      const myComment = {
        note_content: this.note_content,
        global_user_id: this.currentUser.global_user_id,
        idea_id: this.myViewIdea.idea_id,
      };
      axios
        .post("http://localhost:8080/add/comment", myComment)
        .then((response) => {})
        .catch((e) => {
          console.log(e);
        });
      this.commented = true;
    },
    async ideaCategoryFetch() {
      try {
        const ideasFetched = await axios.get(
          "http://localhost:8080/list_ideas/"
        );
        console.log(ideasFetched)
        const categoriesFetched = await axios.get(
          "http://localhost:8080/list_category/"
        );

        // for users
        const usersFetched = await axios.get(
          "http://localhost:8080/list_users/"
        );

        // for comments
        const commentsFetched = await axios.get(
          "http://localhost:8080/list_comments/"
        );

        const result = ideasFetched.data.map((idea) => {
          return {
            idea_id: idea.idea_id,
            idea_title: idea.idea_title,
            idea_description: idea.idea_description,
            category_id: categoriesFetched.data.map((category) => {
              if (idea.category_id == category.category_id) {
                return category.category_name;
              }
            }),
            created_by: usersFetched.data.map((user) => {
              if (idea.global_user_id == user.global_user_id) {
                return user.full_name;
              }
            }),
          };
        });

        console.log(result)

        const results = commentsFetched.data.map((comment) => {
          return {
            idea_id: comment.idea_id,
            note_content: comment.note_content,
            commented_by: usersFetched.data.map((user) => {
              if (comment.global_user_id == user.global_user_id) {
                return user.full_name;
              }
            }),
          };
        });

        const outputs = results.map((ress) => {
          return {
            idea_id: ress.idea_id,
            note_content: ress.note_content,
            commented_by: ress.commented_by.filter((h) => h !== undefined)[0],
          };
        });

        this.comments = outputs;
        console.log(this.comments)

        const output = result.map((res) => {
          return {
            idea_id: res.idea_id,
            idea_title: res.idea_title,
            idea_description: res.idea_description,
            category_name: res.category_id.filter((x) => x !== undefined)[0],
            created_by: res.created_by.filter((y) => y !== undefined)[0],
          };
        });
        this.ideas = output.sort((a,b) => b.idea_id - a.idea_id);
        console.log(this.ideas)
      } catch (error) {
        console.log(error);
      }
    },
  },

  // this is the function for returning all the ideas
  mounted: function () {
    this.ideaCategoryFetch();
  },
};
</script>

