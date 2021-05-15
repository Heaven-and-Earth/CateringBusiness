package com.restManager.operator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restManager.opeartor.pojo.OperatorUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OpeartorUserMapper
 * @Description TODO
 * @date 2021/5/15 15:13
 * @Version 1.0
 */
@Mapper
public interface OpeartorUserMapper extends BaseMapper<OperatorUser> {
}
