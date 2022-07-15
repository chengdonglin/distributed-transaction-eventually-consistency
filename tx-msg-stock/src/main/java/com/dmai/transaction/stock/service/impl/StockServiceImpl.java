package com.dmai.transaction.stock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dmai.transaction.stock.bo.TxMessage;
import com.dmai.transaction.stock.service.TxLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmai.transaction.stock.mapper.StockMapper;
import com.dmai.transaction.stock.entity.Stock;
import com.dmai.transaction.stock.service.StockService;
/**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 14:58:23
 */
@Service
@Slf4j
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService{

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private TxLogService txLogService;

    @Override
    public void decreaseStock(TxMessage txMessage) {
        log.info("库存微服务执行本地事务，商品id:{},购买数量：{}",txMessage.getProductId(),txMessage.getPayCount());
        // 检查是否执行过事务
        Boolean existsTx = txLogService.isExistsTx(txMessage.getTxNo());
        if (existsTx) {
            log.info("库存微服务已经执行过本地事务，事务编号为:{}",txMessage.getTxNo());
            return;
        }
        // 需要加锁
        Stock stock = this.getStockByProductId(txMessage.getProductId());
        if (stock.getTotalCount() < txMessage.getPayCount()) {
            throw new RuntimeException("库存不足");
        }
        this.updateCount(stock,txMessage.getPayCount());
        // 记录事务日志
        txLogService.saveTxLog(txMessage.getTxNo());
    }


    @Override
    public Stock getStockByProductId(Integer productId) {
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.eq(Stock.COL_PRODUCT_ID,productId);
        Stock stock = this.getOne(wrapper);
        return stock;
    }

    private void updateCount(Stock stock,Integer payCount) {
        stock.setTotalCount(stock.getTotalCount() - payCount);
        this.updateById(stock);
    }
}
