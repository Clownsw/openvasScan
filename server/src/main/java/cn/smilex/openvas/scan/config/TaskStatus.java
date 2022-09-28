package cn.smilex.openvas.scan.config;

import lombok.Getter;

/**
 * @author smilex
 * @date 2022/10/2/22:12
 * @since 1.0
 */
@Getter
public enum TaskStatus {
    CREATE((short) 0),
    RUNNING((short) 1),
    FINISH((short) 2),
    ERROR((short) 3);

    final Short status;

    TaskStatus(short status) {
        this.status = status;
    }
}
