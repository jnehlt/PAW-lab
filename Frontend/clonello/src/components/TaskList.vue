<template>
  <div>
    <input type="text" class="list-input" placeholder="Add item to current list" v-model="newlist" @keyup.enter="addlist">
    <transition-group name="fade" enter-active-class="animated fadeInUp" leave-active-class="animated fadeOutDown">
    <div v-for="(list, index) in listsFiltered" :key="list.id" class="list-item">
      <div class="list-item-left">
        <input type="checkbox" v-model="list.completed">
        <div v-if="!list.editing" @dblclick="editlist(list)" class="list-item-label" :class="{ completed : list.completed }">{{ list.title }}</div>
        <input v-else class="list-item-edit" type="text" v-model="list.title" @blur="doneEdit(list)" @keyup.enter="doneEdit(list)" @keyup.esc="cancelEdit(list)" v-focus>
      </div>
      <div class="remove-item" @click="removelist(index)">
        <i class="fa fa-trash" aria-hidden="true"></i>
      </div>
    </div>
    </transition-group>

    <div class="extra-container">
      <div><label><input type="checkbox" :checked="!anyRemaining" @change="checkAlllists"> Check All</label></div>
      <div class="item-counter">{{ remaining }} items left</div>
    </div>

    <div class="extra-container">
      <div>
        <button :class="{ active: filter == 'all' }" @click="filter = 'all'">All</button>
        <button :class="{ active: filter == 'active' }" @click="filter = 'active'">Active</button>
        <button :class="{ active: filter == 'completed' }" @click="filter = 'completed'">Completed</button>
      </div>

      <div>
        <transition name="fade">
        <button v-if="showClearCompletedButton" @click="clearCompleted">Clear Completed</button>
        </transition>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: 'list-list',
  data () {
    return {
      newlist: '',
      idForlist: 1,
      beforeEditCache: '',
      filter: 'all',
      lists: [
      ]
    }
  },
  computed: {
    remaining() {
      return this.lists.filter(list => !list.completed).length
    },
    anyRemaining() {
      return this.remaining != 0
    },
    listsFiltered() {
      if (this.filter == 'all') {
        return this.lists
      } else if (this.filter == 'active') {
        return this.lists.filter(list => !list.completed)
      } else if (this.filter == 'completed') {
        return this.lists.filter(list => list.completed)
      }
      return this.lists
    },
    showClearCompletedButton() {
      return this.lists.filter(list => list.completed).length > 0
    }
  },
  directives: {
    focus: {
      inserted: function (el) {
        el.focus()
      }
    }
  },
  methods: {
    addlist() {
      if (this.newlist.trim().length == 0) {
        return
      }
      this.lists.push({
        id: this.idForlist,
        title: this.newlist,
        completed: false,
        editing: false,
      })
      this.newlist = ''
      this.idForlist++
    },
    editlist(list) {
      this.beforeEditCache = list.title
      list.editing = true
    },
    doneEdit(list) {
      if (list.title.trim() == '') {
        list.title = this.beforeEditCache
      }
      list.editing = false
    },
    cancelEdit(list) {
      list.title = this.beforeEditCache
      list.editing = false
    },
    removelist(index) {
      this.lists.splice(index, 1)
    },
    checkAlllists() {
      this.lists.forEach((list) => list.completed = event.target.checked)
    },
    clearCompleted() {
      this.lists = this.lists.filter(list => !list.completed)
    }
  }
}
</script>

<style lang="scss">
  @import url("https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css");
  .list-input {
    width: 100%;
    padding: 10px 18px;
    font-size: 18px;
    margin-bottom: 16px;
    &:focus {
      outline: 0;
    }
  }
  .list-item {
    margin-bottom: 12px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    animation-duration: 0.3s;
  }
  .remove-item {
    cursor: pointer;
    margin-left: 14px;
    &:hover {
      color: blue;
    }
    color: red
    
  }
  .list-item-left { // later
    display: flex;
    align-items: center;
  }
  .list-item-label {
    padding: 10px;
    margin-left: 12px;
    background-color: white;
    border-radius: 20%;
    color: black;
  }
  .list-item-edit {
    font-size: 24px;
    color: #2c3e50;
    margin-left: 12px;
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc; //override defaults
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    &:focus {
      outline: none;
    }
  }
  .completed {
    text-decoration: line-through;
    color: grey;
  }
  .extra-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 16px;
    border-top: 1px solid lightgrey;
    padding-top: 14px;
    margin-bottom: 14px;
  }
  button {
    font-size: 14px;
    background-color: white;
    appearance: none;
    border-radius: 20%;
    &:hover {
      background: blue;
    }
    &:focus {
      outline: none;
    }
   
  }
  .active {
    background: #007bff;
  }
  // CSS Transitions
  .fade-enter-active, .fade-leave-active {
    transition: opacity .2s;
  }
  .fade-enter, .fade-leave-to {
    opacity: 0;
  }
  label {
    color:white
  }
  .item-counter {
    color: white
  }
</style>