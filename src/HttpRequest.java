import java.util.Hashtable;

public class HttpRequest{
    private String method;
    private String uri;
    private String protocol;
    private String version;
    private Hashtable<String,String> header;
    public HttpRequest(){
        header=new Hashtable<String,String>();
    }
    public void setMethod(String method){
	    this.method=method;
    }
    public void setUri(String URI){
	    this.uri=URI;
    }
    public void setProtocol(String protocol){
	    this.protocol=protocol;
    }
    public void setVersion(String version){
	    this.version=version;
    }
    public void setHeader(String header,String value){
	    this.header.put(header,value);
    }
    public String getMethod(){
	    return method;
    }
    public String getUri(){
	    return uri;
    }
    public String getProtocol(){
	    return protocol;
    }
    public String getVersion(){
	    return version;
    }
    public String getHeader(String header){
	    return this.header.get(header);
    }
}
