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
                label="日志"
                :show-overflow-tooltip="true">
              <template slot-scope="slot">
                <el-button type="primary" size="mini" @click="logDialogClick(slot.row.id)">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
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
    </el-dialog>
  </div>
</template>

<script>
import common from '../config/common';
import taskApi from "@/api/task";

export default {
  name: 'HomeView',
  data() {
    return {
      activeName: 'viewTask',
      taskTable: [],
      task: {
        taskType: 0,
      },
      createTaskRules: {
        guoKaiId: [
          {required: true, message: '请输入任务ID!', trigger: 'blur'},
        ]
      },
      taskSubmitBtnDisable: true,
      logDialogVisible: false,
      logCacheTaskId: 0,
      logTableData: [],
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
      this.task = {
      }

      this.taskSubmitBtnDisable = false

      if (this.activeName === 'viewTask') {
        this.taskListPagination = {
          total: 0,
          currentPage: 1,
          pageSize: 10
        }
        this.getTaskList()
      }
    },
    taskSubmit(formName, taskType) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

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

.logDialogTable {
  .logDialogTablePagination {
    margin-top: 10px;
    text-align: center;
  }
}
</style>