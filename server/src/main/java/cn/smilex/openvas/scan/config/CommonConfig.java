package cn.smilex.openvas.scan.config;

import cn.hutool.core.lang.Snowflake;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

/**
 * @author smilex
 * @date 2022/9/30/20:29
 * @since 1.0
 */
@Data
@Component
@ConfigurationProperties("common")
public class CommonConfig {
    public static final String EMPTY_STRING = "";
    public static final Snowflake SNOWFLAKE = new Snowflake(2, 3);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final Path BACK_PATH = Paths.get(
            System.getProperty("user.dir") + File.separator + "back"
    );
    public static final String OPENVAS_COMMAND_PREFIX =
            "sudo -Hiu gvm gvm-cli --gmp-username \"admin\" --gmp-password \"admin\" socket --socketpath /var/run/gvmd/gvmd.sock --xml \"%s\"";

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
