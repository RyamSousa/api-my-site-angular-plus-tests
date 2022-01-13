package com.api.git.model;

import com.api.git.models.UserResponse;

public class UserResponseMother {

    public static UserResponse getUserResponse() {
        return new UserResponse(
                "name",
                "ryamsousa",
                "link1",
                "link2",
                "asdasd",
                "ryam@asd.com",
                "VILT",
                "link3",
                "Brasil",
                0
        );
    }
}
