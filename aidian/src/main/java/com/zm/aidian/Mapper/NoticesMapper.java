package com.zm.aidian.Mapper;

import com.zm.aidian.dao.Notices;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NoticesMapper {
//    查询全部公告
    List<Notices> selectAllNotices();
//    通过id查询公告
    Notices selectNoticeById(@Param("id")Integer id);
//    添加公告
    Integer addNotice(Notices notices);
//    修改公告
    Integer updateNotice(Notices notices);
//    删除公告
    Integer deleteNoticeById(@Param("id")Integer id);
}
