package cn.smilex.openvas.scan.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author smilex
 * @date 2022/9/30/20:29
 * @since 1.0
 */
@Data
@Component
@ConfigurationProperties("common")
public class CommonConfig {
    public static final Path BACK_PATH = Paths.get(
            System.getProperty("user.dir") + File.separator + "back"
    );

    private String passWordKey;
    private String redisUserNameListKey;
    private String redisCodeCaptchaIndex;
    private String redisCodeCaptchaListKey;
    private String redisSysConfigKey;
    private String redisErrorCountKey;
    private String redisUnBlockTimeKey;
    private Integer redisErrorMaxCount;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
