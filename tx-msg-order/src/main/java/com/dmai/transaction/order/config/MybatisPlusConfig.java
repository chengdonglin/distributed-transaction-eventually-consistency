package com.dmai.transaction.order.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 *
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-14 18:45:28
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = {"com.dmai.transaction.order.mapper"})
public class MybatisPlusConfig {

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor();
    }
}
