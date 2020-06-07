package com.kenny.repository;

import com.kenny.entity.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * ClassName: MongoDBRepository
 * Function:  TODO
 * Date:      2020/6/6 18:46
 *
 * @author Kenny
 * version    V1.0
 */
public interface MongoDBRepository extends MongoRepository<MemberReadHistory, String> {

    List<MemberReadHistory> findMemberReadHistoriesByMemberId(Long memberId);





}
