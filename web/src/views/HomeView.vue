<template>
  <div class="main">
    <div class="mainBox">
      <h1 class="mainBoxTitle">国开解析平台</h1>
      <el-tabs v-model="activeName" @tab-click="handlerTabsClick">

        <el-tab-pane label="自动刷题" name="createAutoCommitTask">
          <el-form :model="task" :rules="autoCommitTaskRules" ref="taskForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="课程ID" prop="guoKaiId">
              <el-input v-model="task.guoKaiId"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="taskSubmit('taskForm', 0)" :disabled="taskSubmitBtnDisable">创建任务
              </el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="自动阅读" name="createAutoViewTask">
          <el-form :model="task" ref="taskForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="课程" prop="course">
              <el-select v-model="task.guoKaiId" placeholder="请选择">
                <el-option
                    v-for="item in courseOption"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="taskSubmit('taskForm', 1)" :disabled="taskSubmitBtnDisable">创建任务
              </el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="查看任务" name="viewTask">
          <el-table
              class="viewTaskTable"
              :data="taskTable"
              border
              style="width: 100%">

            <el-table-column
                prop="id"
                align="center"
                label="任务ID"
                width="200px"
                :show-overflow-tooltip="true">
            </el-table-column>

            <el-table-column
                prop="taskType"
                align="center"
                label="任务类型"
                width="100px"
                :show-overflow-tooltip="true">
              <template slot-scope="slot">
                <el-tag v-if="slot.row.taskType === 0" type="success">自动刷题</el-tag>
                <el-tag v-else-if="slot.row.taskType === 1" type="success">自动阅读</el-tag>
              </template>
            </el-table-column>

            <el-table-column
                prop="guoKaiId"
                align="center"
                label="考试ID"
                width="130px"
                :show-overflow-tooltip="true">
            </el-table-column>

            <el-table-column
                prop="status"
                align="center"
                label="状态"
                width="100px"
                :show-overflow-tooltip="true">
              <template slot-scope="slot">
                <el-tag v-if="slot.row.status === 0" type="success">创建</el-tag>
                <el-tag v-else-if="slot.row.status === 1" type="info">执行</el-tag>
                <el-tag v-else-if="slot.row.status === 3" type="danger">错误</el-tag>
                <el-tag v-else type="danger">完成</el-tag>
              </template>
            </el-table-column>

            <el-table-column
                prop="gmtCreated"
                align="center"
                label="创建时间"
                :show-overflow-tooltip="true">
            </el-table-column>

            <el-table-column
                prop="gmtModified"
                align="center"
                label="修改时间"
                :show-overflow-tooltip="true">
            </el-table-column>

            <el-table-column
                prop="log"
                align="center"
                label="日志"
                :show-overflow-tooltip="true">
              <template slot-scope="slot">
                <el-button type="primary" size="mini" @click="logDialogClick(slot.row.id)">查看</el-button>
              </template>
            </el-table-column>
          </el-table>

          <el-pagination
              class="viewTaskTablePagination"
              @current-change="taskPaginationCurrentChangeHandler"
              :page-size="taskListPagination.pageSize"
              layout="prev, pager, next"
              :total="taskListPagination.total">
          </el-pagination>
        </el-tab-pane>

        <el-tab-pane label="用户设置" name="userManager">
          <p>功能暂未开发</p>
        </el-tab-pane>

        <el-tab-pane label="配置设置" name="configManager">
          <el-form :model="config" :rules="configRules" ref="configForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="config1" prop="config1">
              <el-input v-model="config.config1" placeholder="_ga"></el-input>
            </el-form-item>

            <el-form-item label="config2" prop="config2">
              <el-input v-model="config.config2" placeholder="HWWAFSESID"></el-input>
            </el-form-item>

            <el-form-item label="config3" prop="config3">
              <el-input v-model="config.config3" placeholder="HWWAFSESTIME"></el-input>
            </el-form-item>

            <el-form-item label="config4" prop="config4">
              <el-input v-model="config.config4" placeholder="_gat"></el-input>
            </el-form-item>

            <el-form-item label="config5" prop="config5">
              <el-input v-model="config.config5" placeholder="session"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="configSubmit('configForm')">保存配置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="系统设置" name="systemManager">
          <el-form :model="sysConfig" ref="taskForm" :rules="sysConfigRules" label-width="100px" class="demo-ruleForm">
            <el-form-item label="开启登录" prop="enableLogin">
              <el-radio-group v-model="sysConfig.enableLogin">
                <el-radio :label="true">是</el-radio>
                <el-radio :label="false">否</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="开启注册" prop="enableRegister">
              <el-radio-group v-model="sysConfig.enableRegister">
                <el-radio :label="true">是</el-radio>
                <el-radio :label="false">否</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="开启答题" prop="autoCommit">
              <el-radio-group v-model="sysConfig.autoCommit">
                <el-radio :label="true">是</el-radio>
                <el-radio :label="false">否</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="开启阅读" prop="autoView">
              <el-radio-group v-model="sysConfig.autoView">
                <el-radio :label="true">是</el-radio>
                <el-radio :label="false">否</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="updateSysConfig">更新
              </el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="系统升级" name="systemUpdate">
          <el-upload
              action="http://localhost:1111/sysUpgrade/upgrade"
              :limit="1"
              :headers="uploadHeaders"
              :show-file-list="false"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-tab-pane>
      </el-tabs>
    </div>

    <el-dialog
        class="logDialogTable"
        top="2%"
        title="日志列表"
        append-to-body
        :visible.sync="logDialogVisible"
        width="60%"
        :before-close="logDialogHandleClose">
      <el-table
          :data="logTableData"
          style="width: 100%">
        <el-table-column
            align="center"
            prop="log"
            label="日志"
            :show-overflow-tooltip="true"
        >
        </el-table-column>

        <el-table-column
            align="center"
            prop="gmtCreated"
            label="创建时间"
            width="180"
            :show-overflow-tooltip="true"
        >
        </el-table-column>
      </el-table>

      <el-pagination
          class="logDialogTablePagination"
          @current-change="logPaginationCurrentChangeHandler"
          :page-size="logListPagination.pageSize"
          layout="prev, pager, next"
          :total="logListPagination.total">
      </el-pagination>
    </el-dialog>
  </div>
</template>

<script>
import common from '../config/common';
import taskApi from "@/api/task";
import configApi from "@/api/config";
import logApi from "@/api/log";
import systemApi from "@/api/system";

export default {
  name: 'HomeView',
  data() {
    return {
      activeName: 'viewTask',
      taskTable: [],
      taskListPagination: {
        total: 0,
        currentPage: 1,
        pageSize: 10
      },
      config: {
        config1: '',
        config2: '',
        config3: '',
        config4: '',
        config5: ''
      },
      configRules: {
        config1: [
          {required: true, message: '请输入', trigger: 'blur'},
        ],
        config2: [
          {required: true, message: '请输入', trigger: 'blur'},
        ],
        config3: [
          {required: true, message: '请输入', trigger: 'blur'},
        ],
        config4: [
          {required: true, message: '请输入', trigger: 'blur'},
        ],
        config5: [
          {required: true, message: '请输入', trigger: 'blur'},
        ],
      },
      task: {
        taskType: 0,
        guoKaiId: null
      },
      autoCommitTaskRules: {
        guoKaiId: [
          {required: true, message: '请输入任务ID!', trigger: 'blur'},
        ]
      },
      taskSubmitBtnDisable: true,
      logDialogVisible: false,
      logCacheTaskId: 0,
      logTableData: [],
      logListPagination: {
        total: 0,
        currentPage: 1,
        pageSize: 10
      },
      courseOption: [],
      sysConfig: {
        enableLogin: false,
        enableRegister: false,
        autoCommit: true,
        autoView: false
      },
      sysConfigRules: {
        enableLogin: [
          {required: true, message: '请选择', trigger: 'blur'}
        ],
        enableRegister: [
          {required: true, message: '请选择', trigger: 'blur'}
        ],
        autoCommit: [
          {required: true, message: '请选择', trigger: 'blur'}
        ],
        autoView: [
          {required: true, message: '请选择', trigger: 'blur'}
        ]
      },
      uploadHeaders: {
        token: this.$store.getters.GET_TOKEN
      }
    }
  },
  methods: {
    getTaskList() {
      taskApi.taskList(this.taskListPagination.currentPage, this.taskListPagination.pageSize).then(resp => {
        resp = resp.data
        if (resp.code === 1000) {
          this.taskListPagination.currentPage = resp.data.currentPage
          this.taskListPagination.pageSize = resp.data.pageSize
          this.taskListPagination.total = resp.data.total
          this.taskTable = resp.data.taskList
        } else {
          this.$message.error('获取任务列表失败!')
        }
      })
    },
    taskPaginationCurrentChangeHandler(currentPage) {
      this.taskListPagination.currentPage = currentPage
      this.getTaskList()
    },
    getConfig() {
      configApi.getConfig().then(resp => {
        resp = resp.data
        if (resp.code === 1000) {
          if (resp.data !== null) {
            this.config = resp.data
          }
        } else {
          this.$message.error('获取配置失败!')
        }
      })
    },
    handlerTabsClick() {
      this.task = {
        taskType: 0,
        guoKaiId: null
      }

      this.courseOption = []

      this.taskSubmitBtnDisable = false

      if (this.activeName === 'createAutoViewTask') {
        this.getCourseOptions()
      }

      if (this.activeName === 'viewTask') {
        this.taskListPagination = {
          total: 0,
          currentPage: 1,
          pageSize: 10
        }
        this.getTaskList()
      }

      if (this.activeName === 'configManager') {
        this.getConfig()
      }

      if (this.activeName === 'systemManager') {
        this.getSysConfig()
      }
    },
    configSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          configApi.insertOrUpdateConfig(this.config).then(resp => {
            resp = resp.data
            if (resp.code === 1000) {
              this.$message.success('保存配置成功!')
              this.getConfig()
            } else {
              this.$message.error('保存配置失败!')
            }
          })
        } else {
          return false;
        }
      })
    },
    taskSubmit(formName, taskType) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (taskType === 1 && this.task.guoKaiId === null) {
            this.$message.error('请先选择课程!')
            return false;
          }

          this.taskSubmitBtnDisable = true
          this.task.taskType = taskType

          taskApi.createTask(this.task).then(resp => {
            this.taskSubmitBtnDisable = false
            resp = resp.data
            if (resp.code === 1000) {
              this.$message.success('创建任务成功!')
            }
          })
        } else {
          return false;
        }
      })
    },
    getCourseOptions() {
      taskApi.autoViewSupportList().then(resp => {
        resp = resp.data
        this.courseOption = resp.data
      })
    },
    logDialogClick(taskId) {
      this.logCacheTaskId = taskId;
      logApi.getLogByTaskId({
        currentPage: this.logListPagination.currentPage,
        pageSize: this.logListPagination.pageSize,
        taskId: taskId
      }).then(resp => {
        resp = resp.data
        if (resp.code === 1000) {
          this.logListPagination.currentPage = resp.data.currentPage
          this.logListPagination.pageSize = resp.data.pageSize
          this.logListPagination.total = resp.data.total
          this.logTableData = resp.data.logList
          this.logDialogVisible = true
        }
      })
    },
    logPaginationCurrentChangeHandler(currentPage) {
      this.logListPagination.currentPage = currentPage
      this.logDialogClick(this.logCacheTaskId)
    },
    logDialogHandleClose(done) {
      this.logCacheTaskId = 0
      this.logTableData = []
      this.logListPagination = {
        total: 0,
        currentPage: 1,
        pageSize: 10
      }
      done()
    },
    getSysConfig() {
      systemApi.getSysConfig().then(resp => {
        resp = resp.data
        this.sysConfig = resp.data
      })
    },
    updateSysConfig() {
      systemApi.updateSysConfig(this.sysConfig).then(resp => {
        resp = resp.data
        if (resp.data) {
          this.$message.success('更新成功!')
        } else {
          this.$message.error('更新失败!')
        }
      })
    },
  },
  created() {
    document.title = '首页' + common.WEB_TITLE
    this.getTaskList()
    this.getConfig()
    this.getCourseOptions()
  }
}
</script>

<style scoped lang="less">
.main {
  display: flex;
  justify-content: center;
  margin-top: 30px;

  .mainBox {
    padding: 5px 20px;
    width: 1000px;
    height: 600px;

    .mainBoxTitle {
      text-align: center;
    }

    .viewTaskTablePagination {
      margin-top: 10px;
      text-align: center;
    }
  }
}

.logDialogTable {
  .logDialogTablePagination {
    margin-top: 10px;
    text-align: center;
  }
}
</style>