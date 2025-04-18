<template>
  <a-card :bordered="false" class="card-area">
    <div style="width: 100%">
      <a-col :span="22" v-if="newsList.length > 0">
        <a-alert
          banner
          :message="newsContent"
          type="info"
        />
      </a-col>
      <a-col :span="2">
        <a-button type="primary" style="margin-top: 2px;margin-left: 10px" @click="newsNext">下一页</a-button>
      </a-col>
    </div>
    <br/>
    <br/>
    <a-row :gutter="30" v-if="userInfo != null">
      <a-col :span="6">
        <a-card :bordered="false">
          <span slot="title">
            <a-icon type="user" style="margin-right: 10px" />
            <span>用户信息</span>
          </span>
          <div>
            <a-avatar :src="'http://127.0.0.1:9527/imagesWeb/' + userInfo.images" shape="square" style="width: 100px;height: 100px;float: left;margin: 10px 0 10px 10px" />
            <div style="float: left;margin-left: 20px;margin-top: 8px">
              <span style="font-size: 20px;font-family: SimHei">{{ userInfo.name }}</span>
              <span style="font-size: 14px;font-family: SimHei">{{ userInfo.code }}</span>
            </div>
            <div style="float: left;margin-left: 20px;margin-top: 8px">
              <span style="font-size: 14px;font-family: SimHei">电话：{{ userInfo.phone == null ? '- -' : userInfo.phone }}</span>
            </div>
            <div style="float: left;margin-left: 20px;margin-top: 8px">
              <span style="font-size: 14px;font-family: SimHei">邮箱：{{ userInfo.email == null ? '- -' : userInfo.email }}</span>
            </div>
          </div>
        </a-card>
      </a-col>
    </a-row>
    <div style="background:#ECECEC; padding:30px;margin-top: 30px;margin-bottom: 30px">
      <a-row :gutter="30">
        <a-col :span="6" v-for="(item, index) in statusList" :key="index">
          <div style="background: #e8e8e8">
            <a-carousel autoplay style="height: 150px;" v-if="item.images !== undefined && item.images !== ''">
              <div style="width: 100%;height: 150px" v-for="(item, index) in item.images.split(',')" :key="index">
                <img :src="'http://127.0.0.1:9527/imagesWeb/'+item" style="width: 100%;height: 150px">
              </div>
            </a-carousel>
            <a-card :bordered="false">
              <span slot="title">
                <span style="font-size: 14px;font-family: SimHei">
                  {{ item.spaceName }} | {{ item.spaceAddress }}
                  <span style="margin-left: 15px;color: orange" v-if="item.status == -1">预约中</span>
                  <span style="margin-left: 15px;color: green" v-if="item.status == 0">空闲</span>
                  <span style="margin-left: 15px;color: red" v-if="item.status == 1">停车中</span>
                  <a style="text-align: right;margin-left: 10px" v-if="item.status == 0" @click="showModal(item)"><a-icon type="paper-clip" />预约</a>
                </span>
              </span>
            </a-card>
          </div>
        </a-col>
      </a-row>
      <a-modal
        title="选择预定车辆"
        :visible="visible"
        @ok="reserveSpace"
        @cancel="handleCancel"
      >
        <a-form :form="form" layout="vertical">
          <a-row :gutter="20">
            <a-col :span="24">
              <p style="text-align: center;font-size: 16px;font-weight: 600">人脸认证</p>
            </a-col>
            <a-col :span="24">
              <div style="height: 350px">
                <div class="camera_outer">
                  <video id="videoCamera" :width="videoWidth" :height="videoHeight" autoplay></video>
                  <canvas style="display:none;" id="canvasCamera" :width="videoWidth" :height="videoHeight" ></canvas>
                  <div v-if="imgSrc" class="img_bg_camera">
                    <img :src="imgSrc" alt="" class="tx_img">
                  </div>
                  <div style="margin-top: 10px">
                    <a-button
                      size="small"
                      type="primary"
                      @click.stop.prevent="getCompetence">打开摄像头
                    </a-button>
                    <a-button
                      size="small"
                      type="primary"
                      @click.stop.prevent="stopNavigator">关闭摄像头
                    </a-button>
                    <a-button
                      size="small"
                      type="primary"
                      @click.stop.prevent="setImage">识别
                    </a-button>
                  </div>
                </div>
              </div>
            </a-col>
            <a-col :span="24" v-if="faceVerification">
              <a-form-item label='车辆信息' v-bind="formItemLayout">
                <a-radio-group button-style="solid" v-decorator="[
                    'vehicleId',
                    {rules: [{ required: true, message: '请选择车辆' }]}
                  ]">
                  <a-radio-button :value="item.id" v-for="(item, index) in vehicleList" :key="index">
                    {{ item.vehicleNumber }}
                  </a-radio-button>
                </a-radio-group>
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </a-modal>
    </div>
  </a-card>
</template>

<script>
import {mapState} from 'vuex'

const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'Work',
  data () {
    return {
      form: this.$form.createForm(this),
      formItemLayout,
      faceCheck: false,
      visible: false,
      statusList: [],
      vehicleList: [],
      loading: false,
      userInfo: null,
      memberInfo: null,
      spaceInfo: null,
      newsList: [],
      faceView: {
        visiable: false
      },
      videoWidth: 470,
      videoHeight: 300,
      imgSrc: '',
      thisCancas: null,
      thisContext: null,
      thisVideo: null,
      faceVerification: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    })
  },
  mounted () {
    this.getWorkStatusList()
    this.selectMemberByUserId()
    this.selectVehicleByUserId()
  },
  methods: {
    getCompetence () {
      this.userInfo == undefined
      var _this = this
      this.thisCancas = document.getElementById('canvasCamera')
      this.thisContext = this.thisCancas.getContext('2d')
      this.thisVideo = document.getElementById('videoCamera')
      // 旧版本浏览器可能根本不支持mediaDevices，我们首先设置一个空对象
      if (navigator.mediaDevices === undefined) {
        navigator.mediaDevices = {}
      }
      // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
      // 使用getUserMedia，因为它会覆盖现有的属性。
      // 这里，如果缺少getUserMedia属性，就添加它。
      if (navigator.mediaDevices.getUserMedia === undefined) {
        navigator.mediaDevices.getUserMedia = function (constraints) {
          // 首先获取现存的getUserMedia(如果存在)
          var getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia
          // 有些浏览器不支持，会返回错误信息
          // 保持接口一致
          if (!getUserMedia) {
            return Promise.reject(new Error('getUserMedia is not implemented in this browser'))
          }
          // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
          return new Promise(function (resolve, reject) {
            getUserMedia.call(navigator, constraints, resolve, reject)
          })
        }
      }
      var constraints = { audio: false, video: { width: this.videoWidth, height: this.videoHeight, transform: 'scaleX(-1)' } }
      navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
        // 旧的浏览器可能没有srcObject
        if ('srcObject' in _this.thisVideo) {
          _this.thisVideo.srcObject = stream
        } else {
          // 避免在新的浏览器中使用它，因为它正在被弃用。
          _this.thisVideo.src = window.URL.createObjectURL(stream)
        }
        _this.thisVideo.onloadedmetadata = function (e) {
          _this.thisVideo.play()
        }
      }).catch(err => {
        console.log(err)
      })
    },
    setImage () {
      var _this = this
      // 点击，canvas画图
      _this.thisContext.drawImage(_this.thisVideo, 0, 0, _this.videoWidth, _this.videoHeight)
      // 获取图片base64链接
      var image = this.thisCancas.toDataURL('image/png')
      let data = { file: image.replace(/^data:image\/\w+;base64,/, ''), name: this.name }
      this.$post('/cos/face/verification', data).then((r) => {
        if (r.data.msg !== '成功') {
          this.$message.error(r.data.msg)
        } else {
          this.$message.success('验证通过')
          setTimeout(() => {
            this.faceVerification = true
            this.faceView.visiable = false
            this.thisVideo.srcObject.getTracks()[0].stop()
          })
        }
      })
      // _this.imgSrc = image
      // this.$emit('refreshDataList', this.imgSrc)
    },
    dataURLtoFile (dataurl, filename) {
      var arr = dataurl.split(',')
      var mime = arr[0].match(/:(.*?);/)[1]
      var bstr = atob(arr[1])
      var n = bstr.length
      var u8arr = new Uint8Array(n)
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
      }
      return new File([u8arr], filename, { type: mime })
    },
    stopNavigator () {
      this.thisVideo.srcObject.getTracks()[0].stop()
    },
    faceViewOpen () {
      if (this.name !== '') {
        this.faceView.visiable = true
      } else {
        this.$message.warning('请先输入姓名')
      }
    },
    newsNext () {
      if (this.newsPage + 1 === this.newsList.length) {
        this.newsPage = 0
      } else {
        this.newsPage += 1
      }
      this.newsContent = `《${this.newsList[this.newsPage].title}》 ${this.newsList[this.newsPage].content}`
    },
    showModal (row) {
      this.spaceInfo = row
      this.visible = true
    },
    handleCancel (e) {
      console.log('Clicked cancel button')
      this.faceVerification = false
      this.thisVideo.srcObject.getTracks()[0].stop()
      this.visible = false
    },
    selectVehicleByUserId () {
      this.$get(`/cos/vehicle-info/user/${this.currentUser.userId}`).then((r) => {
        this.vehicleList = r.data.data
      })
    },
    reserveSpace () {
      this.form.validateFields((err, values) => {
        if (!err) {
          values.spaceId = this.spaceInfo.id
          this.$post('/cos/reserve-info', {
            ...values
          }).then((r) => {
            this.$message.success('预约成功！预约时间为30分钟')
            this.visible = false
            this.getWorkStatusList()
          })
        }
      })
    },
    selectMemberByUserId () {
      this.$get(`/cos/member-info/member/${this.currentUser.userId}`).then((r) => {
        this.userInfo = r.data.user
        this.memberInfo = r.data.member
        this.newsList = r.data.bulletin
        if (this.newsList.length !== 0) {
          this.newsContent = `《${this.newsList[0].title}》 ${this.newsList[0].content}`
        }
      })
    },
    getWorkStatusList () {
      this.$get(`/cos/space-status-info/status/list`).then((r) => {
        this.statusList = r.data.data
      })
    }
  }
}
</script>

<style scoped>
>>> .ant-card-meta-title {
  font-size: 13px;
  font-family: SimHei;
}
>>> .ant-card-meta-description {
  font-size: 12px;
  font-family: SimHei;
}
>>> .ant-divider-with-text-left {
  margin: 0;
}

>>> .ant-card-head-title {
  font-size: 13px;
  font-family: SimHei;
}
>>> .ant-card-extra {
  font-size: 13px;
  font-family: SimHei;
}
.ant-carousel >>> .slick-slide {
  text-align: center;
  height: 250px;
  line-height: 250px;
  overflow: hidden;
}

</style>
