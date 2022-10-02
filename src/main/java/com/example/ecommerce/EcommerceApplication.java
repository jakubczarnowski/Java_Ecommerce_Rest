package com.example.ecommerce;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.ERole;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.service.UserService;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {
    @Value("${ecommerce.app.adminLogin}")
    String username;
    @Value("${ecommerce.app.adminPassword}")
    String password;
    @Value("${ecommerce.app.adminEmail}")
    String email;

    public static void main(String[] args) {
        ApplicationContext appContext = SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(redirectConnector());
        return tomcat;
    }

    private Connector redirectConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

    // Add a root category
    @Bean
    public CommandLineRunner insertRootCategory(@Autowired CategoryRepository repository) {
        return (args) -> {
            try {
                repository.save(new Category("root", "rootCategory", 1));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
    }

    // Add User Roles
    @Bean
    public CommandLineRunner insertDefaultCategories(@Autowired RoleRepository repository) {
        return (args) -> {
            try {
                repository.save(new Role(ERole.ROLE_USER));
                repository.save(new Role(ERole.ROLE_ADMIN));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
    }

    // Add admin
    @Bean
    public CommandLineRunner insertAdminAccount(@Autowired RoleRepository roleRepository, UserService service) {
        return (args) -> {
            try {
                User user = new User(this.username, this.email, password, "Admin", "Admin");
                user.getRoles().add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
                service.createUser(user);
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        };
    }
}
