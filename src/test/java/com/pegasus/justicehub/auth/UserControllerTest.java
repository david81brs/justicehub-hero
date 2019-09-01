package com.pegasus.justicehub.auth;

import com.pegasus.justicehub.auth.controller.UserController;
import com.pegasus.justicehub.auth.model.Role;
import com.pegasus.justicehub.auth.model.User;
import com.pegasus.justicehub.auth.service.UserService;
import com.pegasus.justicehub.auth.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUser() throws Exception {
        User user = new User();
        Role role = new Role();
        Set<Role> roles = new HashSet<Role>();

        role.setName("ROLE_USER");
        user.setUsername("wilbor.valantine");
        user.setEnabled(true);
        user.setEmail("wilbor.valantine@goodtimes.com");
        roles.add(role);

        user.setRoles(roles);
        UserService mockUserService = new UserServiceImpl();
        mockUserService.save(user);
        given(mockUserService.findByUsername("wilbor.valantine")).willReturn(user);
        this.mockMvc.perform(get("/users/{id}(id=user.getId()"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("wilbor.valantine")));

    }
}
