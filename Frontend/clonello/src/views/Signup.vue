<template>
    <div class="loginform">
        <form @submit.prevent="handleRegister">
            <h3 style="text-align: center">Sign Up</h3>

            <div class="form-group">
                <label>First Name</label>
                <input v-model="user.firstName" v-validate="'required|min:3|max:20'" type="firstName" class="form-control form-control-lg" name="firstname" placeholder="First Name" />
            </div>

            <div class="form-group">
                <label>Last Name</label>
                <input v-model="user.lastName" v-validate="'required|min:3|max:20'" type="lastName" class="form-control form-control-lg" name="lastname" placeholder="Last Name" />
            </div>

            <div class="form-group">
                <label>Email</label>
                <input v-model="user.email" v-validate="'required|email|max:50'" type="email" class="form-control form-control-lg" name="email" placeholder="name@example.com" />
            </div>

            <div class="form-group">
                <label>Password</label>
                <input v-model="user.password" v-validate="'required|min:4|max:40'" type="password" class="form-control form-control-lg" name="password" placeholder="Password: minimum 4 characters"/>
            </div>

            <button type="button" class="sendbutton btn btn-dark btn-lg btn-block" @click="handleRegister">Sign Up</button>

            <button type="button" class="signup btn btn-primary">
                <router-link to="/login" exact>Sign In</router-link>
            </button>
            
            <button type="button" class="forgot-password btn btn-primary">
                <router-link to="/forgotpassword" exact>Forgot password?</router-link>
            </button>
        </form>
    </div>
</template>

<script>
import User from "../models/user";

export default {
    name: 'Register',
    data() {
        return {
            user: new User('', '', '', ''),
            submitted: false,
            successful: false,
            message: ''
        };
    },

    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        }
    },

    mounted() {
        if (this.loggedIn) {
            this.$router.push('/login');
        }
    },

    methods: {
        handleRegister() {
            this.message = '';
            this.submitted = true;
            this.$validator.validate().then(isValid => {
                if (isValid) {
                    this.$store.dispatch('auth/register', this.user).then(
                        data => {
                            this.message = data.message;
                            this.successful = true;
                            this.$router.push('/login');
                        },
                        error => {
                            this.message =
                                (error.respone && error.response.data) ||
                                error.message ||
                                error.toString();
                            this.successful = false;                                
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
.signup {
  text-align: center;
  font-size: 13px;
  color: #7a7a7a;
  margin-top: 10px;
}

.forgot-password a,
.signup a {
  color: #ffffff;
}


</style>