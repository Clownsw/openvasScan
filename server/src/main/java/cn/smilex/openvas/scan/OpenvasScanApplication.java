package cn.smilex.openvas.scan;

import cn.smilex.openvas.scan.util.CommonUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author smilex
 * @date 2022年9月28日13:15:54
 * @since 1.0
 */
@SpringBootApplication
public class OpenvasScanApplication {

    public static void main(String[] args) {
        CommonUtil.configurableApplicationContext = SpringApplication.run(OpenvasScanApplication.class, args);
    }

}
