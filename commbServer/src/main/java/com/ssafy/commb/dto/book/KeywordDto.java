package com.ssafy.commb.dto.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter                                     // Getter 생성
@Setter                                     // Setter 생성
@JsonInclude(JsonInclude.Include.NON_NULL)  // null 값은 JSON 반환 시 제외
@AllArgsConstructor                         // Builder pattern 사용 시 반드시 전체 인자를 포함하는 생성자 필수
@NoArgsConstructor                          // 기본 생성자
@Builder                                    // Builder 패턴 사용
@Alias("Keyword")
public class KeywordDto {
    private Integer id;
    private String keyword;
    private Integer cnt;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
        private KeywordDto data;
        private String retMsg;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ResponseList {
        private List<KeywordDto> data;
        private String retMsg;
    }
}
