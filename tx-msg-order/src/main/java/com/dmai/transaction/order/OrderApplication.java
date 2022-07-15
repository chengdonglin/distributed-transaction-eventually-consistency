package com.dmai.transaction.order;


import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * <p>
 *
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 11:42:06
 */
@SpringBootApplication
@MapperScan(value = {"com.dmai.transaction.order.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)
public class OrderApplication {

    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class, args);
    }
}