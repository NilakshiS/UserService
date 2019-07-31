package com.stackroute.Muzix.config;

import com.stackroute.Muzix.domain.User;
import com.stackroute.Muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.Muzix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//annotation for configuration class
@Configuration
//annotation to enable Swagger2
@EnableSwagger2
public class WebConfiguration {

    //UserService Dependency to save users
    private final UserService userService;

    //Autowired constructor to inject dependency
    @Autowired
    public WebConfiguration(UserService userService) {
        this.userService = userService;
    }

    //Event Listener called when Context Refreshed Event is called
    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent cfr) {
        try {
            //add users to database
            userService.saveUser(new User(1,"RM","BTS Rapper and Lead"));
            userService.saveUser(new User(2,"Suga","BTS Rapper, Producer and Lyricist"));
            userService.saveUser(new User(3,"J-Hope","BTS Rapper, Dancer, Producer and Lyricist"));
            //userService.saveUsersFromApi();
            System.out.println("Context Refreshed");
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }


    //Bean to enable H2 console
//    @Bean
//    ServletRegistrationBean h2ServletRegistration(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
//        registrationBean.addUrlMappings("/console/*");
//        return registrationBean;
//    }

    //Bean for docket api, used for swagger2 documnetation
    @Bean
    public Docket docketApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.stackroute.Muzix.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo()).tags(new Tag("User Controller", "Operations pertaining to Users in User Management"));
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("User Management REST API")
                .contact(new Contact("Nilakshi Sahai", "https://github.com/NilakshiS/", "nilakshi97sahai@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
