<template>
  <full-calendar :eventDataList="eventDataList" />
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      eventDataList: [],
    };
  },
  mounted() {
    axios
      .get("/v1/schedule")
      .then((response) => {
        console.log(response);
        this.items = response.data;

        for (let i = 0; i < this.items.length; i++) {
          let events = {
            title: "",
            start: "",
            start: this.items[i]["start"],
            end: this.items[i]["end"],
          };
          this.eventDataList.push(events);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
<style lang="scss" scope>
</style>