package cn.smilex.openvas.scan.listener;

import cn.smilex.openvas.scan.config.CommonConfig;
import cn.smilex.openvas.scan.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author smilex
 * @date 2022/10/1/19:35
 * @since 1.0
 */
@Slf4j
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    private CommonConfig commonConfig;
    private RedisService redisService;

    @Autowired
    public void setCommonConfig(CommonConfig commonConfig) {
        this.commonConfig = commonConfig;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(@NotNull Message message, byte[] pattern) {
        final String key = message.toString();
        if (key.contains(commonConfig.getRedisCodeCaptchaListKey())) {
            String id = key.substring(key.lastIndexOf(":") + 1);
        }
    }
}
