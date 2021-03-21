<template>
  <v-card class="mx-auto" width="500">
    <v-toolbar color="primary" dark>
      <v-icon class="mr-3">mdi-forum-outline</v-icon>

      <v-toolbar-title>채팅방 목록</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon @click="searchActive = !searchActive">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon link router :to="{ name: 'chat-make' }">
        <v-icon>mdi-chat-plus-outline</v-icon>
      </v-btn>
    </v-toolbar>
    <v-row v-if="searchActive">
      <v-col cols="6" sm="4" md="6" style="margin-left:80px;"
        ><v-text-field v-if="true" label="채팅방" clearable></v-text-field
      ></v-col>
      <v-col cols="3" md="4">
        <div class="my-3">
          <v-btn color="warning" dark>
            찾기
          </v-btn>
        </div>
      </v-col>
    </v-row>

    <v-list two-line>
      <v-list-item-group>
        <template v-for="(item, index) in items">
          <v-list-item :key="item.title">
            <template v-slot>
              <v-list-item-content>
                <v-list-item-title v-text="item.title"></v-list-item-title>

                <v-list-item-subtitle
                  class="text--primary"
                  v-text="item.headline"
                ></v-list-item-subtitle>
              </v-list-item-content>

              <v-list-item-action>
                <v-list-item-action-text
                  v-text="item.action"
                ></v-list-item-action-text>
              </v-list-item-action>
            </template>
          </v-list-item>

          <v-divider v-if="index < items.length - 1" :key="index"></v-divider>
        </template>
      </v-list-item-group>
    </v-list>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    searchActive: false,
    selected: [2],
    items: [
      {
        action: "15 min",
        headline: "Brunch this weekend?",
        subtitle: `I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
        title: "Ali Connors"
      },
      {
        action: "2 hr",
        headline: "Summer BBQ",
        subtitle: `Wish I could come, but I'm out of town this weekend.`,
        title: "me, Scrott, Jennifer"
      },
      {
        action: "6 hr",
        headline: "Oui oui",
        subtitle: "Do you have Paris recommendations? Have you ever been?",
        title: "Sandra Adams"
      },
      {
        action: "12 hr",
        headline: "Birthday gift",
        subtitle:
          "Have any ideas about what we should get Heidi for her birthday?",
        title: "Trevor Hansen"
      },
      {
        action: "18hr",
        headline: "Recipe to try",
        subtitle:
          "We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
        title: "Britta Holt"
      }
    ]
  })
}
</script>
