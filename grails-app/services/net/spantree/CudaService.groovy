package net.spantree

import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

class CudaService {

    def authenticateCudaUser(String assoc_handle, String sig, String response_nonce) {
        HttpClient httpClient = new DefaultHttpClient()
        def url = 'http://localhost:8080/cas/openid';
        def user = "http://localhost:8080/cas/openid/cedric@spantree.net";
        def post = new HttpPost(url)

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>()
        nameValuePairs.add(new BasicNameValuePair("openid.signed", "op_endpoint,claimed_id,identity,return_to,response_nonce,assoc_handle"))
        nameValuePairs.add(new BasicNameValuePair("openid.sig", sig))
        nameValuePairs.add(new BasicNameValuePair("openid.ns", "http://specs.openid.net/auth/2.0"))
        nameValuePairs.add(new BasicNameValuePair("openid.identity", user))
        nameValuePairs.add(new BasicNameValuePair("openid.response_nonce", response_nonce))
        nameValuePairs.add(new BasicNameValuePair("openid.claimed_id", user))
        nameValuePairs.add(new BasicNameValuePair("openid.mode", "check_authentication"))
        nameValuePairs.add(new BasicNameValuePair("openid.assoc_handle", assoc_handle))
        nameValuePairs.add(new BasicNameValuePair("openid.return_to", "http://localhost:9000/SimpleRequest/auth"))

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs))

        def resp = httpClient.execute(post)

        // reponse will include a true or false boolean if login was validated by CUDA
        log.info resp
    }
}
