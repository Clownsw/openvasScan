<template>
  <div>
    <div class="userLoginBox">
      <h1 class="userLoginBoxTitle">注册 - 国开解析平台</h1>
      <div class="userLoginBottom">
        <el-form :model="user" :rules="rules" ref="userLoginForm" label-width="100px" class="userLoginForm">
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="user.userName"></el-input>
          </el-form-item>

          <el-form-item label="密码" prop="passWord">
            <el-input v-model="user.passWord" type="password"></el-input>
          </el-form-item>

          <el-form-item class="userLoginFormCodeCaptcha" label="验证码" prop="code">
            <el-input v-model="user.code" @keyup.enter.native="submitForm('userLoginForm')"></el-input>
            <el-image :src="image"></el-image>
          </el-form-item>

          <el-form-item class="userLoginFormFooter">
            <el-button type="primary" @click="submitForm('userLoginForm')">注册</el-button>
            <el-button @click="resetForm">重置</el-button>
            <el-button type="info" @click="toLoginPage">去登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import userApi from "@/api/user";
import common from "@/config/common";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        userName: '',
        passWord: '',
        code: ''
      },
      rules: {
        userName: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 4, max: 10, message: '用户名的长度在 4 到 10 个字符', trigger: 'blur'}
        ],
        passWord: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ],
        code: [
          {required: true, message: '请输入验证码', trigger: 'blur'},
          {min: 4, max: 4, message: '请输入正确的验证码', trigger: 'blur'}
        ],
      },
      image: '',
      imageId: '',
      timer: null
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.user.imageId = this.imageId
          userApi.register(this.user).then(resp => {
            resp = resp.data
            if (resp.code === 1000) {
              this.$message.success('注册成功!')
              this.$router.push('/login')
            }
          })
        } else {
          return false;
        }
      });
    },
    resetForm() {
      this.user = {}
    },
    resetTimer() {
      if (this.timer !== null) {
        clearInterval(this.timer)
        this.timer = null
      }
    },
    createCodeCaptcha() {
      userApi.captcha().then(resp => {
        this.resetTimer()
        resp = resp.data.data
        this.image = resp.image
        this.imageId = resp.imageId

        this.timer = setInterval(() => {
          let r = async () => {
            if (this.imageId === '') {
              this.createCodeCaptcha()
            } else {
              let resp = await userApi.captchaTtl(this.imageId)
              if (resp.data.data === -2) {
                this.createCodeCaptcha()
              }
            }
          }

          r().then(() => {
          })
        }, 3000)
      })
    },
    toLoginPage() {
      this.$router.push('/login')
    }
  },
  mounted() {
    this.createCodeCaptcha()
  },
  created() {
    document.title = '注册' + common.WEB_TITLE
  },
  destroyed() {
    this.resetTimer()
  }
}
</script>

<style scoped lang="less">
.userLoginBox {
  margin-top: 100px;
  text-align: center;

  .userLoginBoxTitle {
    margin-left: 100px;
  }

  .userLoginBottom {
    display: flex;
    justify-content: center;

    .userLoginForm {
      width: 450px;

      .userLoginFormCodeCaptcha ::v-deep(.el-form-item__content) {
        display: flex;
      }

      .userLoginFormCodeCaptcha {

        .el-input {
          margin-right: 10px;
        }

        .el-image {
          width: 100px;
          height: 40px;
        }
      }

      .userLoginFormFooter {
        text-align: center;
      }
    }
  }
}


</style>