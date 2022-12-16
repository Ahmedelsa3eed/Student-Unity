package csed.swe.studentunity.config;


import csed.swe.studentunity.Logic.AdminCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminCheckInterceptor())
                .addPathPatterns("/accounts/**")
                .addPathPatterns("/tasks/**");
    }

}
