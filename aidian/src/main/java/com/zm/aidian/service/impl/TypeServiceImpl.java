package com.zm.aidian.service.impl;

import com.zm.aidian.Mapper.TypeMapper;
import com.zm.aidian.dao.Types;
import com.zm.aidian.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Types> selectAllType() {
        List<Types> typeList = typeMapper.selectAllType();
        return typeList;
    }

    @Override
    public Types selectTypeById(Integer id) {
        Types type = typeMapper.selectTypeById(id);
        return type;
    }

    @Override
    public Integer addType(Types types) {
        return typeMapper.addType(types);
    }

    @Override
    public Integer updateType(Types types) {
        return typeMapper.updateType(types);
    }

    @Override
    public Integer deleteTypeById(Integer id) {
        return typeMapper.deleteTypeById(id);
    }


}
