<template>
    <div class="loginform">
        <form name="form" @submit.prevent="handleLogin">
            <h3 style="text-align: center">Sign In</h3>

            <div class="form-group">
                <label for="email">Email</label>
                <input v-validate="required" type="email" class="form-control form-control-lg" name="email" placeholder="name@example.com" />
                <div v-if="errors.has('email')" class="alert alert-danger" role="alert">Username is required!</div>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input v-validate="required" type="password" class="form-control form-control-lg" name="password" />
                <div v-if="errors.has('password')" class="alert alert-danger" role="alert">Password is required!</div>
            </div>

            <button type="button" class="sendbutton btn btn-dark btn-lg btn-block">
                <router-link to="/" exact>Sign In</router-link>
            </button>

            <button type="button" class="signup btn btn-primary">
                <router-link to="/signup" exact>Sign up</router-link>
            </button>
            
            <button type="button" class="forgot-password btn btn-primary">
                <router-link to="/forgotpassword" exact>Forgot password?</router-link>
            </button>
        </form>
    </div>
</template>

<script>
import User from "../models/user"


export default {
    data () {
        return {
            user: new User('', ''),
            loading: false,
            message: ''
        };
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn
        }
    },
    created() {
        if (this.loggedIn) {
            this.$router.push("/");
        }
    },
    methods: {
        handleLogin() {
            this.loading = true;
            this.$validator.validateAll().then(isValid => {
                if (!isValid) {
                    this.loading = false;
                    return;
                }

                if (this.user.email && this.user.password) {
                    this.$store.dispatch("auth/login", this.user).then(
                        () => {
                            this.$router.push("/");
                        },
                        error => {
                            this.loading = false;
                            this.message = 
                                (error.response && error.response.data) ||
                                error.message ||
                                error.toString();
                        }
                    );
                }
            });
        }
    }
};

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