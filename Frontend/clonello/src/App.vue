<template>
  
    <router-view ></router-view>
  
</template>

<script>
  import Home from "../src/views/Home"
  import Login from "../src/views/Login"


  export default {
    views: {
      'home': Home,
      'login': Login
    }
  }

</script>

<style lang="scss">

</style>
