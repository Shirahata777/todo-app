<template>
  <v-row>
    <v-col class="text-center">
      {{data.todono}}
      {{data.userNo}}
      {{data.title}}
      {{data.content}}
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      items: [
        {},
      ],
    };
  },
  async asyncData({ params }) {
    const {data} = await axios
      .get(`/v1/todo/${params.todono}/detail`)
      .catch((err) => {
        console.log(err);
      });

      console.log(data);

      return {
        data : data
      };
  },
};
</script>
<style lang="scss">

</style>
