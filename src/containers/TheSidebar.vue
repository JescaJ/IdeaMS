<template>
  <CSidebar 
    fixed 
    :minimize="minimize"
    :show="show"
    @update:show="(value) => $store.commit('set', ['sidebarShow', value])"
  >
    <CSidebarBrand class="d-md-down-none" to="/">
      <!-- <CIcon 
        class="c-sidebar-brand-full" 
        name="logo" 
        title="AIDEA"
        size="custom-size" 
        :height="35" 
        viewBox="0 0 556 134"
      />
      <CIcon 
        class="c-sidebar-brand-minimized" 
        name="logo"        
        size="custom-size" 
        :height="35" 
        viewBox="0 0 110 134"
      /> -->
      <!-- <CImg
    src="logos"
    block
    class="mb-2"
  /> -->
  <h2 style="text-decoration:none;">AIDEA</h2>
    </CSidebarBrand>

    <CRenderFunction flat :content-to-render="$options.nav"/>
    <CSidebarMinimizer
      class="d-md-down-none"
      @click.native="$store.commit('set', ['sidebarMinimize', !minimize])"
    />
  </CSidebar>
</template>

<script>
import nav from './_nav'
import logos from '../assets/IDMSlogo.png';

export default {
  name: 'TheSidebar',
  nav,
  logos,
  computed: {
    show () {
      return this.$store.state.sidebarShow 
    },
    minimize () {
      return this.$store.state.sidebarMinimize 
    }
  }
}
</script>

<style>
.c-sidebar.c-sidebar-fixed {
    position: fixed;
    top: 8%;
    bottom: 0;
    z-index: 1030;
}
</style>
