package com.example.nanuer_server.service.heart;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponseStatus;
import com.example.nanuer_server.domain.entity.HeartEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.HeartRepository;
import com.example.nanuer_server.domain.repository.PostRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.heart.AddHeartDto;
import com.example.nanuer_server.dto.heart.HeartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 게시물이 삭제되면 heart도 같이 삭제
@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;

    private  final PostRepository postRepository;

    private final UserRepository userRepository;

    //
    public HeartDto addHeart(AddHeartDto addheartDto) throws BaseException {
        if(postRepository.findByPostId(addheartDto.getPostId()).getPostStatus() ==0){
            throw new BaseException(BaseResponseStatus.USER_USER_EMPTY_USER);
        }

        UserEntity userEntity = userRepository.getReferenceById(addheartDto.getUserId());
        PostEntity postEntity = postRepository.getReferenceById(addheartDto.getPostId());

        addheartDto.setUserEntity(userEntity);
        addheartDto.setPostEntity(postEntity);

        HeartEntity heartEntity = addheartDto.toEntity();

        PostEntity heartPostEntity = heartEntity.getPostEntity();
        heartPostEntity.setHeartCount(heartPostEntity.getHeartCount()+1);
        heartRepository.save(heartEntity);
        return heartEntity.toDto();
    }

    public void deleteHeart(int heartId) throws BaseException {
       HeartEntity heartEntity = heartRepository.findById(heartId).get();
       if(heartEntity.getPostEntity().getPostStatus()==0){
           throw new BaseException(BaseResponseStatus.POST_POST_EMPTY_POST);
       }
       PostEntity postEntity = heartEntity.getPostEntity();
       postEntity.setHeartCount(postEntity.getHeartCount()-1);
       heartRepository.delete(heartEntity);
    }

}
