

CREATE TABLE vshop_distribution_item
(
  id BIGINT PRIMARY KEY NOT NULL COMMENT '主键' AUTO_INCREMENT,
  item_id BIGINT NOT NULL COMMENT '商品id',
  status TINYINT NOT NULL COMMENT '上下架标示 0:下架,1:上架,2:冻结',
  vcategory_id BIGINT DEFAULT 0 NOT NULL COMMENT '分类id，默认0未分类',
  vshop_id BIGINT NOT NULL COMMENT '微店id',
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
  is_delete INT DEFAULT 0 NOT NULL COMMENT '删除标示0:未删除1：已删除',
  story VARCHAR(512) COMMENT '商品故事'
);
CREATE INDEX idx_item_id ON vshop_distribution_item (item_id);
CREATE INDEX idx_vshop_id_vcategory_id_item_id ON vshop_distribution_item (vshop_id, vcategory_id, item_id);