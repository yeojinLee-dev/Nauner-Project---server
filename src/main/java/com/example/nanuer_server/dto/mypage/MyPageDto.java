package com.example.nanuer_server.dto.mypage;

import com.example.nanuer_server.domain.entity.MyPageEntity;
import com.example.nanuer_server.dto.User.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageDto {
    private int myPageId;
    private UserInfoDto userInfoDto;

    public MyPageEntity toEntity(){
        MyPageEntity myPageEntity = MyPageEntity.builder()
                .myPageId(myPageId)
                .userEntity(userInfoDto.UserInfoToEntity())
                .build();
        return myPageEntity;
    }
}
