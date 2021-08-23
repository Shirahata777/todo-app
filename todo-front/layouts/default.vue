<template>
  <v-app :style="$store.state.isDarken? colorLight : colorDark">
    <v-navigation-drawer
      v-model="drawer"
      :mini-variant="miniVariant"
      :clipped="clipped"
      fixed
      app
    >
      <v-list>
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          :to="item.to"
          router
          exact
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title v-text="item.title" />
          </v-list-item-content>
        </v-list-item>
      </v-list>
       <v-switch v-model="theme" :prepend-icon="themeIcon"></v-switch>
    </v-navigation-drawer>
    <v-app-bar :clipped-left="clipped" fixed app>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />

      <v-toolbar-title v-text="title" />
      <v-spacer />
    </v-app-bar>
    <v-main>
      <v-container>
        <Nuxt />
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  conponent: {},
  data() {
    return {
      theme: this.$store.state.theme,
      clipped: false,
      drawer: false,
      fixed: false,
      items: [
        {
          icon: "mdi-apps",
          title: "TODO一覧",
          to: "/",
        },
        {
          icon: "mdi-chart-bubble",
          title: "TODO作成",
          to: "/save",
        },
        {
          icon: "mdi-calendar",
          title: "カレンダー",
          to: "/calendar",
        },
      ],
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: "Vuetify.js",
      colorLight: {
        color: "#fff",
        background: "#222",
      },
      colorDark: {
        color: "#222",
         background: "#fff",
      }
    };
  },
  computed: {
    themeIcon() {
      return this.$store.state.theme
        ? "mdi-weather-night"
        : "mdi-weather-sunny";
    },
  },
  watch: {
    theme() {
      this.$store.dispatch("theme", this.theme);
      this.$vuetify.theme.dark = this.theme;
    },
  },
};
</script>
