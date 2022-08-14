package com.example.nanuer_server.controller.User;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.entity.UserRole;
import com.example.nanuer_server.dto.User.JoinUserDto;

import com.example.nanuer_server.dto.User.UserInfoDto;
import com.example.nanuer_server.service.User.UserService;
import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.dto.User.LoginUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


@Log4j2
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {


    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("")
    public String hello() {
        return "hello";
    }

    //삭제
    @DeleteMapping("")
    public BaseResponse<String> delete(@RequestParam String email) {
        try{
            userService.delete(email);
            String result = email + " 해당 유저가 삭제되었습니다.";
            return new BaseResponse<>(result);

        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저 상태관리
    @PatchMapping("/status")
    public BaseResponse<String> UserStatus(@RequestParam String email) {
        try{
            userService.UserStatus(email);
            UserInfoDto userInfoDto  = userService.GetUser(email);
            String status = userInfoDto.getUserStatus();
            String result = email + " 해당 유저가 " + status + " 되었습니다.";
            return new BaseResponse<>(result);

        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저 정보 조회
    @GetMapping("/info")
    public BaseResponse<UserInfoDto> GetUser(@RequestParam String email) {
        try{
            UserInfoDto userInfoDto = userService.GetUser(email);

            return new BaseResponse<>(userInfoDto);

        }
        catch (BaseException exception) {
            System.out.println("error");
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    //아이디 찾기
    @GetMapping("/getEmail")
    public BaseResponse<String> GetUserEmail(HttpServletRequest request, @RequestParam String phone){
        try{
            UserInfoDto userInfoDto = userService.GetUserByPhone(phone);
            String result =  userInfoDto.getEmail();
            return  new BaseResponse<>(result);

        }
        catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/updatePw")
    public BaseResponse<String> ModifyPw(@RequestParam String phone, String password){
        try {
            userService.ModifyPw(phone,password);
            UserInfoDto userInfoDto  = userService.GetUserByPhone(phone);
            String result = "새 비밀번호 : " + userInfoDto.getPassword();
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    //jwt 토근 헤더에서 가져와 사용자 이메일 조회
    @ResponseBody
    @GetMapping("/userInfo")
    public BaseResponse<Integer> getUserInfoByJwt(HttpServletRequest request) throws BaseException {
        int userId = userService.GetHeaderAndGetUser(request);
        return new BaseResponse<>(userId);
    }

    /*
    // 이메일 보내기
    @Transactional
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("memberEmail") String memberEmail){
        MailDTO dto = ms.createMailAndChangePassword(memberEmail);
        ms.mailSend(dto);

        return "/member/login";
    }

*/

}