package com.dmai.transaction.stock.service;

import com.dmai.transaction.stock.entity.TxLog;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 14:58:23
 */
public interface TxLogService extends IService<TxLog>{

        /**
         * 检查是否存在事务
         * @param txNo
         * @return
         */
    Boolean isExistsTx(String txNo);

        /**
         * 记录事务日志
         * @param txNo
         */
    void saveTxLog(String txNo);

}
