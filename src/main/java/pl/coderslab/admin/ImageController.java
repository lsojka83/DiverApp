package pl.coderslab.admin;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE
        )
    public void getImage1(HttpServletResponse response,
                         @RequestParam(required = false) String uuid

    ) throws IOException {
            FileSystemResource imgFile = new FileSystemResource(System.getProperty("user.dir") + "\\images\\" + uuid + ".jpg");
//        System.out.println("!!!!p"+imgFile.getPath());
//            FileSystemResource imgFile = new FileSystemResource(System.getProperty("user.dir")+"\\images\\7d873bbd-7eea-441e-8233-fd20c70981c7.jpg");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }

    @RequestMapping(value = "/imagealt",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<InputStreamResource> getImage2(
            @RequestParam(required = false) String uuid
    ) throws IOException {

//        ClassPathResource imgFile = new ClassPathResource(System.getProperty("user.dir")+"\\images\\7d873bbd-7eea-441e-8233-fd20c70981c7.jpg");
        FileSystemResource imgFile = new FileSystemResource(System.getProperty("user.dir") + "\\images\\" + uuid + ".jpg");
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

}
