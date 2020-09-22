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
            {{ MainList.title }}
            <div class="input-group mb-3">
              <input
                type="text"
                class="form-control"
                placeholder="Add title of MainList"
                aria-label="Add title of MainList"
                aria-describedby="basic-addon2"
                v-model="MainList.enterText"
                @keyup.enter="addCard(MainList)"
              />
              <div class="input-group-append">
                <button class="input-group-text" id="basic-addon2" @click="addCard(MainList)">Add</button>
              </div>
            </div>
            <div v-for="(card) in cardsForList(MainList.id)" :key="card.id">
              <div>
                <b-button
                  class="modalForCards"
                  v-on:click="modalCard(card)"
                  v-b-modal.modal-1
                >{{ card.title }}</b-button>
                <b-modal v-if="activeCard == card.id" id="modal-1" v-bind:title="card.title">
                  <p class="my-4">
                    <tasklist></tasklist>
                  </p>
                </b-modal>

                <div class="remove-item" @click="removeCard(card.id)">
                  <i class="fa fa-trash" aria-hidden="true"></i>
                </div>
              </div>
            </div>

            <footer @click="removeMainLists(index)">
              <i class="far fa-times-circle"></i>
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
      filter: 0,
      cards: []
    };
  },
  mounted() {
    TaskList;
  },
  methods: {
    cardsForList(listId) {
      return this.cards.filter(x => x.listId === listId);
    },
    addMainList() {
      if (this.newMainList.trim().length == 0) {
        return;
      }
      this.MainLists.push({
        id: this.idForMainList,
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
    addCard(mainList) {
      if (mainList.enterText.trim().length == 0) {
        return;
      }
      this.cards.push({
        id: this.idForCard,
        title: mainList.enterText,
        listId: mainList.id,
        completed: false
      });
      mainList.enterText = "";
      this.idForCard++;
    },
    modalCard(card) {
      this.activeCard = card.id;
    },
    removeCard(activeCard) {
      let card = this.cards.filter(x => x.id === activeCard)[0];
      if (card !== undefined && card !== null) {
        this.cards = this.cards.filter(x => x !== card);
      }
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
  margin-right: 20%;
}
.modalForCards {
  width: 100%;
}
footer:hover {
    color:red;
}
</style>
