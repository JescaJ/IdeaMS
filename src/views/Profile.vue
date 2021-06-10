<template>
  <div class="container">
    <header class="jumbotron">
      <h3 style="text-align: center">
        <strong>{{ currentUser.fullname }}</strong> Profile
      </h3>
    </header>
    <!-- <p>
      <strong>Token:</strong>
      {{currentUser.accessToken.substring(0, 20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
    </p>
    <p>
      <strong>Id:</strong>
      {{currentUser.global_user_id}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.primary_email}}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul> -->
    <form
      style="width: 800px; margin: 0 auto; border-radius: 25px"
      data-toggle="validator"
    >
      <input type="hidden" :value="currentUser.global_user_id" />
      <div class="m-3">
        <div class="form-group row">
          <label class="col-form-label col-4"><b>Prefix:</b></label>
          <div class="col-8">
            <!-- <select  class="form-control" readonly :value="currentUser.prefix_id">
              <option th:each="prefix : ${prefixList}" th:value="${prefix.prefix_id}"  th:text="${user.userPrefixMapping.prefix_name}"/>
            </select> -->
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.prefix_id"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-form-label col-4"><b>First Name:</b></label>
          <div class="col-8">
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.first_name"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-form-label col-4"><b>Last Name:</b></label>
          <div class="col-8">
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.last_name"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-form-label col-4"><b>Gender:</b></label>
          <div class="col-8">
            <!-- <select  class="form-control" readonly>
								<option th:each="gender : ${genderList}" th:value="${gender.gender_id}"  th:text="${user.userGenderMapping.gender_name}"/>
							</select> -->
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.gender_id"
            />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-form-label col-4"><b>Email:</b></label>
          <div class="col-8">
            <input
              type="text"
              readonly
              class="form-control"
              :value="currentUser.primary_email"
            />
          </div>
        </div>
      </div>
      <!-- <div>
        <button
          type="button"
          class="edit btn btn-primary"
          @click="inputToggle(event)"
        >
          Edit
        </button>
        <button type="submit" class="save btn btn-primary">Save</button>
        <button type="button" class="cancel btn btn-primary">Cancel</button>
      </div> -->
    </form>
  </div>
</template>

<script>
export default {
  name: "Profile",
  data() {
    return {
      edit: true,
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }
  },
  methods: {
    inputToggle(e) {
      e.preventDefault();
      ":input".prop("readonly", (edit = !edit));
      ".edit".click(function () {
        this.hide();
        this.siblings(".save, .cancel").show();
      });
      ".cancel".click(function () {
        this.siblings(".edit").show();
        this.siblings(".save").hide();
        this.hide();
      });
      ".save".click(function () {
        this.siblings(".edit").show();
        this.siblings(".cancel").hide();
        this.hide();
      });
    },
  },
};
</script>
