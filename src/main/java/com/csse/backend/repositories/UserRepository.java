package com.csse.backend.repositories;

import com.csse.backend.domains.User;

public interface UserRepository {

    User getUserById(Long id);

}
