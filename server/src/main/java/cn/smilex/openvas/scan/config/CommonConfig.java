package cn.smilex.openvas.scan.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static final Object EMPTY_OBJECT = new Object();
    public static final List<?> EMPTY_LIST = new ArrayList<>(0);
    public static final Map<?, ?> EMPTY_MAP = new HashMap<>(0);

    public static final String OPENVAS_COMMAND_PREFIX =
            "sudo -Hiu gvm gvm-cli --gmp-username \"admin\" --gmp-password \"admin\" socket --socketpath /var/run/gvmd/gvmd.sock --xml \"%s\"";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
