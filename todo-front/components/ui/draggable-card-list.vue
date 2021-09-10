<template>
  <draggable :options="{ group: 'ITEMS' }" v-model="item">
    <template v-for="(l, index) in item">
      <v-card class="pa-2" max-width="344" outlined tile :key="index">
        <nuxt-link :to="'/inspire/' + l.todo_no">
          <v-card-title v-text="l.title"> </v-card-title>
          <v-card-subtitle> {{ l.start }} / {{ l.end }}</v-card-subtitle>
          <v-card-text v-text="l.content"> </v-card-text>
        </nuxt-link>
      </v-card>
    </template>
  </draggable>
</template>

<script>
import draggable from "vuedraggable";
export default {
  props: ["list"],
  components: { draggable },
  data() {
    return {
      item: this.list,
    };
  },
};
</script>

<style lang="scss" scoped>
</style>