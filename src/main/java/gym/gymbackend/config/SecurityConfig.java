package gym.gymbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    SecurityConfig(DataSource dataSource, JwtRequestFilter jwtRequestFilter) {
        this.dataSource = dataSource;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM person WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authority AS a WHERE username=?");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(POST, "/authenticate").permitAll()
                .antMatchers(GET,"/activities").authenticated()
                .antMatchers(GET, "/memberships").authenticated()
                .antMatchers(PATCH, "/persons/{^[\\w]$}/password").authenticated()
                .antMatchers(GET, "/workouts/person/{^[\\w]$}").authenticated()
                .antMatchers(GET,"/facilities").authenticated()
                .antMatchers("/persons/{^[\\w]$}/authorities").hasRole("ADMIN")
                .antMatchers("/employees/**").hasRole("ADMIN")
                .antMatchers("/memberships/**").hasRole("ADMIN")
                .antMatchers("/persons/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/subscriptions/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/facilities/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/exerciseMuscles/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/activities/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/workouts/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .anyRequest().denyAll()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
