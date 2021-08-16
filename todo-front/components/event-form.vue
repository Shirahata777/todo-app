<template>
  <v-container>
    <div class="text-left display-1">
      <h1 class="contact_header">イベント追加</h1>
    </div>

    <v-form
      ref="form"
      v-model="valid"
      class=""
      action=""
      method="post"
      @submit="validate"
      lazy-validation
    >
      <v-text-field
        v-model="eventTitle"
        :counter="10"
        :rules="eventTitleRules"
        label="イベントタイトル"
        name="イベントタイトル"
        required
      ></v-text-field>
      <v-text-field
        v-model="start"
        label="開始日"
        name="開始日"
        required
      ></v-text-field>
      <v-text-field
        v-model="end"
        label="終了日"
        name="終了日"
        required
      ></v-text-field>
      <v-text-field
        v-model="contents"
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
  font-weight: 700;
  text-shadow: #fff 2px 0, #fff -2px 0, #fff 0 -2px, #fff 0 2px, #fff 2px 2px,
    #fff -2px 2px, #fff 2px -2px, #fff -2px -2px, #fff 1px 2px, #fff -1px 2px,
    #fff 1px -2px, #fff -1px -2px, #fff 2px 1px, #fff -2px 1px, #fff 2px -1px,
    #fff -2px -1px, rgba(0, 0, 0, 0.5) 3px 3px 3px;
}
</style>

<script>
export default {
  data: () => ({
    valid: true,
    eventTitle: "",
    eventTitleRules: [
      (v) => !!v || "event title is required",
      (v) => (v && v.length <= 20) || "event title must be less than 20 characters",
    ],
    start: "",
    end: "",
    contents: "",
  }),

  methods: {
    validate() {
      var result = confirm(
        "イベントを登録しますか？"
      );
      if (result) {
        submit(this.$refs.form.validate());
      } else {
        alert("イベントを登録できませんでした...");
      }
    },
    submit(validate) {
      if (!validate) {
        this.isError = true;
        event.preventDefault();
        alert("イベントを送信できませんでした...");
      }
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>
