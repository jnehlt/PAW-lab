import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
      path: "/",
      name: "Login",
      component: () => import ("../views/Login.vue")
  },
  {
    path: "/home",
    name: "Home",
    component: () => import ("../views/Home.vue")
  },
  {
    path: "/sign-up",
    name: "Signup",
    component: () => import ("../views/Signup.vue")
  },
  {
    path: "/forgotpassword",
    name: "Forgot Password",
    component: () => import ("../views/ForgotPassword.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
