package com.example.nanuer_server.dto.Chat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetChatUserDto {

    private int userId;

    private int roomNumber;
}
