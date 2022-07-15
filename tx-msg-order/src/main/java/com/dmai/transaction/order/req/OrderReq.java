package com.dmai.transaction.order.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 11:40:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {
    private Integer productId;
    private Integer payCount;
}
