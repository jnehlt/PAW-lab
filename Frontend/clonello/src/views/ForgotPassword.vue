<template>
    <div class="loginform">
        <form>
            <h3 style="text-align: center">Forgot password</h3>

            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control form-control-lg" placeholder="name@example.com" />
            </div>

            <button type="button" class="sendbutton btn btn-dark btn-lg btn-block">
                <router-link to="/login" exact>Send email</router-link>
            </button>

            <button type="button" class="signup btn btn-primary">
                <router-link to="/signup" exact>Sign up</router-link>
            </button>
            
            <button type="button" class="forgot-password btn btn-primary">
                <router-link to="/login" exact>Sign In</router-link>
            </button>
        </form>
    </div>
</template>

<script>
export default {

}
</script>

<style lang="scss">

body {
    background-image: url("../assets/running-track-sn-3000x2001.jpg");
    background-size: cover;
    background-position: center;
}

html, body {
    height: 100%;
}


.loginform {
  width: 30%;
  height: 100%;
  margin: 0 auto;
  margin-top: 12%;
}

label {
  font-weight: 500;
}

.signup {
    float: right;
}

.forgot-password,
.forgot-password a,
.sendbutton {
  text-align: center;
  font-size: 13px;
  color: #7a7a7a;
  margin-top: 10px;
}

.forgot-password a,
.sendbutton a,
.signup a {
  color: #ffffff;
}


</style>