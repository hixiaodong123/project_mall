package com.cskaoyan.mall.oss;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.cskaoyan.mall.bean.Storage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@ConfigurationProperties(prefix = "oss")
@Component
public class MyOssClient {
	private String bucket;//cskaoyan
	private String endpoint ;//oss-cn-beijing.aliyuncs.com
	private String accessKey ;//LTAI8EgTxlc4QQZr
	private String secret ;//RBDvSGZOR8DaUJxlLrW4Ed46RVnAkR

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String ossFileUpload(MultipartFile file) throws IOException {
		InputStream inputStream = file.getInputStream();
		long size = file.getSize();
		String contentType = file.getContentType();
		String name = file.getOriginalFilename();
		if (null == name) return null;
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//		uuid = "test" + name.substring(name.lastIndexOf("."));//测试专用，用户覆盖服务器端数据，避免造成太多测试数据
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(size);
		objectMetadata.setContentType(contentType);

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uuid, inputStream, objectMetadata);

		OSSClient ossClient = new OSSClient(endpoint, accessKey, secret);
		ossClient.putObject(putObjectRequest);
		return "http://" + bucket + "." + endpoint + "/" + uuid;
	}

	public Map<String, Object> localUpload(MultipartFile file) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		String name = file.getOriginalFilename();
		if (null == name) return null;
		String path = "d://images/";
		String uuid = UUID.randomUUID().toString().replaceAll("-", "")
				+ name.substring(name.lastIndexOf("."));
		File tempFile = new File(path, uuid);
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		try {
			file.transferTo(tempFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("key", uuid);

		map.put("url", path +  uuid);
		return map;
	}
}
