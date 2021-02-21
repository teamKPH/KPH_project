<template>
  <div id="app">
    <v-app id="inspire">
      <v-app id="inspire">
        <v-navigation-drawer v-model="drawer" app clipped>
          <v-list dense>
            <v-list-item v-if="!isLogin" link router :to="{ name: 'login' }">
              <v-list-item-action>
                <v-icon>mdi-account</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Login</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item
              v-else
              @click="logout"
              link
              router
              :to="{ name: 'Home' }"
            >
              <v-list-item-action>
                <v-icon>mdi-account-off-outline</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Logout</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item link>
              <v-list-item-action>
                <v-icon>mdi-cog</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Settings</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>

        <v-app-bar app clipped-left>
          <v-app-bar-nav-icon
            @click.stop="drawer = !drawer"
          ></v-app-bar-nav-icon>
          <v-toolbar-title>Welcome</v-toolbar-title>
        </v-app-bar>

        <v-main>
          <v-container class="fill-height" fluid>
            <v-row align="center" justify="center">
              <router-view />
            </v-row>
          </v-container>
        </v-main>

        <v-footer app>
          <span>&copy; {{ new Date().getFullYear() }}</span>
          <span class="ml-1">Copyright By TeamKPH</span>
        </v-footer>
      </v-app>
    </v-app>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex"
export default {
  name: "App",

  data: () => ({
    drawer: null
  }),
  computed: {
    ...mapState(["isLogin", "userInfo"])
  },
  created() {
    this.$vuetify.theme.dark = true
  },
  methods: {
    ...mapMutations(["logout"])
  }
}
</script>
