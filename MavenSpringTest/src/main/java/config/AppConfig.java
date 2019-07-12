package config;

import com.flyex.HelloWorldService;
import com.flyex.SpringHelloWorld;
import com.flyex.StrutsHelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.flyex")
public class AppConfig {
    /**@Bean
    public HelloWorldService helloWorldService(){
        return new HelloWorldService();
    }
    @Bean
    public SpringHelloWorld springHelloWorld(){
        return new SpringHelloWorld();
    }
    @Bean
    public StrutsHelloWorld strutsHelloWorld(){
        return new StrutsHelloWorld();
    }**/
}
