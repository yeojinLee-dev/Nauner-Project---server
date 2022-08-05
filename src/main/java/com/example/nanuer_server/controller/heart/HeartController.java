package com.example.nanuer_server.controller.heart;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.dto.heart.HeartDto;
import com.example.nanuer_server.service.heart.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
public class HeartController {
    private final HeartService heartService;

    // 내가 찜한 게시물 조회는 마이페이지 서비스에서 구현함.

    @PostMapping("/add")
    public BaseResponse<HeartDto> addHeart(@RequestBody HeartDto addheartDto){
        try {
            HeartDto addedHeartDto = heartService.addHeart(addheartDto);
            return new BaseResponse<>(addedHeartDto);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @DeleteMapping("/delete/{heart_id}")
    public BaseResponse<String> deleteHeart(@PathVariable(name = "heart_id") int heartId){
        try {
            heartService.deleteHeart(heartId);
            String message = "찜이 해제되었습니다.";
            return new BaseResponse<>(message);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
