<template>
<div>
  <!-- this is the view that loads the different components -->
  <CNavbar expandable="md" 
          color="dark">
      <CNavbarNav class="mr-auto">
        <CNavItem v-if="showUserBoard">
          <router-link to="/home">
            <font-awesome-icon icon="home"/>Home
          </router-link>
        </CNavItem>
        <CNavItem v-if="showAdminBoard">
          <router-link to="/dashboard" class="nav-link">Admin Board</router-link>
        </CNavItem>
        <CNavItem v-if="showUserBoard">
          <router-link v-if="currentUser" to="/createIdea" class="nav-link">Create Idea</router-link>
        </CNavItem>
        <CNavItem v-if="showUserBoard">
          <router-link v-if="currentUser" to="/ideas" class="nav-link">View Ideas</router-link>
        </CNavItem>
      </CNavbarNav>

      <CNavbarNav class="ml-auto" v-if="!currentUser">
        <CNavItem>
          <router-link to="/register" class="nav-link">
            <font-awesome-icon icon="user-plus" />Sign Up
          </router-link>
        </CNavItem>
        <CNavItem>
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" />Login
          </router-link>
        </CNavItem>
      </CNavbarNav>

      <CNavbarNav v-if="currentUser" class="ml-auto">
        <CNavItem>
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" />
            {{ currentUser.fullname }}
          </router-link>
        </CNavItem>
        <CNavItem>
          <a class="nav-link" href @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" />LogOut
          </a>
        </CNavItem>
      </CNavbarNav>
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
