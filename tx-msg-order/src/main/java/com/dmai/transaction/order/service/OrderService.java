package com.dmai.transaction.order.service;

import com.dmai.transaction.order.bo.TxMessage;
import com.dmai.transaction.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 09:53:54
 */
public interface OrderService extends IService<Order>{
        /**
         * 提交订单同时保持事务信息
         * @param txMessage
         */
    void submitOrderAndSaveTxNo(TxMessage txMessage);

        /**
         * 提交订单
         * @param productId  商品id
         * @param payCount  购买数量
         */
    void submitOrder(Integer productId,Integer payCount);
}
