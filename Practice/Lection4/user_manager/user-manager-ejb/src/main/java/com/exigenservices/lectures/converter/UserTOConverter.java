package com.exigenservices.lectures.converter;

import com.exigenservices.lectures.domain.User;
import com.exigenservices.lectures.model.UserTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bolbin on 23.11.2014.
 */
public class UserTOConverter {

    public UserTO convert(User user) {
        return new UserTO(user.getId(),
                          user.getName(),
                          user.getDateOfBirth());
    }

    public List<UserTO> convertAll(List<User> usersList) {
        List<UserTO> result = new ArrayList<UserTO>();
        for(User user : usersList) {
            result.add(convert(user));
        }
        return result;
    }
}
