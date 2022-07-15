package com.dmai.transaction.stock.entity;

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
 * @since 2022-07-15 14:58:23
 */
/**
    * 事务日志
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tx_log")
public class TxLog {
    /**
     * 分布式事务全局序列号
     */
    private String txNo;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    public static final String COL_TX_NO = "tx_no";

    public static final String COL_CREATE_TIME = "create_time";
}