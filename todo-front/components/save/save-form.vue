<template>
  <validation-observer
    ref="observer"
    @submit.prevent="submit"
    v-slot="{ invalid }"
  >
  <h1>{{pageTitle}}</h1>
    <v-form ref="form" v-model="valid" lazy-validation>
      <template v-for="(value, index) in validateSchema">
        <validation-provider
          v-slot="{ errors }"
          :name="value.name"
          :rules="value.rule"
          :key="index"
        >
          <v-text-field
            v-model="value.val"
            :counter="value.counter"
            :error-messages="errors"
            :label="value.label"
            required
          ></v-text-field>
        </validation-provider>
      </template>
      <SchedulePicker @schedule="emitScheduleData" />
      <v-btn @click="clear"> clear </v-btn>
      <Dialog :invalid="invalid" :sendMessage="sendMessage" @submit="submit" />
    </v-form>
  </validation-observer>
</template>


<script>
import axios from "axios";

import Dialog from "~/components/save/ui/dialog.vue";
import SchedulePicker from "~/components/save/ui/schedule-picker.vue";

export default {
  components: {
    Dialog,
    SchedulePicker,
  },

  data() {
    return {
      pageTitle: "TODO保存画面",
      sendMessage: true,
      msg: "",
      valid: "",

      startDate: "",
      endDate: "",
      validateSchema: [
        {
          name: "title",
          rule: "required|max:10",
          label: "担当者名",
          counter: 10,
          val: "",
        },
        {
          name: "content",
          rule: "required|max:255",
          label: "TODO内容",
          counter: 255,
          var: "",
        },
      ],
    };
  },
  methods: {
    submit() {
      this.$refs.observer.validate();
      axios
        .post("/v1/todo/save", {
          title: this.validateSchema[0].val,
          content: this.validateSchema[1].val,
          userno: 1,
          // start_day: this.startDate,
          // end__day: this.endDate,
        })
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          this.sendMessage = false;
          console.log(err);
        });
      this.clear();
    },
    clear() {
      this.name = "";
      this.content = "";
      this.startDate = "";
      this.sendDate = "";
      this.$refs.observer.reset();
    },
    emitScheduleData(...sheduleData) {
      this.startDate = sheduleData[0];
      this.endDate = sheduleData[1];
    },
  },
};
</script>
<style lang="scss">
ul li {
  list-style-type: none;
}
</style>