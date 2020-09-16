package com.children.repository.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * author 孙博
 * date 2020/9/4 16:50
 */
@Data
@Document(collection = "behavior_record")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BehaviorRecordEntity {

    private String id;

    private String content;

    private List<String> imageUrlList;

    private Long collectionNum;

    private Long praiseNum;

    private Long responseNum;

    private String userId;

    private String userAvatar;

    private String createTime;

    private String updateTime;
}
