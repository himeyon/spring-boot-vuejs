<template>
  <b-row>
    <b-col cols="12" lg="6">
      <b-card no-header>
        <template slot="header">
          User id:  {{ $route.params.id }}
        </template>
        <b-table striped small fixed responsive="sm" :items="items" :fields="fields">
          <template slot="value" slot-scope="data">
            <strong>{{data.item.value}}</strong>
          </template>
        </b-table>
        <template slot="footer">
          <b-button @click="goBack">Back</b-button>
        </template>
      </b-card>
    </b-col>
  </b-row>
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
      items: [],
      fields: [
        {key: 'key'},
        {key: 'value'},
      ],
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    getUser() {
	    AXIOS.get(`/user/` + this.$route.params.id)
	    .then(response => {
	    	const user = response.data
			  const userDetails = user ? Object.entries(user) : [['id', 'Not found']]
	      this.items = userDetails.map(([key, value]) => {return {key: key, value: value}})
	    })
	    .catch(e => {
	      this.errors.push(e)
	    })
    }
  },
  created () {
	  this.getUser()
  }
}
</script>
