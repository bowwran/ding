package com.zm.aidian.service;

import com.zm.aidian.dao.Types;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeService {
    //    查询全部类型
    List<Types> selectAllType();
    //通过id查询类型
    Types selectTypeById(Integer id);
    //    添加类型
    Integer addType(Types types);
    //    修改类型
    Integer updateType(Types types);
    //    删除类型
    Integer deleteTypeById(Integer id);
}
