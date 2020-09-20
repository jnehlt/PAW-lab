<template>
  <div id="app">
    <topheader></topheader>
    <projectheader></projectheader>
    <smallcards></smallcards> 
 
   


   
  </div>
</template>

<script>
import TopHeader from "@/components/TopHeader.vue";
import ProjectHeader from "@/components/ProjectHeader.vue";
import SmallCards from "@/components/SmallCards.vue";

export default {
  components: {
    "topheader": TopHeader,
    "projectheader": ProjectHeader,
    "smallcards": SmallCards,
 
  }
};
</script>

<style lang="scss">

body {
    background-image: url("../src/assets/running-track-sn-3000x2001.jpg");
    background-size: cover;
    background-position: center;
}
#app{
  font-size: 20px;
}

.dropdown-header {
    font-size: larger;
    font-weight: bolder;
}

html, body {
    height: 100%;
}

.list-head {
    font-weight: bolder;
    text-transform: uppercase;
}

.row {
  display: flex;
  flex-wrap: nowrap;
  overflow-x: auto;
  margin-left: -5px;
  margin-right: -5px;
  margin-top: 10px;
  height: 100vh;
}

::-webkit-scrollbar {
  height: 4px;              /* height of horizontal scrollbar ← You're missing this */
  width: 4px;               /* width of vertical scrollbar */
  border: 1px solid #d5d5d5;
  margin-left: -5px;
  margin-right: -5px;
}


</style>
