<template>
  <v-container>
    <div class="text-left display-1">
      <h1 class="contact_header">イベント追加</h1>
    </div>

    <v-form ref="form" v-model="valid" @submit.prevent="validate">
      <v-text-field
        background-color="#e1f5fe"
        v-model="addEventData.title"
        label="イベントタイトル"
        name="イベントタイトル"
        required
      ></v-text-field>
      <v-text-field
        background-color="#e1f5fe"
        v-model="addEventData.start"
        label="開始日"
        name="開始日"
        required
      ></v-text-field>
      <v-text-field
        background-color="#e1f5fe"
        v-model="addEventData.end"
        label="終了日"
        name="終了日"
        required
      ></v-text-field>
      <v-text-field
        background-color="#e1f5fe"
        v-model="addEventData.contents"
        label="コンテンツ"
        name="コンテンツ"
        required
      ></v-text-field>
      <v-btn color="error" class="mr-4" @click="reset">
        フォームリセット
      </v-btn>
      <v-btn color="success" type="submit"> 送信 </v-btn>
    </v-form>
  </v-container>
</template>

<style lang="scss">
.contact_header {
  font-size: 20px;
  font-family: lust-script, sans-serif;
  font-style: normal;
  color: #4fc3f7;
  font-weight: 700;
  text-shadow: #fff 2px 0, #fff -2px 0, #fff 0 -2px, #fff 0 2px, #fff 2px 2px,
    #fff -2px 2px, #fff 2px -2px, #fff -2px -2px, #fff 1px 2px, #fff -1px 2px,
    #fff 1px -2px, #fff -1px -2px, #fff 2px 1px, #fff -2px 1px, #fff 2px -1px,
    #fff -2px -1px, rgba(0, 0, 0, 0.5) 3px 3px 3px;
}
.v-label {
  color: black !important;
}
</style>

<script>
export default {
  data: () => ({
    valid: true,
    eventTitle: "",
    eventTitleRules: [
      (v) => !!v || "event title is required",
      (v) =>
        (v && v.length <= 20) || "event title must be less than 20 characters",
    ],
    addEventData: {
      title: "",
      start: "",
      end: "",
      contents: "",
    },
  }),

  methods: {
    validate() {
      this.sentAddEventData(this.addEventData);
    },
    reset() {
      this.$refs.form.reset();
    },
    sentAddEventData() {
      this.$emit("addEventData", this.addEventData);
    },
  },
};
</script>
