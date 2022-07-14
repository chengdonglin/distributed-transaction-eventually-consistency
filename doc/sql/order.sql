create table if not exists `order`
(
    `id` bigint(20) primary key comment 'ID',
    `order_no` varchar(64) not null comment '订单编号',
    `product_id` bigint(20) not null comment '商品id',
    `pay_count` int not null comment '购买数量',
    `create_time` datetime comment '创建时间',
    index order_index(order_no),
    index product_index(product_id)
) engine=InnoDB default charset=utf8mb4 comment '订单表';

create table if not exists `tx_log`(
    `tx_no` varchar(64) not null comment '分布式事务全局序列号',
    `create_time` datetime default null comment '创建时间',
    primary key (tx_no)
)engine=InnoDB default charset=utf8mb4 comment '事务日志';