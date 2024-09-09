package com.azhu.apocalypse.config.listen;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author: azhu
 * @date: 2024/9/9 17:09
 * @desc:
 */
@Component
public class TestEventListener {
    @EventListener
    public void handleEvent(Object event) {
        // 处理事件
        System.out.println("接收到事件: " + event);
    }
}
