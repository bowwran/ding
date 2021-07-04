package com.zm.aidian.service.impl;

import com.zm.aidian.Mapper.NoticesMapper;
import com.zm.aidian.dao.Notices;
import com.zm.aidian.service.NoticesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticesServiceImpl implements NoticesService {

    @Autowired
    private NoticesMapper noticesMapper;
    @Override
    public List<Notices> selectAllNotices() {
        List<Notices> noticesList = noticesMapper.selectAllNotices();
        return noticesList;
    }

    @Override
    public Notices selectNoticeById(Integer id) {
        return noticesMapper.selectNoticeById(id);
    }

    @Override
    public Integer addNotice(Notices notices) {
        return noticesMapper.addNotice(notices);
    }

    @Override
    public Integer updateNotice(Notices notices) {
        return noticesMapper.updateNotice(notices);
    }

    @Override
    public Integer deleteNoticeById(Integer id) {
        return noticesMapper.deleteNoticeById(id);
    }
}
