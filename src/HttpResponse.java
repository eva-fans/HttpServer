import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;
public class HttpResponse{
    private String protocol;
    private String version;
    private int statusCode;
    private String reasonPhrase;
    private Hashtable<String,String> header;
    private byte[] data;
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    public void setProtocol(String protocol){
	this.protocol=protocol;
    }
    public Set<Entry<String,String>> getHeaderSet(){
        return header.entrySet();
    }
    public void setVersion(String version){
	this.version=version;
    }
    public void setStatusCode(int statusCode){
	this.statusCode=statusCode;
    }
    public void setReasonPhrase(String reasonPhrase){
	this.reasonPhrase=reasonPhrase;
    }
    public void setHeader(String header,String value){
	this.header.put(header,value);
    }
    public String getProtocol(){
	return protocol;
    }
    public String getVersion(){
	return version;
    }
    public int getStatusCode(){
	return statusCode;
    }
    public String getReasonPhrase(){
	return reasonPhrase;
    }
    public String getHeader(String header){
	return this.header.get(header);
    }
}
