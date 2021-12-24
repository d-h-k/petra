package com.board.post.dto;

import com.board.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PostDtoRequest {

    @Builder
    public PostDtoRequest(String title, String contents, LocalDateTime validFrom, LocalDateTime validUntil) {
        this.title = title;
        this.contents = contents;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    private String title;

    private String contents;

    private LocalDateTime validFrom;

    private LocalDateTime validUntil;

    //@todo : 첨부파일 여러개를 담는 필드 필요

    //주의 : spring bean 객체가 아니라 DI 받을수가 없어 modelMapper사용이 불가능함. 고로 필드가 추가되면 수동업데이트 필요함
    public Post toPost() {
        return Post
                .builder()
                .title(this.title)
                .contents(this.contents)
                .validFrom(this.validFrom)
                .validUntil(this.validUntil)
                .build();
    }

    public PostDtoRequest validExtension() {
        this.validFrom = LocalDateTime.now().minusYears(100);
        this.validUntil = LocalDateTime.now().plusYears(100);
        return this;
    }
}
