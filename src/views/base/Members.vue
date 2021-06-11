<template>
  <div>
    <CCardBody>
      <CDataTable :items="members" :fields="fields" :small="small" :items-per-page="small ? 10 : 7" pagination >
        <CIcon name="cil-grid" /> Simple Table
        <template #update="{ item }">
          <td class="py-2">
            <CButton
              color="primary"
              variant="outline"
              square
              size="sm"
              @click="viewMember(item), (warningModal = true)"
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
      title="Update User Roles"
      :show.sync="warningModal"
      :no-close-on-backdrop="true"
      :centered="true"
      size="lg"
      ><CForm @submit.prevent="saveUpdatedMember(myMember)" v-if="!update">
        <CInput type="hidden" :value="myMember.global_user_id" />
        <CInputRadioGroup
              label="Roles"
              horizontal
              :options="myRoles"
              :value.sync="selectedValue"
            />
        <!-- <div class="form-group row">
					<label class="col-form-label col-4">Roles</label>
					<div class="col-8">
						<th:block th:each="role : ${listRoles}">
							<input type="checkbox" th:field="*{roles}"
								th:value="${role.serial_id}" th:text="${role.role_name}"
								class="m-2" />
						</th:block>
					</div>
				</div>
				<div>
					<button type="submit" class="btn btn-primary">Update Role</button>
				</div>
			</div> -->
        <CButton data-dismiss="modal" type="submit" size="sm" color="success" style="float: right;"
          ><CIcon name="cil-check-circle" /> Submit</CButton
        >
      </CForm>
      <div v-else>
      <h3>User Updated successfully!</h3>
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
      title="Delete User"
      :show.sync="deleteModal"
      
    >
    <CForm @submit.prevent="deleteMember(myMemberDelete)" v-if="!submitted">
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
  name: "Member",
  components: { CTableWrapper },
  props:{
        small: Boolean,
  },
  data() {
    return {
      first_name: "",
      last_name: "",
      primary_email: "",
      global_user_id: "",
      update: false,
      submitted: false,
      members: [],
      myMember: "",
      myMemberDelete:"",
      myRoles: [],
      roles:[],
      selectedValue: [],
      fields: [
        { key: "global_user_id", label:"User ID"},
        { key: "first_name" },
        { key: "last_name" },
        { key: "primary_email", label:"Email"},
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
    viewMember(item) {
      this.myMember = {
        global_user_id: this.global_user_id,
        first_name: this.first_name,
        last_name: this.last_name,
        primary_email: this.primary_email,
        roles: this.selectedValue
      };
      this.myMember = item;
      console.log(this.myMember)
    },
    saveUpdatedMember(myMember) {
      axios
        .put(`http://localhost:8080/roles/save${myMember.global_user_id}`, myMember)
        .then((response) => {
          console.log(response)
        })
        .catch((e) => {
          console.log(e);
        });
      this.update = true;
    },
    confirmDelete(item){
        this.myMemberDelete = {
        global_user_id: this.global_user_id,
        first_name: this.first_name,
        last_name: this.last_name,
        primary_email: this.primary_email,
      };
      this.myMemberDelete = item;
    },
    deleteMember(myMemberDelete) {
        axios
        .delete(`http://localhost:8080/user/delete/${myMemberDelete.global_user_id}`)
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
      .get("http://localhost:8080/list_users/")
      .then((response) => {
        this.members = response.data;
      })
      .catch((error) => console.log(error));

      axios
      .get("http://localhost:8080/list_role")
      .then((response) => {
        console.log(response.data)
        const results = response.data.map((role) => {
          return {
            value: role.serial_id,
            label: role.role_name,
          };
        });
        this.myRoles = results;
        console.log(this.roles)
      })
      .catch((error) => console.log(error));
  },
};
</script>

