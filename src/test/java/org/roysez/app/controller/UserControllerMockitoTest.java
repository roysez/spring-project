package org.roysez.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.roysez.app.model.User;
import org.roysez.app.service.UserService;
import org.roysez.app.util.UserValidator;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author roysez
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerMockitoTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
                .setValidator(new UserValidator())
                .setViewResolvers(viewResolver())
                .setControllerAdvice(new ExceptionHandlingController())
                .build();
    }

    @Test
    public void getAllUsersPage() throws Exception {
        User first = new User();
        first.setFirstName("Sergiy");
        first.setLastName("Balukh");
        User second = new User();
        second.setFirstName("Diana");
        second.setLastName("Martin");

        when(userService.findAll()).thenReturn(Arrays.asList(first,second));

        mockMvc.perform(get("/users/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/users/users.jsp"))
                .andExpect(model().attribute("listOfAllUsers",hasSize(2)));

        verify(userService,times(1)).findAll();
        verifyNoMoreInteractions(userService);

    }

    /**
     * Test: if user not found, must be returned view with {@code modelAttribute } 'error'
     * @throws Exception
     */
    @Test
    public void getUserProfile() throws Exception {
        User emptyPointer = null;
        when(userService.findBySso("emptyUser")).thenReturn(emptyPointer);

        mockMvc.perform(get("/users/emptyUser"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/users/user_profile.jsp"))
                .andExpect(model().attribute("error",notNullValue()))
                .andExpect(model().attribute("user",nullValue()));

        verify(userService,times(1)).findBySso("emptyUser");
        verifyNoMoreInteractions(userService);
    }

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}