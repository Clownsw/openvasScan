<template>
  <div class="main">
    <div class="mainBox">
      <h1 class="mainBoxTitle">Openvas漏扫Demo</h1>
      <el-tabs v-model="activeName" @tab-click="handlerTabsClick">

        <el-tab-pane label="创建任务" name="createTask">
          <el-form :model="task" :rules="createTaskRules" ref="taskForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="课程ID" prop="guoKaiId">
              <el-input v-model="task.guoKaiId"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="taskSubmit('taskForm', 0)" :disabled="taskSubmitBtnDisable">创建任务
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
                prop="name"
                align="center"
                label="任务名称"
                :show-overflow-tooltip="true">
            </el-table-column>

            <el-table-column
                prop="id"
                align="center"
                label="任务ID"
                width="350px"
                :show-overflow-tooltip="true">
            </el-table-column>

            <el-table-column
                prop="status"
                align="center"
                label="状态"
                width="100px"
                :show-overflow-tooltip="true">
            </el-table-column>

            <el-table-column
                prop="createdTime"
                align="center"
                label="创建时间"
                :show-overflow-tooltip="true">
            </el-table-column>

            <el-table-column
                prop="modifyTime"
                align="center"
                label="修改时间"
                :show-overflow-tooltip="true">
            </el-table-column>

            <el-table-column
                prop="log"
                align="center"
                label="操作"
                width="300px"
                :show-overflow-tooltip="true">
              <template slot-scope="slot">
                <el-button type="primary" :disabled="slot.row.status !== 'New'" size="mini"
                           @click="startTaskClick(slot.row.id)">启动任务
                </el-button>

                <el-button type="danger" :disabled="slot.row.status !== 'Done'" size="mini"
                           @click="showTaskResultDialogClick(slot.row.id)">查看结果
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>

    <el-dialog
        top="2%"
        title="任务执行结果"
        append-to-body
        :visible.sync="taskResultDialogVisible"
        width="60%"
        :before-close="taskResultDialogCloseHandler">
      <el-input v-model="taskResult" readonly type="textarea" :autosize="{ minRows: 10, maxRows: 30}"/>
    </el-dialog>
  </div>
</template>

<script>
import common from '../config/common';
import taskApi from "@/api/task";
import resultApi from "@/api/result";

export default {
  name: 'HomeView',
  data() {
    return {
      activeName: 'viewTask',
      taskTable: [],
      task: {
        taskType: 0,
      },
      taskResult: {},
      createTaskRules: {
        guoKaiId: [
          {required: true, message: '请输入任务ID!', trigger: 'blur'},
        ]
      },
      taskSubmitBtnDisable: true,
      taskResultDialogVisible: false
    }
  },
  methods: {
    getTaskList() {
      taskApi.taskList().then(resp => {
        resp = resp.data

        if (resp.code === 1000) {
          this.taskTable = resp.data
        } else {
          this.$message.error('获取任务列表失败!')
        }
      })
    },
    handlerTabsClick() {
      this.task = {}

      this.taskSubmitBtnDisable = false

      if (this.activeName === 'viewTask') {
        this.getTaskList()
      }
    },
    taskSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

        } else {
          return false;
        }
      })
    },
    startTaskClick(taskId) {
      taskApi.startTask(taskId).then(resp => {
        resp = resp.data
        if (resp.code === 1000 && resp.data !== null) {
          this.$message.success('启动任务成功')
        } else {
          this.$message.success('启动任务失败')
        }
        this.getTaskList()
      })
    },
    showTaskResultDialogClick(taskId) {
      resultApi.selectResultById(taskId).then(resp => {
        resp = resp.data

        if (resp.code === 1000) {
          this.taskResult = resp.data
          this.taskResultDialogVisible = true
        } else {
          this.$message.error('获取任务结果失败')
          this.taskResultDialogVisible = false
        }
      })
    },
    taskResultDialogCloseHandler(done) {
      this.taskResult = null
      done()
    },
  },
  created() {
    document.title = '首页' + common.WEB_TITLE
    this.getTaskList()
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
    width: 1200px;
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
</style>