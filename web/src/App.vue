<template>
  <div class="layout" id="app">
    <login></login>
  </div>
</template>
<script>
  import login from '@/components/login/login'

  export default {
    name: 'app',
    components: {
      'login': login
    }
  }
</script>
