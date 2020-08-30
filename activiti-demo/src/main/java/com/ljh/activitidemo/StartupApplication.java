package com.ljh.activitidemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.activitidemo.demo.EventNotificationCallUtil;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class StartupApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(StartupApplication.class, args);
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//
//    @Bean
//    public CommandLineRunner run1(){
//        return args -> Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
//    }

    //    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        HttpPost httpPost = new HttpPost("http://124.172.188.97/qa-changan-workflow-engine/workflow/procInstance/startProcInstanceByDefKey");
//
////        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
////        params.add(new BasicNameValuePair("processDefKey", "1"));
////        params.add(new BasicNameValuePair("businessKey", "0"));
////        params.add(new BasicNameValuePair("tenantId", "10"));
//
//        Map<String, Object> param = new HashMap<>();
//        param.put("processDefKey", "tbt");
//        param.put("businessKey", "111");
//        param.put("tenantId", "4e086791212649d79f30ec0527599aee");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        CloseableHttpResponse httpResponse = null;
//        try {
//            // 设置 HttpPost 参数
////            String json = objectMapper.writeValueAsString(param);
//            String json = "{\n" +
//                    "  \"businessKey\": \"111\",\n" +
//                    "  \"formParam\": {\n" +
//                    "    \"business_code\": \"tbt\"\n" +
//                    "  },\n" +
//                    "  \"processDefKey\": \"tbt\",\n" +
//                    "  \"tenantId\": \"4e086791212649d79f30ec0527599aee\",\n" +
//                    "  \"userId\": 432,\n" +
//                    "  \"variables\": {}\n" +
//                    "}";
//            StringEntity se = new StringEntity(json);
//            se.setContentEncoding("UTF-8");
//            se.setContentType("application/json");
//            httpPost.setEntity(se);
//            httpResponse = httpClient.execute(httpPost);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            // 输出请求结果
//            System.out.println(EntityUtils.toString(httpEntity));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (httpResponse != null) {
//                    httpResponse.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
