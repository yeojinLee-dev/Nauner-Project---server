package com.example.nanuer_server.dto.mypage;

import com.example.nanuer_server.domain.entity.MyPageEntity;
import com.example.nanuer_server.dto.User.UserDto;
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
    private Long myPageId;
    private UserDto userDto;

    public MyPageEntity toEntity(){
        MyPageEntity myPageEntity = MyPageEntity.builder()
                .myPageId(myPageId)
                .userEntity(userDto.toEntity())
                .build();
        return myPageEntity;
    }
}
