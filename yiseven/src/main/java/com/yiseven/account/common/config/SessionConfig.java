package com.yiseven.account.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author hdeng
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 36000)
public class SessionConfig {

}
