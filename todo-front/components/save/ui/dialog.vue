<template>
  <v-dialog v-model="dialog" width="500">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="mr-4"
        type="submit"
        v-bind="attrs"
        v-on="on"
        :disabled="invalid"
      >
        submit
      </v-btn>
    </template>
    <v-card>
      <v-card-title class="text-h5 grey lighten-2">
        <template v-if="this.sendMessage">{{ message }}</template>
        <template v-else>{{ errMessage }}</template>
      </v-card-title>

      <v-card-text>
        閉じるボタンを押してダイアログを閉じてください！
      </v-card-text>

      <v-divider></v-divider>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" text @click="dialog = false"> 　閉じる </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: ["invalid", "sendMessage"],
  data() {
    return {
      dialog: false,
      errMessege: "保存することができませんでした...",
      message: "保存完了しました！",
    };
  },
};
</script>

<style lang="scss" scoped>
</style>