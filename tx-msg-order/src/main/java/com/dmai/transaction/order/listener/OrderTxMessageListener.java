package com.dmai.transaction.order.listener;

import com.alibaba.fastjson.JSONObject;
import com.dmai.transaction.order.bo.TxMessage;
import com.dmai.transaction.order.service.OrderService;
import com.dmai.transaction.order.service.TxLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  实现 rocketmq 回调生产者的 executeLocalTransaction(Message msg, Object arg)，该方法执行本地事务提交订单信息
 *  checkLocalTransaction(Message msg) 通过这个方法来查询消息生产者的本地事务状态，这个方法来查询消息生产本地事务状态
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 10:49:28
 */
@Component
@RocketMQTransactionListener
@Slf4j
public class OrderTxMessageListener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TxLogService txLogService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
       try {
           log.info("订单微服务执行本地事务");
           TxMessage txMessage =  this.getTxMessage(msg);
           // 执行本地事务
           orderService.submitOrderAndSaveTxNo(txMessage);
           // 提交事务
           log.info("订单微服务提交事务");
           return RocketMQLocalTransactionState.COMMIT;
       } catch (Exception e) {
           e.printStackTrace();
           log.info("订单微服务回滚事务");
           return RocketMQLocalTransactionState.ROLLBACK;
       }
    }


    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info("订单微服务查询本地事务");
        TxMessage txMessage = this.getTxMessage(msg);
        Boolean tx = txLogService.isExistsTx(txMessage.getTxNo());
        if (tx) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }


    private TxMessage getTxMessage(Message msg) {
        String messageString = new String((byte[]) msg.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String txStr = jsonObject.getString("txMessage");
        return JSONObject.parseObject(txStr,TxMessage.class);
    }
}
