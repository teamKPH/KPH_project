<template>
  <v-container style="width:450px;">
    <v-layout align-center row wrap>
      <v-flex xs12>
        <v-alert v-if="isError" type="error">
          {{ errorMsg }}
        </v-alert>
        <v-card>
          <v-toolbar flat>
            <v-toolbar-title>회원가입</v-toolbar-title>
          </v-toolbar>
          <div class="pa-5">
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                v-model="formData.email"
                :rules="emailRules"
                label="Enter E-mail"
                required
              ></v-text-field>

              <v-text-field
                v-model="formData.nickname"
                :counter="10"
                :rules="nameRules"
                label="Name"
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

              <v-text-field
                v-model="chkPassword"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Enter Password Again"
                hint="At least 8 characters"
                counter
                @click:append="show = !show"
              ></v-text-field>

              <h6 v-if="sameChk(chkPassword)" class="mb-5 teal--text accent-3">
                Please create the two passwords identical.
              </h6>
              <h6 v-else class="mb-5 red--text lighten-2">
                Please create the two passwords identical.
              </h6>

              <div class="mt-3 d-flex flex-row-reverse">
                <v-btn color="error" class="mr-4" @click="reset"> 리셋 </v-btn>

                <v-btn
                  :disabled="!valid"
                  color="blue"
                  class="mr-4"
                  @click="login(formData)"
                >
                  회원가입
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
import RegisterObj from "../models/resisterObj"
import axios from "axios"
export default {
  data: () => ({
    formData: new RegisterObj("", "", ""),
    valid: false,
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 10) || "Name must be less than 10 characters"
    ],
    isError: false,
    errorMsg: "",
    email: "",
    emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+\..+/.test(v) || "E-mail must be valid"
    ],
    show: false,
    chkPassword: "",
    rules: {
      required: value => !!value || "Required.",
      min: v => v.length >= 8 || "Min 8 characters"
    }
  }),
  methods: {
    sameChk(password) {
      if (this.formData.password == password) return true
      else {
        this.valid = false
        return false
      }
    },
    login(LoginObj) {
      if (!this.formData.studentId || !this.formData.password) {
        this.isError = true
        this.errorMsg = "이메일과 비밀번호를 입력해주세요."
        return
      }
      axios
        .get("/sign-up", LoginObj)
        .then(res => {
          console.log(res)
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
