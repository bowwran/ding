package com.zm.aidian.Mapper;


import com.zm.aidian.dao.Types;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface TypeMapper {
//    查询全部类型
    List<Types> selectAllType();
//通过id查询类型
    Types selectTypeById(@Param("id")Integer id);

//    添加类型
    Integer addType(Types types);
//    修改类型
    Integer updateType(Types types);
//    删除类型
    Integer deleteTypeById(@Param("id")Integer id);
}
