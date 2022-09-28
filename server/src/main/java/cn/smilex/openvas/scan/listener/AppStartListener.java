package cn.smilex.openvas.scan.listener;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

/**
 * @author smilex
 * @date 2022/10/1/9:25
 * @since 1.0
 */
@Slf4j
@Component
public class AppStartListener implements ApplicationListener<ApplicationContextEvent> {
    @Override
    public void onApplicationEvent(@NotNull ApplicationContextEvent event) {
    }
}
