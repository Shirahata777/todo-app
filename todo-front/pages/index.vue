<template>
  <div>
    <v-container>
      <v-row no-gutters>
        <v-col cols="12" sm="12" md="12">
          <v-card v-for="(item, index) in items" :key="index">
            <div
              class="d-flex flex-no-wrap justify-space-between"
            >
              <nuxt-link :to="'/inspire/' + item[0]['todoNo']">
                <div>
                  <v-card-title class="text-h5" v-text="item[0]['title']">
                  </v-card-title>
                  <v-card-subtitle
                    v-text="item[0]['content']"
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
 async asyncData() {
   const {data} = await axios
      .get("/v1/todo/")
      .catch((err) => {
        console.log(err);
      });

      return {
        items : data
      };
  },
};
</script>
<style lang="scss">

</style>