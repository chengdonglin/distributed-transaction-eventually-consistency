package com.dmai.transaction.stock.service;

import com.dmai.transaction.stock.bo.TxMessage;
import com.dmai.transaction.stock.entity.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 14:58:23
 */
public interface StockService extends IService<Stock>{
        /**
         * 扣减库存
         * @param txMessage
         */
    void decreaseStock(TxMessage txMessage);

        /**
         * 通过productId获取库存
         * @param productId
         * @return
         */
    Stock  getStockByProductId(Integer productId);

}
