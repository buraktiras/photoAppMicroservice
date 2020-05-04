package com.appsdeveloperblog.photoApp.api.users.service;

import com.appsdeveloperblog.photoApp.api.users.data.UserEntity;
import com.appsdeveloperblog.photoApp.api.users.data.UsersRepository;
import com.appsdeveloperblog.photoApp.api.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        userEntity.setEncryptedPassword("testEncryptedPassword");
        usersRepository.save(userEntity);
        return null;
    }

}
