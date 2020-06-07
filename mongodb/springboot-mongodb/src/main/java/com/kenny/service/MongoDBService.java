package com.kenny.service;

import com.kenny.entity.MemberReadHistory;

import java.util.List;

/**
 * ClassName: MongoDBService
 * Function:  TODO
 * Date:      2020/6/6 19:14
 *
 * @author Kenny
 * version    V1.0
 */
public interface MongoDBService {

    int create(MemberReadHistory memberReadHistory);

    int delete(List<String> ids);

    List<MemberReadHistory> list(Long memberId);
}
