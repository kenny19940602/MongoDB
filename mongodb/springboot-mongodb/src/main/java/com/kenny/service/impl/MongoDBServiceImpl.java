package com.kenny.service.impl;

import com.kenny.entity.MemberReadHistory;
import com.kenny.repository.MongoDBRepository;
import com.kenny.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: MongoDBServiceImpl
 * Function:  TODO
 * Date:      2020/6/6 19:43
 *
 * @author Kenny
 * version    V1.0
 */
@Service
public class MongoDBServiceImpl implements MongoDBService {

    @Autowired
    private MongoDBRepository mongoDBRepository;
    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        mongoDBRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for (String id : ids) {
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        mongoDBRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return mongoDBRepository.findMemberReadHistoriesByMemberId(memberId);
    }
}
