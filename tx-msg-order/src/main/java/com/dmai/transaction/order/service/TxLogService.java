package com.dmai.transaction.order.service;

import com.dmai.transaction.order.entity.TxLog;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 09:53:53
 */
public interface TxLogService extends IService<TxLog>{
        /**
         * 是否存在全局事务
         * @param txNo
         * @return
         */
    Boolean isExistsTx(String txNo);

        /**
         * 保存事务日志
         * @param txNo
         */
    void saveTxLog(String txNo);
}
