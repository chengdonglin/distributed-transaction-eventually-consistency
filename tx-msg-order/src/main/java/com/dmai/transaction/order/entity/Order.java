package com.dmai.transaction.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author linchengdong
 * @since 2022-07-15 09:53:54
 */
/**
    * 订单表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`order`")
public class Order {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 购买数量
     */
    @TableField(value = "pay_count")
    private Integer payCount;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_PAY_COUNT = "pay_count";

    public static final String COL_CREATE_TIME = "create_time";
}