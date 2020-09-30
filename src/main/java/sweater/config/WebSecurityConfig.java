package sweater.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import sweater.service.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//включ. авторизацию
                .antMatchers("/", "/registration").permitAll() //для главной страницы ("/") разрешается полный доступ
                .anyRequest().authenticated() //для всех остальных запросов требуется авторизация
                      .and()
                .formLogin() //включается формлогин
                .loginPage("/login") //страница логина находится по пути "/login"
                .permitAll() //разрешаем этим пользоваться всем
                      .and()
                .logout() //разлогиниться
                .permitAll(); // разрешаем этим пользоваться всем
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
              //  .dataSource(dataSource) //нужен для того, чтобы менеджер мог ходить в бд и искать там пользователей и их роли
                .passwordEncoder(NoOpPasswordEncoder.getInstance()); //шифрует пароли

    }
}