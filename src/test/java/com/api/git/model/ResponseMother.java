package com.api.git.model;

import com.api.git.models.RepositoryResponse;
import com.api.git.models.UserResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseMother {

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

    public static List<RepositoryResponse> getRepositoryResponse(){
        return new ArrayList<RepositoryResponse>(
                Arrays.asList(
                    new RepositoryResponse(
                            "name",
                            "20/04/2000",
                            "false",
                            "asdasdasd",
                            "java"
                    ),
                    new RepositoryResponse(
                            "name 2",
                            "20/05/2000",
                            "false",
                            "asdadsadasdasdasd",
                            "angular"
                    )
                )
        );
    }
}
