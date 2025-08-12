package org.example.scheduleapp.user.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.user.dto.UserGetAllResponse;
import org.example.scheduleapp.user.dto.UserSaveRequest;
import org.example.scheduleapp.user.dto.UserSaveResponse;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
