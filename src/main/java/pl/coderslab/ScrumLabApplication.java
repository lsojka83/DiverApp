package pl.coderslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import pl.coderslab.model.FileOps;

@SpringBootApplication
@ServletComponentScan
public class ScrumLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrumLabApplication.class, args);

    }

//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(20000000);
//        return multipartResolver;
//    }

    @Bean(name = "fileOps")
    public FileOps fileOps()
    {
        FileOps fileOps = new FileOps();
        return fileOps;
    }

}
