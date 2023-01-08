package cn.smilex.openvas.scan.runtime;

import cn.hutool.core.io.IoUtil;
import cn.smilex.openvas.scan.config.CommonConfig;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

/**
 * @author smilex
 */
@Data
public class ProcessTask implements Callable<ProcessTask> {
    private String[] command;
    private String result;
    private String errorMsg;

    protected ProcessTask() {
        this(null);
    }

    public ProcessTask(String[] command) {
        this.command = command;
        this.result = CommonConfig.EMPTY_STRING;
        this.errorMsg = CommonConfig.EMPTY_STRING;
    }

    @Override
    public ProcessTask call() {
        try {
            Process process = Runtime.getRuntime().exec(this.command);
            if (process.waitFor() != 0) {
                this.errorMsg = IoUtil.read(process.getErrorStream(), StandardCharsets.UTF_8);
            } else {
                this.result = IoUtil.read(process.getInputStream(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            this.errorMsg = e.getMessage();
        }

        return this;
    }

    public boolean isError() {
        return !CommonConfig.EMPTY_STRING.equals(this.errorMsg);
    }
}
