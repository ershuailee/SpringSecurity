package com.example.springsecurity.entity.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * <p>
 * 通用基础实体类
 * </p>
 *
 * @author 李二帅
 * @since 2023-03-28
 */
@Data
public class BasePageEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @TableLogic
    private Integer deleteFlag = 0;

    /**
     * 总的记录条数
     */
    @TableField(exist = false)
    private Long totalNum;

    /**
     * 页码
     */
    @TableField(exist = false)
    private Long pageNum = 1L;

    /**
     * 每页显示长度
     */
    @TableField(exist = false)
    private Long pageSize = 10L;
}
