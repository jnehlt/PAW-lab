<template>
    <div class="login">
        <form>
            <h3 style="text-align: center">Sign In</h3>

            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control form-control-lg" placeholder="name@example.com" />
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control form-control-lg" />
            </div>

            <button type="button" class="signin btn btn-dark btn-lg btn-block">
                <router-link to="/home" exact>Sign In</router-link>
            </button>

            <button type="button" class="signup btn btn-primary">
                <router-link to="/sign-up" exact>Sign up</router-link>
            </button>
            
            <button type="button" class="forgot-password btn btn-primary">
                <router-link to="/forgot-password" exact>Forgot password?</router-link>
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


.login {
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
.signup {
  text-align: center;
  font-size: 13px;
  color: #7a7a7a;
  margin-top: 10px;
}

.forgot-password a,
.signup a,
.sihnin a {
  color: #ffffff;
}


</style>