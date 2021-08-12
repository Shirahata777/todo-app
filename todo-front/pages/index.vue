<template>
  <div>
    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <v-col cols="12" sm="12" md="12">
          <v-card v-for="(item, index) in items" :key="index">
            <div
              class="d-flex flex-no-wrap justify-space-between"
            >
              <nuxt-link :to="'/inspire/' + item.todono">
                <div>
                  <v-card-title class="text-h5" v-text="item.name">
                  </v-card-title>
                  <v-card-subtitle
                    v-text="item.content"
                  ></v-card-subtitle>
                </div>
              </nuxt-link>
            </div>
          </v-card>
        </v-col>
        <v-spacer></v-spacer>
      </v-row>
    </v-container>
  </div>
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
      .get("/v1/api/todo/")
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
<style lang="scss">

</style>