package cn.smilex.openvas.scan.runtime;

import cn.smilex.openvas.scan.config.CommonConfig;

/**
 * @author smilex
 */
public class OpenvasProcessTask extends ProcessTask {
    public OpenvasProcessTask(String command) {
        String[] args = new String[]{"/bin/sh", "-c", String.format(CommonConfig.OPENVAS_COMMAND_PREFIX, command)};
        super.setCommand(args);
    }
}
