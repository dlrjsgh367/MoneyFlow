-- 사용자 테이블
create table user
(
    `id`         bigint auto_increment primary key,
    `username`   varchar(50) unique  not null,
    `email`      varchar(100) unique not null,
    `password`   varchar(255)        not null,
    `created_at` timestamp default current_timestamp
);

-- 소비 내역 테이블
create table expense
(
    `id`          bigint auto_increment primary key,
    `user_id`     bigint         not null,
    `category_id` bigint,
    `amount`      decimal(10, 2) not null,
    `description` varchar(255),
    `spent_at`    date           not null,
    `created_at`  timestamp default current_timestamp,
    foreign key (`user_id`) references user (`id`) on delete cascade,
    foreign key (`category_id`) references category (`id`) on delete set null
);

-- 소비 카테고리 테이블
create table category
(
    `id`         bigint auto_increment primary key,
    `name`       varchar(50) unique not null,
    `created_at` timestamp default current_timestamp
);

-- 예산 설정 테이블
create table budget
(
    `id`          bigint auto_increment primary key,
    `user_id`     bigint         not null,
    `category_id` bigint         not null,
    `amount`      decimal(10, 2) not null,
    `month`       date           NOT NULL, -- YYYY-MM 형식
    `created_at`  timestamp default current_timestamp,
    foreign key (user_id) references user (id) on delete cascade,
    foreign key (category_id) references category (id) on delete cascade
);

-- 수입 내역 테이블
CREATE TABLE income
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT         NOT NULL,
    source      VARCHAR(50)    NOT NULL, -- 최적화: 100 → 50
    amount      DECIMAL(10, 2) NOT NULL,
    received_at DATE           NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

-- 인덱스 추가
CREATE INDEX idx_expense_user ON expense(user_id);
CREATE INDEX idx_expense_category ON expense(category_id);
CREATE INDEX idx_income_user ON income(user_id);
CREATE INDEX idx_budget_user ON budget(user_id);
CREATE INDEX idx_budget_category ON budget(category_id);

ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'moneyflow123';
FLUSH PRIVILEGES;

ALTER USER 'moneyflow_user'@'%' IDENTIFIED WITH mysql_native_password BY 'moneyflow_pass';
FLUSH PRIVILEGES;

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'moneyflow123';
FLUSH PRIVILEGES;
