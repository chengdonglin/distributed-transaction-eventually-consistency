package com.dmai.transaction.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmai.transaction.order.entity.TxLog;
import com.dmai.transaction.order.mapper.TxLogMapper;
import com.dmai.transaction.order.service.TxLogService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 09:53:53
 */
@Service
public class TxLogServiceImpl extends ServiceImpl<TxLogMapper, TxLog> implements TxLogService{

    @Override
    public Boolean isExistsTx(String txNo) {
        QueryWrapper<TxLog> wrapper = new QueryWrapper<TxLog>();
        wrapper.eq(TxLog.COL_TX_NO,txNo);
        int count = this.count(wrapper);
        return count == 0 ? false : true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTxLog(String txNo) {
        TxLog txLog = new TxLog();
        txLog.setTxNo(txNo);
        txLog.setCreateTime(new Date());
        this.save(txLog);
    }
}
