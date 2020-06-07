package com.kenny.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * ClassName: MemberReadHistory
 * Function:  TODO
 * Date:      2020/6/6 18:58
 *
 * @author Kenny
 * version    V1.0
 */
@Document
@Data
public class MemberReadHistory {

    @Id
    private String id;
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long productId;
    private String productName;
    private String productPic;
    private String productSubTitle;
    private String productPrice;
    private Date createTime;

}
