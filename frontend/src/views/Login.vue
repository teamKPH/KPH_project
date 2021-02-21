<template>
  <v-container style="width:450px;">
    <v-layout align-center row wrap>
      <v-flex xs12>
        <v-alert v-if="isError" type="error">
          {{ errorMsg }}
        </v-alert>
        <v-card>
          <v-toolbar flat>
            <v-toolbar-title>로그인</v-toolbar-title>
          </v-toolbar>
          <div class="pa-5">
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                v-model="formData.studentId"
                label="Enter E-mail"
                required
              ></v-text-field>

              <v-text-field
                v-model="formData.password"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Enter Password"
                hint="At least 8 characters"
                counter
                @click:append="show = !show"
              ></v-text-field>

              <div class="mt-3 d-flex flex-row-reverse">
                <v-btn color="error" class="mr-4" @click="reset"> 리셋 </v-btn>

                <v-btn color="primary" class="mr-4"> 회원가입 </v-btn>

                <v-btn
                  :disabled="!valid"
                  color="success"
                  class="mr-4"
                  @click="login(formData)"
                >
                  로그인
                </v-btn>
              </div>
            </v-form>
          </div>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import LoginObj from "../models/loginObj"
import axios from "axios"
export default {
  data: () => ({
    formData: new LoginObj("", ""),
    valid: false,
    isError: false,
    errorMsg: "",
    studentId: "",
    studentIdRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+\..+/.test(v) || "E-mail must be valid"
    ],
    show: false,
    password: "",
    rules: {
      required: value => !!value || "Required.",
      min: v => v.length >= 8 || "Min 8 characters"
    }
  }),
  methods: {
    login(LoginObj) {
      if (!this.formData.studentId || !this.formData.password) {
        this.isError = true
        this.errorMsg = "이메일과 비밀번호를 입력해주세요."
        return
      }
      axios
        .post("/sign-in", LoginObj)
        .then(res => {
          let token = res.data.data
          localStorage.setItem("access_token", token)
          this.$store.dispatch("getAccountInfo")
          this.$router.push({ name: "Home" })
        })
        .catch(err => {
          if (err.response) {
            this.isError = true
            this.errorMsg = err.response.data.message
          }
        })
    },
    validate() {
      this.$refs.form.validate()
    },
    reset() {
      this.$refs.form.reset()
    },
    resetValidation() {
      this.$refs.form.resetValidation()
    }
  }
}
</script>
