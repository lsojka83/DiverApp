package pl.coderslab.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


public class FileOps {


    private final String serverDir = System.getProperty("user.dir");
    //    private final String serverDir = System.getProperty("catalina.base");
    String imagesFolder = serverDir + "\\images\\";
    String randomFileName;
    String filePath;
    Path path;
    File file;


    public boolean saveFile(MultipartFile multipartFile, String randomFileName) {

        filePath = imagesFolder + randomFileName + ".jpg";
        path = Paths.get(filePath);

        file = new File(filePath);

        File theDir = new File(imagesFolder);
        if(!theDir.exists())
        {
            theDir.mkdirs();
        }

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch (Exception e)
            {
                return false;
            }
        }

        try (
                OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public boolean deleteFile(String UUID)
    {
//        path = Paths.get(filePath);
//        file = new File(filePath);

//        file.delete();
        filePath = imagesFolder + UUID + ".jpg";
        path = Paths.get(filePath);
        try {
            Files.delete(path);
        }
        catch (Exception e)
        {
            System.out.println("E!!!"+e.toString());
        }

        return true;
    }

    public String getImagesFolder() {
        return imagesFolder;
    }


//    public Path getFilePath()
//    {
//
//        filePath = imagesFolder + randomFileName + ".jpg";
//        path = Paths.get(filePath);
//
//        return path;
//    }
}
