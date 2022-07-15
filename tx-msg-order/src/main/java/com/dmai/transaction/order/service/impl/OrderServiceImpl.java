package com.dmai.transaction.order.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmai.transaction.order.bo.TxMessage;
import com.dmai.transaction.order.service.TxLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import java.util.Date;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmai.transaction.order.mapper.OrderMapper;
import com.dmai.transaction.order.entity.Order;
import com.dmai.transaction.order.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 09:53:54
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

    @Autowired
    private TxLogService txLogService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitOrderAndSaveTxNo(TxMessage txMessage) {
        Boolean existsTx = txLogService.isExistsTx(txMessage.getTxNo());
        if (existsTx) {
            log.info("订单微服务已经执行过事务，商品id为：{},事务编号为:{}",txMessage.getProductId(),txMessage.getTxNo());
            return;
        }
        // 生成订单
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setOrderNo(IdUtil.getSnowflakeNextIdStr());
        order.setPayCount(txMessage.getPayCount());
        order.setProductId(txMessage.getProductId());
        this.save(order);
        // 添加事务日志
        this.txLogService.saveTxLog(txMessage.getTxNo());
    }

    @Override
    public void submitOrder(Integer productId, Integer payCount) {
        // 生产全局分布式序列化
        String txNo = IdUtil.fastUUID();
        TxMessage txMessage = new TxMessage(productId,payCount,txNo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("txMessage",txMessage);
        Message<String> message = MessageBuilder.withPayload(jsonObject.toString()).build();
        // 发送一条事务消息
        rocketMQTemplate.sendMessageInTransaction("tx_order_group",message,null);
    }
}
