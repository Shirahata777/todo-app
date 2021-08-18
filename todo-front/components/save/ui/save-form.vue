<template>
  <validation-observer ref="observer" v-slot="{ invalid }">
    <form @submit.prevent="submit">
      <validation-provider
        v-slot="{ errors }"
        name="Name"
        rules="required|max:10"
      >
        <v-text-field
          v-model="name"
          :counter="10"
          :error-messages="errors"
          label="Name"
          required
        ></v-text-field>
      </validation-provider>
      <validation-provider
        v-slot="{ errors }"
        name="Content"
        rules="required|max:100"
      >
        <v-text-field
          v-model="content"
          :counter="10"
          :error-messages="errors"
          label="Content"
          required
        ></v-text-field>
      </validation-provider>

      <Dialog :invalid="invalid" />
      <SchedulePicker @schedule="emitScheduleData" />
      <p>{{ startDate }}</p>
      <p>{{ endDate }}</p>

      <v-btn @click="clear"> clear </v-btn>
    </form>
  </validation-observer>
</template>

<script>
import axios from "axios";
import { required, digits, email, max, regex } from "vee-validate/dist/rules";
import {
  extend,
  ValidationObserver,
  ValidationProvider,
  setInteractionMode,
} from "vee-validate";

import Dialog from "~/components/save/ui/dialog.vue";
import SchedulePicker from "~/components/save/ui/schedule-picker.vue";

setInteractionMode("eager");

extend("digits", {
  ...digits,
  message: "{_field_} needs to be {length} digits. ({_value_})",
});

extend("required", {
  ...required,
  message: "{_field_} can not be empty",
});

extend("max", {
  ...max,
  message: "{_field_} may not be greater than {length} characters",
});

extend("regex", {
  ...regex,
  message: "{_field_} {_value_} does not match {regex}",
});
export default {
  components: {
    ValidationProvider,
    ValidationObserver,
    Dialog,
    SchedulePicker,
  },

  data() {
    return {
      name: "",
      content: "",
      msg: "",

      startDate: "",
      endDate: "",
    };
  },
  methods: {
    submit() {
      this.$refs.observer.validate();
      axios
        .post("/v1/api/todo/save", {
          name: this.name,
          content: this.content,
        })
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
      this.clear();
    },
    clear() {
      this.name = "";
      this.content = "";
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
</style>