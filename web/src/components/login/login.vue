<template>
  <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
    <FormItem prop="user">
      <Input type="text" v-model="formInline.user" placeholder="Username">
      <Icon type="ios-person-outline" slot="prepend"></Icon>
      </Input>
    </FormItem>
    <p></p>
    <FormItem prop="password">
      <Input type="password" v-model="formInline.password" placeholder="Password">
      <Icon type="ios-lock-outline" slot="prepend"></Icon>
      </Input>
    </FormItem>
    <p></p>
    <FormItem>
      <Button type="primary" @click="handleSubmit('formInline')">Signin</Button>
    </FormItem>
    <FormItem>
      <Button type="Default" @click="menuList()">请求菜单</Button>
    </FormItem>
  </Form>
</template>
<script>
  export default {
    data() {
      return {
        formInline: {
          user: '',
          password: ''
        },
        ruleInline: {
          user: [
            {required: true, message: 'Please fill in the user name', trigger: 'blur'}
          ],
          password: [
            {required: true, message: 'Please fill in the password.', trigger: 'blur'},
            {type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            this.$http({
              url: '/login',
              method: 'post',
              data: {
                'username': this.formInline.user,
                'password': this.formInline.password
              }
            }).then(res=>{
              sessionStorage.setItem('Authorization', res.headers.authorization)
            }).catch(reason => {

            })
            this.$Message.success('Success!');
          } else {
            this.$Message.error('Fail!');
          }
        })
      },
      menuList(){
        this.$http({
          url: '/usercenter/api/menu/list',
          method: 'post',
          data: {
            'level': 0,
            'menuname': ''
          }
        }).then(res=>{
          console.info(res)
        })
      }
    }
  }
</script>
