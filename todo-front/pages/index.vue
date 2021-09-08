<template>
  <v-card
    class="d-flex flex-wrap justify-space-around"
    color="lighten-2"
    flat
    tile
  >
  <draggable v-model="items">
    <template v-for="(item, index) in items">
      
      <v-card
        class="pa-2"
        max-width="344"
        outlined
        tile
        :key="index"
      >
        <nuxt-link :to="'/inspire/' + item['todo_no']">
          <v-card-title v-text="item.title"> </v-card-title>
          <v-card-subtitle> {{ item.start }} / {{ item.end }}</v-card-subtitle>
          <v-card-text v-text="item.content"> </v-card-text>
        </nuxt-link>
      </v-card>
    </template>
    </draggable>
  </v-card>
</template>
<script>
import axios from "axios";
import draggable from 'vuedraggable'
export default {
  components: { draggable },
  data() {
    return {
      items: [{}],
    };
  },
  async asyncData() {
    const { data } = await axios.get("/v1/todo/").catch((err) => {
      console.log(err);
    });

    return {
      items: data,
    };
  },
  watch: {
    items() {
      console.log(this.items);
    }
  }
};
</script>
<style lang="scss">
</style>