import Vue from 'vue';
import {
  ValidationProvider,
  ValidationObserver,
  extend,
  localize
} from 'vee-validate';
import {
  required,
  max,
  regex,
  before
} from 'vee-validate/dist/rules';
import ja from 'vee-validate/dist/locale/ja.json';

// 必要なルールのみインポート
extend('required', {
  ...required,
  message: '{_field_} can not be empty',
})

extend('max', {
  ...max,
  message: '{_field_} may not be greater than {length} characters',
})

extend('regex', {
  ...regex,
  message: '{_field_} {_value_} does not match {regex}',
})

// メッセージを設定
localize('ja', ja);

Vue.component('ValidationProvider', ValidationProvider);
Vue.component('ValidationObserver', ValidationObserver);
