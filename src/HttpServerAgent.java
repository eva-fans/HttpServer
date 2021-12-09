import java.net.*;
import java.util.Map.Entry;
import java.util.regex.*;
import java.io.*;
public class HttpServerAgent extends Thread{
    private Socket socket;
    public HttpServerAgent(Socket socket){
        this.socket=socket;
    }
    public void run(){
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedOutputStream output=new BufferedOutputStream(socket.getOutputStream());
        Page page=new Page();
        String request_pattern_string="([a-zA-Z]+) (/[\\w.]+) ([a-zA-Z]+)/([0-9].[0-9])";
        Pattern request_pattern=Pattern.compile(request_pattern_string);
        String header_pattern_string="(\\w+): (\\w+)";
        Pattern header_pattern=Pattern.compile(header_pattern_string);

        HttpRequest request=new HttpRequest();
        String line=reader.readLine();
        Matcher request_matcher=request_pattern.matcher(line);
        while(request_matcher.find()){
                request.setMethod(request_matcher.group(0));
                request.setUri(request_matcher.group(1));
                request.setProtocol(request_matcher.group(2));
                request.setVersion(request_matcher.group(3));
            line=reader.readLine();
            Matcher header_matcher=header_pattern.matcher(line);
            while(header_matcher.find()){
                request.setHeader(header_matcher.group(0),header_matcher.group(1));
            }
            HttpResponse response=new HttpResponse();
            response.setProtocol("HTTP");
            response.setReasonPhrase("OK");
            response.setVersion("1.1");
            response.setHeader("Server", "utulsaServer/1.0");
            if(request.getMethod().equals("GET")){
                page.get(request,response);
            }
            else if(request.getMethod().equals("HEAD")){
                page.head(request, response);
            }
            else{
                response.setStatusCode(501);
            }
            String response_string=response.getProtocol()+"/"+response.getVersion()+" ";
            response_string+=response.getStatusCode()+" ";
            response_string+=response.getReasonPhrase()+" \r\n";
            for(Entry<String,String> entry:response.getHeaderSet()){
                response_string+=entry.getKey()+": "+entry.getValue()+"\r\n";
            }
            response_string+="\r\n";
            writer.write(response_string);

            if(response.getData()!=null){
                output.write(response.getData());
            }
            
            request=new HttpRequest();
            line=reader.readLine();
            request_matcher=request_pattern.matcher(line);
        }
            socket.close();
            reader.close();
            writer.close();
            output.close();
        } catch (IOException e) {

        }
    }
}
