<template>
  <v-row>
    <v-col class="text-center">
      {{data.todo_no}}
      {{data.user_no}}
      {{data.title}}
      {{data.content}}
      {{data.start}}
      {{data.end}}
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
      .get(`/v1/todo/${params.todono}`)
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
