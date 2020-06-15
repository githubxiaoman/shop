package cxm.shop.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * httpclient的使用
 * httpClient模拟浏览器发送请求
 * @author	xiaoman
 * @Date 2020年2月10日下午7:54:05
 */
public class HttpClientTest {
	//使用方法,执行get请求
	public void doGet() throws Exception{
		//创建httpClient对象
		CloseableHttpClient httpclient=	HttpClients.createDefault();
		//创建一个GET对象
		HttpGet get=new HttpGet("http://www.baidu.com");
		//执行请求
		CloseableHttpResponse response = httpclient.execute(get);
		//取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"utf-8");
		//打印信息
		System.out.println("statusCode****:"+statusCode);
		
		System.out.println("string*****:"+string);
		//关闭httpClient
		response.close();
		httpclient.close();
	}
	//使用方法,执行get请求,添加参数
	public void doGetAddParam() throws Exception{
		//创建httpClient对象
		CloseableHttpClient httpclient=	HttpClients.createDefault();
		//创建一个GET对象
		URIBuilder builder=new URIBuilder("http://www.sogou.com/web");
		builder.addParameter("query","胡歌");
		HttpGet get=new HttpGet(builder.build());
		//执行请求
		CloseableHttpResponse response = httpclient.execute(get);
		//取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"utf-8");
		//打印信息
		System.out.println("statusCode****:"+statusCode);
		
		System.out.println("string*****:"+string);
		//关闭httpClient
		response.close();
		httpclient.close();
	}
	//使用方法,执行post请求
		public void doPost() throws Exception{
			//创建httpClient对象
			CloseableHttpClient httpclient=	HttpClients.createDefault();
			//创建一个GET对象
			HttpPost post=new HttpPost("http://localhost:8083/httpclient/post.html");
			//执行请求
			CloseableHttpResponse response = httpclient.execute(post);
			//取响应结果
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity,"utf-8");
			//打印信息
			System.out.println("string*****:"+string);
			//关闭httpClient
			response.close();
			httpclient.close();
		}
		//使用方法,执行post请求,添加参数
		public void doPostAddParam() throws Exception{
			//创建httpClient对象
			CloseableHttpClient httpclient=	HttpClients.createDefault();
			//创建一个GET对象
			HttpPost post=new HttpPost("http://localhost:8083/httpclient/post.html");
			//创建一个Entity.模拟一个表单
			List<NameValuePair> kvList=new ArrayList<>();
			kvList.add(new BasicNameValuePair("username","张三"));
			kvList.add(new BasicNameValuePair("password","123"));
			StringEntity entity=new UrlEncodedFormEntity(kvList,"utf-8");
			//设置请求内容
			post.setEntity(entity);
			
			//执行请求
			CloseableHttpResponse response = httpclient.execute(post);
			//取响应结果
			String string = EntityUtils.toString( response.getEntity());
			//打印信息
			System.out.println("string*****:"+string);
			//关闭httpClient
			response.close();
			httpclient.close();
		}
}
