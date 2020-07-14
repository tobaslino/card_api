package com.tobias;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.transaction.Transactional;

import com.tobias.entity.Usuario;
import com.tobias.resource.UserResource;

@QuarkusTest
public class ExampleResourceTest {
    @Test
    @Transactional
    public void testCreateNewUser() {
        var n = Usuario.build("tobias");
        var ur = new UserResource();
        ur.create(n);
    }
}