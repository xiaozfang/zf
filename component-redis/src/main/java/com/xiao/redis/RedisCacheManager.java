//package com.xiao.redis;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.lettuce.LettuceConnection;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Configuration
//@EnableCaching
//@EnableConfigurationProperties(RedisConfig.class)
//public class RedisCacheConfig extends CachingConfigurerSupport implements LettuceConnection {
//
//    @Autowired
//    private RedisConfig redisConfig;
//
//    @Bean
//    @Override
//    public KeyGenerator keyGenerator() {
//        return (target, method, params) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append(":");
//            sb.append(method.getName());
//            if (ArrayUtils.isNotEmpty(params)) {
//                String collect = Arrays.stream(params).map(x -> x.getClass().getSimpleName()).collect(Collectors.joining(",", "(", ")"));
//                sb.append(collect);
//            }
//            return sb.toString();
//        };
//    }
//
//    @Bean
//    @Override
//    @ConditionalOnProperty({"redis.cache-one.host"})
//    public RedisCacheManager cacheManager() {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer()))
//                .entryTtl(redisConfig.getEntryTtl());
//        RedisCacheManager cacheManager = RedisCacheManager.builder(lettuceConnectionFactory(redisConfig))
//                .cacheDefaults(redisCacheConfiguration)
//                .transactionAware()
//                .build();
//        cacheManager.afterPropertiesSet();
//        log.info("RedisCacheManager config success");
//        return cacheManager;
//    }
//
//    private FastJsonRedisSerializer<Object> fastJsonRedisSerializer() {
//        ParserConfig.getGlobalInstance().addAccept("com.demo.entity.");
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.WriteNullListAsEmpty,
//                SerializerFeature.WriteDateUseDateFormat,
//                SerializerFeature.WriteEnumUsingToString,
//                SerializerFeature.WriteClassName);
//        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//        fastJsonRedisSerializer.setFastJsonConfig(fastJsonConfig);
//        return fastJsonRedisSerializer;
//    }
//}