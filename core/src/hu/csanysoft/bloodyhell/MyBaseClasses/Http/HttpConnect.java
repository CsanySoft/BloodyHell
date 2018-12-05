package hu.csanysoft.bloodyhell.MyBaseClasses.Http;

import com.badlogic.gdx.Net;

/**
 * Created by tuskeb on 2017. 02. 12..
 */

class HttpConnect {


    final Net.HttpRequest httpRequest;

    HttpConnect(String Url) {
        httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Url);
        httpRequest.setTimeOut(2000);
    }

}
