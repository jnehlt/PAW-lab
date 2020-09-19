import Vue from "vue";
import VueRouter from "vue-router";


import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Signup from "../views/Signup.vue";
import ForgotPassword from "../views/ForgotPassword.vue";

Vue.use(VueRouter);

export const router = new VueRouter({
  mode: 'history',
  routes: [
    { path: "/", component: Home },
    { path: "/login", component: Login },
    { path: "/signup", component: Signup },
    { path: "/forgotpassword", component: ForgotPassword },

    { path: "*", redirect: "/"}

  ]
});

router.beforeEach((to, from, next) => {
  // to and from are both route objects. must call `next`.
  const publicPage = ["/login"];
  const authRequired = !publicPage.includes(to.path);
  const loggedIn = localStorage.getItem("user")

  if (authRequired && !loggedIn) {
    return next({
      path: "/login",
      query: { returnUrl: to.path }
    });
  }

  next();
})

export default router;
