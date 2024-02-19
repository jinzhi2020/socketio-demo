create table if not exists `users`
(
    `id`                bigint unsigned auto_increment not null primary key comment '主键',
    `username`          varchar(32)                    not null default '' comment '用户名',
    `password`          varchar(255)                   not null default '' comment '密码',
    `status`            varchar(32)                    not null default 'VALID' comment '状态:VALID-有效;INVALID-无效',
    `latest_login_time` bigint unsigned                not null default 0 comment '最近登录时间',
    `created_at`        timestamp                      not null comment '创建时间',
    `updated_at`        timestamp                      not null comment '更新时间'
) comment '用户表';