package com.dmai.transaction.stock.listener;

import com.alibaba.fastjson.JSONObject;
import com.dmai.transaction.stock.bo.TxMessage;
import com.dmai.transaction.stock.service.StockService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 15:33:28
 */
@Component
@RocketMQMessageListener(topic = "tx_order_group",consumerGroup = "tx_stock_group")
@Slf4j
public class StockTxMessageConsumer implements RocketMQListener<String> {

    @Autowired
    private StockService stockService;

    @Override
    public void onMessage(String message) {
        log.info("库存微服务开始消费事务消息:{}",message);
        TxMessage txMessage = this.getTxMessage(message);
        try {
            stockService.decreaseStock(txMessage);
        } catch (Exception e) {

        }
    }

    private TxMessage getTxMessage(String msg) {
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String txMessage = jsonObject.getString("txMessage");
        return JSONObject.parseObject(txMessage,TxMessage.class);
    }
}
