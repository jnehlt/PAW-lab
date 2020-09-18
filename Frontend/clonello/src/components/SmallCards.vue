<template>
<div>

<div class="input-group mb-3">
  <input type="text" class="form-control" placeholder="Insert title of card" aria-label="Insert title of card" aria-describedby="basic-addon2" v-model="newCard">
  <div class="input-group-append">
    <button class="btn btn-outline-secondary" type="button" @click="addCard" @keyup.enter="addCard">Add card</button>
  </div>
</div>
<div class="container">
 <div class="row">
    <div v-for="(card) in cards" :key="card.id" style="margin-right:10px;">      
            <div class="col-sm" style="width:100%">
            {{card.title}}
        
            <tasklist></tasklist>
            </div>
        </div>
  </div>
</div>
</div>

</template>
<script>
import TaskList from './TaskList.vue'
export default {
    name:'card-lists',
    data() {
        return {
            newCard: '',
            idForCard: 1,
            cards: []
    }
    },
    mounted() {
        TaskList
    },
    methods:{
        addCard() {
            this.cards.push({
                id:this.id,
                title:this.newCard,
                completed:false
            })
            this.newCard=''
            this.idForCard++
        },
        taskListComp: function() {
             alert("eowfewf")
        }
    },
    components: {
        'tasklist':TaskList
    }
};
</script>
<style lang="scss">
.col-sm{
    background-color: aqua;
    height: 60%;
    margin-right: 20%

}
</style>