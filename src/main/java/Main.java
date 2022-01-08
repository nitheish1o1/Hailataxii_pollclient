import java.io.*;

import com.squareup.okhttp.*;
import org.w3c.dom.*;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Main {
    public static void main(String []args) throws IOException, ParserConfigurationException, SAXException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse(" application/xml");
        RequestBody body = RequestBody.create(mediaType, "<taxii_11:Poll_Request \r\n   " +
                " xmlns:taxii_11=\"http://taxii.mitre.org/messages/taxii_xml_binding-1.1\"\r\n    " +
                "message_id=\"42158\"\r\n   " +
                " collection_name=\"guest.Abuse_ch\">\r\n    " +
                "<taxii_11:Exclusive_Begin_Timestamp>2017-1-1T00:00:00Z</taxii_11:Exclusive_Begin_Timestamp>\r\n    " +
                "<taxii_11:Inclusive_End_Timestamp>2017-12-1T12:00:00Z</taxii_11:Inclusive_End_Timestamp>\r\n    " +
                "<taxii_11:Poll_Parameters allow_asynch=\"false\">\r\n        " +
                "<taxii_11:Response_Type>FULL</taxii_11:Response_Type>\r\n    " +
                "</taxii_11:Poll_Parameters>\r\n</taxii_11:Poll_Request>");


        Request request = new Request.Builder()
                .url("http://hailataxii.com/taxii-data")
                .method("POST", body)
                .addHeader("Host", " hailataxii.com")
                .addHeader("Proxy-Connection", " keep-alive")
                .addHeader("Content-Length", " 2702")
                .addHeader("X-TAXII-Content-Type", " urn:taxii.mitre.org:message:xml:1.1")
                .addHeader("X-TAXII-Accept", " urn:taxii.mitre.org:message:xml:1.1")
                .addHeader("User-Agent", " Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36")
                .addHeader("Content-Type", " application/xml")
                .addHeader("Accept", " application/xml")
                .addHeader("Cache-Control", " no-cache")
                .addHeader("X-TAXII-Services", " urn:taxii.mitre.org:services:1.1")
                .addHeader("X-TAXII-Protocol", " urn:taxii.mitre.org:protocol:http:1.0")
                .addHeader("Accept-Encoding", " gzip, deflate")
                .addHeader("Accept-Language", " en-US,en;q=0.8")
                .build();
        Response response = client.newCall(request).execute();
        //System.out.println(response.body().string());

        parser(response.body().string());

    }
    public static void parser(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
        String stix = parse.getDocumentElement().getTextContent();
        System.out.println(stix);
        }
}



