package com.dmai.transaction.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmai.transaction.stock.entity.Stock;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 14:58:23
 */
public interface StockMapper extends BaseMapper<Stock> {
    /**
     * 修改商品库存
     * @param payCount
     * @param id
     */
    void UpdateTotalCountById(@Param("payCount") Integer payCount, @Param("id") Integer id);
}