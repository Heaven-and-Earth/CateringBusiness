package com.restManager.operator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restManager.opeartor.pojo.EnterpriseAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName EnterpriseAccountMapper
 * @date 2021/5/16 16:32
 * @Version 1.0
 * @Author ShyBoy
 */
@Mapper
public interface EnterpriseAccountMapper extends BaseMapper<EnterpriseAccount> {

    //账号还原
    @Update("update t_enterprise_account set is_deleted=0 where enterprise_id=#{id} and is_deleted=1")
    boolean recovery(@Param("id") String id);

}
