<template>
  <div>
    <div class="input-group mb-3">
      <input
        type="text"
        class="form-control"
        placeholder="Insert title of list"
        aria-label="Insert title of list"
        aria-describedby="basic-addon2"
        v-model="newMainList"
        @keyup.enter="addMainList"
      />
      <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="button" @click="addMainList">Add MainList</button>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div v-for="(MainList, index) in MainLists" :key="MainList.id" style="margin-right:10px;">
          <div class="col-sm" style="width:100%">
            {{MainList.title}}
            <div class="input-group mb-3">
              <input
                type="text"
                class="form-control"
                placeholder="Add title of MainList"
                aria-label="Add title of MainList"
                aria-describedby="basic-addon2"
                v-model="newCard"
                @keyup.enter="addCard"
              />
              <div class="input-group-append">
                <button class="input-group-text" id="basic-addon2" @click="addCard">Add</button>
              </div>
            </div>
            <div v-for="(card, index) in cards" :key="card.id">
              <div>
                <b-button v-on:click="modalCard(card)" v-b-modal.modal-1>{{card.title}}</b-button>
                <b-modal v-if="activeCard==card.id" id="modal-1" v-bind:title="card.title">
                  <p class="my-4">
                    <tasklist></tasklist>
                  </p>
                </b-modal>
                <div class="remove-item" @click="removeCard(index)">
                  <i class="fa fa-trash" aria-hidden="true"></i>
                </div>
              </div>
            </div>

            <footer @click="removeMainLists(index)">
              <span>Remove MainList</span>
              <svg
                width="1em"
                height="1em"
                viewBox="0 0 16 16"
                class="bi bi-trash"
                fill="currentColor"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
                />
                <path
                  fill-rule="evenodd"
                  d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
                />
              </svg>
            </footer>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import TaskList from "./TaskList.vue";
export default {
  name: "MainList-lists",
  data() {
    return {
      newMainList: "",
      newCard: "",
      idForMainList: 1,
      idForCard: 100,
      MainLists: [],
      activeCard: 0,
      cards: []
    };
  },
  mounted() {
    TaskList;
  },
  methods: {
    addMainList() {
      if (this.newMainList.trim().length == 0) {
        return;
      }
      this.MainLists.push({
        id: this.id,
        title: this.newMainList,
        completed: false
      });
      this.newMainList = "";
      this.idForMainList++;
    },
    taskListComp: function() {
      alert("eowfewf");
    },
    removeMainLists(index) {
      this.MainLists.splice(index, 1);
    },
    buttonClc: function() {
      window.open("Home.vue");
    },
    addCard(parentId) {
      if (this.newCard.trim().length == 0) {
        return;
      }
      this.cards.push({
        id: this.idForCard,
        title: this.newCard,
        listId: parentId,
        completed: false
      });
      this.newCard = "";
      this.idForCard++;
    },
    modalCard(card) {
      this.activeCard = card.id;
    },
    removeCard(index) {
      this.cards.splice(index, 1);
    }
  },
  components: {
    tasklist: TaskList
  }
};
</script>
<style lang="scss">
.col-sm {
  background-color: white;
  border-radius: 2%;
  height: 60%;
  margin-right: 20%;
}
</style>