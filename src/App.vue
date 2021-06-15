<template>
<div>
  <!-- this is the view that loads the different components -->
  <CNavbar expandable="md" class="fixed-top"
          color="dark">
      <a href class="navbar-brand" @click.prevent>IdeaMS</a>
      <div class="navbar-nav mr-auto">
        <li v-if="showUserBoard" class="nav-item">
          <router-link to="/home" class="nav-link">
            <font-awesome-icon icon="home"/>Home
          </router-link>
        </li>
        <li v-if="showAdminBoard" class="nav-item">
          <router-link to="/dashboard" class="nav-link">Admin Board</router-link>
        </li>
        <li v-if="showUserBoard" class="nav-item">
          <router-link v-if="currentUser" to="/createIdea" class="nav-link">Create Idea</router-link>
        </li>
        <li v-if="showUserBoard" class="nav-item">
          <router-link v-if="currentUser" to="/ideas" class="nav-link">View Ideas</router-link>
        </li>
      </div>

      <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/register" class="nav-link">
            <font-awesome-icon icon="user-plus" />Sign Up
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" />Login
          </router-link>
        </li>
      </div>

      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" />
            {{ currentUser.fullname }}
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" />LogOut
          </a>
        </li>
      </div>
  </CNavbar>
  <router-view></router-view> 
  </div>
</template>

<script>
export default {
  name: 'App',
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
    showUserBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('USER');
      }

      return false;
    },
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }

}
</script>

<style lang="scss">
  // Import Main styles for this application
  @import 'assets/scss/style';
</style>
