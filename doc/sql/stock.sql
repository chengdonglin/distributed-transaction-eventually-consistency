create table if not exists `stock`
(
    `id` bigint(20) not null comment 'ID',
    `product_id` bigint(20) not null comment '商品id',
    `total_count` int(11) default 0 comment '商品总库存',
    primary key (id)
) engine=InnoDB default charset=utf8mb4 comment '模拟库存';

create table if not exists `tx_log`(
    `tx_no` varchar(64) not null comment '分布式事务全局序列号',
    `create_time` datetime default null comment '创建时间',
    primary key (tx_no)
)engine=InnoDB default charset=utf8mb4 comment '事务日志';