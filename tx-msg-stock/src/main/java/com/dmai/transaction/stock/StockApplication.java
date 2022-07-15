package com.dmai.transaction.stock;

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
 * @since 2022-07-15 14:54:28
 */
@SpringBootApplication
@MapperScan(value = {"com.dmai.transaction.stock.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class,args);
    }
}
