<template>
  <div>
    <h3 style="text-align: center">{{ title }}</h3>
    <v-card color="blue lighten-5" width="50wv" class="draggable_card">
      <draggable :options="{ group: 'ITEMS' }" v-model="items">
        <div v-for="(item, index) in items" :key="index">
          <v-card class="pa-2" outlined tile width="30wv">
            <nuxt-link :to="'/inspire/' + item.todo_no">
              <v-card-title
                v-text="item.title"
                class="draggable_card_text text-subtitle-2"
              >
              </v-card-title>
              <v-card-text class="draggable_card_text text-caption"
                >{{ item.start }} / {{ item.end }}</v-card-text
              >
            </nuxt-link>
          </v-card>
        </div>
      </draggable>
    </v-card>
  </div>
</template>

<script>
import draggable from "vuedraggable";
export default {
  props: ["title", "list"],
  components: { draggable },
  data() {
    return {
      items: this.list,
    };
  },
  watch: {
    items() {
      console.log(this.items);
    },
  },
};
</script>

<style lang="scss">
.draggable_card_text {
  color: #263238 !important;
}
.draggable_card {
  border: solid;
  width: 20vw;
  padding: 3px;
  min-height: 80vh;
}
</style>