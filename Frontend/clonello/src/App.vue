<template>
  <div id="app">
    <topheader></topheader>
    <projectheader></projectheader>

    <div class="container">
      <div class="row">
        <div class="col-4 col-md-4 col-sm-4">
          <ul class="list-group">
            <li class="list-group-item disabled list-head" aria-disabled="true">Kategoria<button class="btn btn-kropki"><i class="fas fa-ellipsis-v"></i></button></li>
            <li class="list-group-item">Nazwa zadania</li>
            <li class="list-group-item">Kolejne zadanie</li>
            <li class="list-group-item">I jeszcze jedno</li>
            <li class="list-group-item">I tak w kółko Macieju</li>
          </ul>
        </div>
        <div class="col-4 col-md-4 col-sm-4">
          <ul class="list-group">
            <li class="list-group-item disabled list-head" aria-disabled="true">Kategoria<button class="btn btn-kropki"><i class="fas fa-ellipsis-v"></i></button></li>
            <li class="list-group-item">Nazwa zadania</li>
            <li class="list-group-item">Kolejne zadanie</li>
            <li class="list-group-item">I jeszcze jedno</li>
            <li class="list-group-item">I tak w kółko Macieju</li>
          </ul>
        </div>
        <div class="col-4 col-md-4 col-sm-4">
          <ul class="list-group">
            <li class="list-group-item disabled list-head" aria-disabled="true">Kategoria<button class="btn btn-kropki"><i class="fas fa-ellipsis-v"></i></button></li>
            <li class="list-group-item">Nazwa zadania</li>
            <li class="list-group-item">Kolejne zadanie</li>
            <li class="list-group-item">I jeszcze jedno</li>
            <li class="list-group-item">I tak w kółko Macieju</li>
          </ul>
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>
import TopHeader from "@/components/TopHeader.vue";
import ProjectHeader from "@/components/ProjectHeader.vue"

export default {
  components: {
    "topheader": TopHeader,
    "projectheader": ProjectHeader 
  }
};
</script>

<style lang="scss">
body {
    background-image: url("../src/assets/running-track-sn-3000x2001.jpg");
    background-size: cover;
    background-position: center;
}

.dropdown-header {
    font-size: larger;
    font-weight: bolder;
}

html {
    height: 100%;
}

.list-head {
    font-weight: bolder;
    text-transform: uppercase;
}

.btn {
    margin-left: 2px;
}

.container {
    margin-top: 30px;
}

</style>
