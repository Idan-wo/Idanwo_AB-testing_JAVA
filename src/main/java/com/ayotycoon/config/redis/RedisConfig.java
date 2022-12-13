package com.ayotycoon.config.redis;


import com.ayotycoon.services.CONSTANTS;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class RedisConfig  {
    @Value("${redis.host:localhost}")
    private String redisHost;
    @Value("${redis.port:6379}")
    private Integer redisPort;


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(redisHost);
        jedisConFactory.setPort(redisPort);
        return jedisConFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    DefaultMessageDelegate listener() {
        return new DefaultMessageDelegate();
    }

    @Bean
    MessageListenerAdapter messageListener(DefaultMessageDelegate listener ) {
        return new MessageListenerAdapter(listener, "handleMessage");
    }


    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory, DefaultMessageDelegate listener) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener(listener), ChannelTopic.of(CONSTANTS.redisSubKey));
        return container;
    }
}