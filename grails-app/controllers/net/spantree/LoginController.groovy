package net.spantree

class LoginController {
    CudaService cudaService

    def login() {
        render view:'login'
    }

    def cuda = {

        String realm = "http%3A%2F%2Flocalhost%3A9000%2F" // Set this to your SimpleRequest location
        String user = "http%3A%2F%2Flocalhost%3A8080%2Fcas%2Fopenid%2Fcedric@spantree.net" // User to be logged in

        String url ="http://localhost:8080/cas/" +
                "?openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0" +
                "&openid.claimed_id=" + user +
                "&openid.identity=" + user +
                "&openid.return_to=" + realm + "SimpleRequest%2Fauth" +
                "&openid.realm=" + realm +
                "&openid.mode=checkid_setup"

        redirect(url: url)
    }

    def auth = {
        // uncomment to skip extra "click" step
        // cudaService.authenticateCudaUser(params.openid.assoc_handle, params.openid.sig, params.openid.response_nonce);
        // render 'auth complete'
        render view:'auth'
    }

    def auth2 = {
        cudaService.authenticateCudaUser("123", "", "123"); // invalid request
//        cudaService.authenticateCudaUser(params.openid.assoc_handle, params.openid.sig, params.openid.response_nonce);
        render 'User has been authenticated'
    }

}
