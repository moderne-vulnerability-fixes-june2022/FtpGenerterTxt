package com.hxgis.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import java.net.URLEncoder;

//异步请求
public class HttpRequest2 {
    public static void sendGet(String url) {
        try {

        //    url = URLEncoder.encode(url,"UTF-8");
            url =  url.replace(" ", "%20");

            CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
            httpclient.start();

            final CountDownLatch latch = new CountDownLatch(1);
            // final HttpGet request = new HttpGet("http://www.google.com.tw/");
            final HttpGet request = new HttpGet(url);

            System.out.println(" caller thread id is : " + Thread.currentThread().getId());

            httpclient.execute(request, new FutureCallback<HttpResponse>() {

                public void completed(final HttpResponse response) {
                    latch.countDown();
                    System.out.println(" callback thread id is : " + Thread.currentThread().getId());
                    System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                    try {
                        String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                        System.out.println(" response content is : " + content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                public void failed(final Exception ex) {
                    latch.countDown();
                    System.out.println(request.getRequestLine() + "->" + ex);
                    System.out.println(" callback thread id is : " + Thread.currentThread().getId());
                }

                public void cancelled() {
                    latch.countDown();
                    System.out.println(request.getRequestLine() + " cancelled");
                    System.out.println(" callback thread id is : " + Thread.currentThread().getId());
                }

            });
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                httpclient.close();
            } catch (IOException ignore) {

            }
        }
        catch (Exception exp)
        {
            int ddd = 1;
        }
    }
}
