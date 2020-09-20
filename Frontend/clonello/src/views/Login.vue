<template>
    <div class="loginform">
        <form name="form" @submit.prevent="handleLogin">
            <h3 style="text-align: center">Sign In</h3>

            <div class="form-group">
                <label for="email">Email</label>
                <input v-model="user.name" v-validate="required" type="email" class="form-control form-control-lg" name="name" placeholder="Your email address" >
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input v-model="user.password" v-validate="required" type="password" class="form-control form-control-lg" name="password" >
            </div>

            <button type="button" class="sendbutton btn btn-dark btn-lg btn-block" @click="handleLogin">Login</button>

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
    name: 'Login',
    data () {
        return {
            user: new User('', ''),
            loading: false,
            message: ''
        };
    },

    // mounted () { axios .get('https://api.coindesk.com/v1/bpi/currentprice.json') .then(response => (this.info = response)) },

    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        }
    },
    created() {
        if (this.loggedIn) {
            this.$router.push("/home");
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

                if (this.user.name && this.user.password) {
                    this.$store.dispatch("auth/login", this.user).then(
                        () => {
                            this.$router.push("/home");
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