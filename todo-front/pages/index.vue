<template>
  <div>
    <v-card
      class="d-flex flex-wrap justify-space-around"
      color="lighten-2"
      flat
      tile
    >
      <DraggableCardList
        :title="notCompatibleTitle"
        :list="notCompatibleList"
      />
      <DraggableCardList :title="processTitle" :list="processList" />
      <DraggableCardList :title="endTitle" :list="endList" />
    </v-card>
  </div>
</template>
<script>
import axios from "axios";
import DraggableCardList from "~/components/ui/draggable-card-list.vue";
export default {
  components: { DraggableCardList },
  data() {
    return {
      notCompatibleTitle: "未対応",
      processTitle: "処理中",
      endTitle: "完了",
      notCompatibleList: [],
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
};
</script>
<style lang="scss">
</style>