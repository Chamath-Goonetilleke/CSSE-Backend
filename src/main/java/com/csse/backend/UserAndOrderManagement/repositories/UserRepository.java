package com.csse.backend.UserAndOrderManagement.repositories;

import com.csse.backend.UserAndOrderManagement.domains.User;

public interface UserRepository {

    User getUserById(Long id);

}
