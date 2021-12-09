import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Page{
    public void get(HttpRequest request, HttpResponse response){
        
        File file=new File(System.getProperty("user.dir")+request.getUri());
        try{
            FileInputStream input=new FileInputStream(file);
            byte[] bytes=input.readAllBytes();
            response.setData(bytes);
            input.close();
        }catch(FileNotFoundException e){
            response.setStatusCode(404);
        } catch (IOException e) {
            response.setStatusCode(400);
        }
        String fileName=file.getName();
        if(fileName.endsWith(".html") || fileName.endsWith(".htm")){
            response.setHeader("Content-Type","text/html");
        }
        else if(fileName.endsWith(".gif")){
            response.setHeader("Content-Type","image/gif");
        }
        else if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")){
            response.setHeader("Content-Type","image/jpeg");
        }
        else if(fileName.endsWith(".pdf")){
            response.setHeader("Content-Type","application/pdf");
        }
    }
    public void head(HttpRequest request, HttpResponse response){
        response.setHeader("Content-Length", "0");
        response.setHeader("Content-Type","html");
    }
}
