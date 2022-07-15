package com.dmai.transaction.stock.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 15:04:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TxMessage implements Serializable {

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 购买数量
     */
    private Integer  payCount;
    /**
     * 全局事务编号
     */
    private String txNo;
}
