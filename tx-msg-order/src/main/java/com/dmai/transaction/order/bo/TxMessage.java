package com.dmai.transaction.order.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 10:10:02
 */
@Data
@AllArgsConstructor
public class TxMessage implements Serializable {
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 购买数量
     */
    private Integer payCount;
    /**
     * 全局事务编号
     */
    private String txNo;
}
