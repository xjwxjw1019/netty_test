package com.zengzhi.nettyall.websocket.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author xiejiawei
 * @Date 2021-05-22
 * @Time 18:45
 */
@Configuration
public class WebSocketConfig {

    /**
     * 扫描当前项目中带有ServerEndpoint注解，转化为webSocket实例
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
