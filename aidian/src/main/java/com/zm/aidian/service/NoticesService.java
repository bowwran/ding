package com.zm.aidian.service;

import com.zm.aidian.dao.Notices;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticesService {
    //    查询全部公告
    List<Notices> selectAllNotices();
    //    通过id查询公告
    Notices selectNoticeById(Integer id);
    //    添加公告
    Integer addNotice(Notices notices);
    //    修改公告
    Integer updateNotice(Notices notices);
    //    删除公告
    Integer deleteNoticeById(Integer id);
}
