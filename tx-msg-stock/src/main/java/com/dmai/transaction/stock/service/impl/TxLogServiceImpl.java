package com.dmai.transaction.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmai.transaction.stock.entity.TxLog;
import com.dmai.transaction.stock.mapper.TxLogMapper;
import com.dmai.transaction.stock.service.TxLogService;
/**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 14:58:23
 */
@Service
public class TxLogServiceImpl extends ServiceImpl<TxLogMapper, TxLog> implements TxLogService{

    @Override
    public Boolean isExistsTx(String txNo) {
        QueryWrapper<TxLog> wrapper = new QueryWrapper<>();
        wrapper.eq(TxLog.COL_TX_NO,txNo);
        int count = this.count(wrapper);
        return count == 0 ? false : true;
    }


    @Override
    public void saveTxLog(String txNo) {
        TxLog txLog = new TxLog();
        txLog.setTxNo(txNo);
        txLog.setCreateTime(new Date());
        this.save(txLog);
    }
}
