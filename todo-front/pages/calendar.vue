<template>
  <full-calendar :items="items" />
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
  mounted() {
    axios
      .get("/v1/schedule")
      .then((response) => {
        console.log(response);
        this.items = response.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
<style lang="scss" scope>
</style>