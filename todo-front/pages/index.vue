<template>
  <v-card
    class="d-flex flex-wrap justify-space-around"
    color="lighten-2"
    flat
    tile
  >
    <DraggableCardList :list="notCompatible" />
    <DraggableCardList :list="processList" />
    <DraggableCardList :list="endList" />
  </v-card>
</template>
<script>
import axios from "axios";
import DraggableCardList from "~/components/ui/todo-card-list.vue";
export default {
  components: { DraggableCardList },
  data() {
    return {
      notCompatible: [],
      processList: [],
      endList: [],
    };
  },
  async asyncData() {
    const { data } = await axios.get("/v1/todo/").catch((err) => {
      console.log(err);
    });

    return {
      processList: data,
    };
  },
  watch: {
    processList() {
      console.log(this.processList);
    },
  },
};
</script>
<style lang="scss">
</style>