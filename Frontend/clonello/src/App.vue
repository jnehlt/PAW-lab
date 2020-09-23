<template>
  
    <router-view ></router-view>
  
</template>

<script>
import Home from "../src/views/Home";
import Login from "../src/views/Login";
import Signup from "../src/views/Signup";
import ForgotPassword from "../src/views/ForgotPassword";

export default {
  views: {
    home: Home,
    login: Login,
    signup: Signup,
    forgotpassword: ForgotPassword
  }
};
</script>

<style lang="scss">

</style>
