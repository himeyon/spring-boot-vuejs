<template>
  <div class="animated fadeIn">
    <b-row>
      <b-col md="12">
        <b-alert :show="dismissCountDown"
                dismissible
                fade
                variant="info"
                @dismissed="dismissCountDown=0"
                @dismiss-count-down="countDownChanged">
        Entry succeed.
        </b-alert>
        <b-card>
          <div slot="header">
            <strong>User Form</strong>
          </div>
          <b-form>
          <b-form-group
            label="ID"
            label-for="ID"
            :label-cols="3"
            :horizontal="true">
            <b-form-input plaintext id="basicStatic" type="text" v-model="item.id"></b-form-input>
          </b-form-group>
          <b-form-group
            description="Let us know your full name."
            label="Your name"
            label-for="basicName"
            :label-cols="3"
            :horizontal="true">
            <b-form-input id="basicName" type="text" autocomplete="name" v-model="item.name"></b-form-input>
          </b-form-group>
          <b-form-group
            description="This is a help text"
            label="Text Input"
            label-for="basicText"
            :label-cols="3"
            :horizontal="true">
            <b-form-input id="basicText" type="text" placeholder="Notes" v-model="item.notes"></b-form-input>
          </b-form-group>
          <b-form-group
            label="Select"
            label-for="basicSelect"
            :label-cols="3"
            :horizontal="true">
            <b-form-select id="basicSelect" v-model="item.role"
              :plain="true"
              :options="['admin', 'normal']"
              value="Please select">
            </b-form-select>
          </b-form-group>
          <b-form-group
            label="Inline checkboxes - custom"
            label-for="basicInlineCustomCheckboxes"
            :label-cols="3"
            :horizontal="true">
            <b-form-checkbox-group id="basicInlineCustomCheckboxes">
              <div class="custom-control custom-checkbox custom-control-inline">
                <input type="checkbox" class="custom-control-input" id="customInChk1" v-model="item.status" true-value="valid" false-value="invalid">
                <label class="custom-control-label" for="customInChk1">Status</label>
              </div>
            </b-form-checkbox-group>
          </b-form-group>
          <div slot="footer">
            <b-button type="button" variant="primary" @click="entryUser">Save changes</b-button>
            <b-button type="button" variant="light" @click="getUser">Cancel</b-button>
            <b-button type="button" variant="secondary" @click="goBack">Back</b-button>
          </div>
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { AXIOS } from '@/shared/http-common'
export default {
  name: 'User',
  props: {
    caption: {
      type: String,
      default: 'User id'
    },
  },
  data: () => {
    return {
      item: {},
      dismissSecs: 3,
      dismissCountDown: 0,
      showDismissibleAlert: false
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    getUser: async function () {
	    AXIOS.get(`/user/` + this.$route.params.id)
	    .then(response => {
	    	this.item = response.data
	    })
	    .catch(e => {
	      this.errors.push(e)
	    })
    },
    entryUser: async function() {
        let params = new FormData();
        params.append("id", this.item.id)
        params.append("name", this.item.name || '')
        params.append("role", this.item.role)
        params.append("status", this.item.status)
        params.append("notes", this.item.notes || '')
        await AXIOS.put(`/user/`, params)
        await this.getUser();
        await this.showAlert();
    },
    showAlert () {
      this.dismissCountDown = this.dismissSecs
    },
    countDownChanged (dismissCountDown) {
      this.dismissCountDown = dismissCountDown
    },
  },
  created: async function () {
	  await this.getUser()
  }
}
</script>
