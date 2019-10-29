package net.ruixinglong.suntv.provider;

import net.ruixinglong.suntv.entity.UserEntity;
import net.ruixinglong.suntv.service.UserService;
import org.apache.catalina.User;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserEntityGroupSequenceProvider implements DefaultGroupSequenceProvider<UserEntity> {

    @Resource
    private UserService userService;

    @Override
    public List<Class<?>> getValidationGroups(UserEntity userEntity) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(UserEntity.class);
        return defaultGroupSequence;
    }
}
