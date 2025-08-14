package org.example.scheduleapp.user.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.scheduleapp.schedule.dto.ScheduleGetAllResponse;
import org.example.scheduleapp.user.dto.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserSaveResponse saveUser(UserSaveRequest request) {
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        User savedUser = userRepository.save(user);
        return new UserSaveResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<UserGetAllResponse> findAll() {
        List<User> users = userRepository.findAll();
        List<UserGetAllResponse> userGetAllResponses = new ArrayList<>();

        for (User user : users) {
            userGetAllResponses.add(new UserGetAllResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            ));
        }
        return userGetAllResponses;
    }

    @Transactional(readOnly = true)
    public UserGetResponse findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("해당 id의 유저를 찾을 수 없습니다.")
        );
        return new UserGetResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UserUpdateResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("해당 id의 유저를 찾을 수 없습니다.")
        );

        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getUsername())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 더티체킹
        user.updateUsernameEmail(
                request.getUsername(),
                request.getEmail()
        );
        return new UserUpdateResponse(
                user.getUsername(),
                user.getEmail()
        );
    }

    @Transactional
    public void deleteOne(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("해당 id의 유저를 찾을 수 없습니다.")
        );

        if (!ObjectUtils.nullSafeEquals(user.getPassword(), password)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.deleteById(userId);
    }
}
