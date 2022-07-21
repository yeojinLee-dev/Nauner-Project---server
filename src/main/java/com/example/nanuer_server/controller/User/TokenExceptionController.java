package com.example.nanuer_server.controller.User;

import com.example.nanuer_server.config.BaseException;
import static com.example.nanuer_server.config.BaseResponseStatus.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenExceptionController {
    @GetMapping("/exception/entrypoint")
    public void entryPoint() throws BaseException {
        throw new BaseException(INVALID_USER_JWT);
    }

    @GetMapping("/exception/access")
    public void denied() throws BaseException {
        throw new BaseException(INVALID_USER_JWT);
    }
}