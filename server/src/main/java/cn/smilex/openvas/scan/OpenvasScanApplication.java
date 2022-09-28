package cn.smilex.openvas.scan;

import cn.smilex.openvas.scan.util.CommonUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author smilex
 * @date 2022年9月28日13:15:54
 * @since 1.0
 */
@EnableScheduling
@MapperScan(basePackages = "cn.smilex.openvas.scan.mapper")
@SpringBootApplication
public class OpenvasScanApplication {

    public static void main(String[] args) {
        CommonUtil.configurableApplicationContext = SpringApplication.run(OpenvasScanApplication.class, args);
    }

}
