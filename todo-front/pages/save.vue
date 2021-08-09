
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

      <v-dialog
      v-model="dialog"
      width="500"
    >
      <template v-slot:activator="{ on, attrs }">
          <v-btn class="mr-4" type="submit" v-bind="attrs" v-on="on" :disabled="invalid"> submit </v-btn>
      </template>
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          保存完了しました！
        </v-card-title>

        <v-card-text>
          閉じるボタンを押してダイアログを閉じてください！
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="dialog = false"
          >
           　閉じる
          </v-btn>
        </v-card-actions>
        </v-card>
    </v-dialog>

      <v-btn @click="clear"> clear </v-btn>
    </form>
  </validation-observer>
</template>
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
  },

  data() {
    return {
      name: "",
      content: "",
      msg: "",
      dialog: false,
    };
  },
  methods: {
    submit() {
      this.$refs.observer.validate();
      axios
        .post("/api/save", {
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
  },
};
</script>
<style lang="scss">
</style>