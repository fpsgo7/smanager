package com.yedam.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yedam.common.Control;

public class ImageDownLoad implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ServletInputStream sis = request.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		
		//"[{"imgSrc":"url","prdName": "상품"},.... => 자바 객체
		List<Map<String,String>> list
			= mapper.readValue(sis, //
					new TypeReference<List<Map<String,String>>>(){
			} );
		String dml ="";
		for(Map<String, String> map:list) {
			String imgSrc = map.get("imgSrc");
			String prdName = map.get("prdName");
			prdName.replace("&", "");
			String prodCode = map.get("prodCode");
			String prodPrice = map.get("prodPrice");
			prodPrice = prodPrice.replace(",", "");
			//fileCreate(imgSrc,prdName);
			dml += dataCreate(prodCode, prdName, prodPrice) + "\n";
		}
		System.out.println(dml);
		PrintWriter out = response.getWriter();
		out.print("{\"retCode\": \"Success\"}");
	}
	
	void fileCreate(String imgSrc, String prdName) throws IOException{
		String srcPath = imgSrc;
		String name = prdName.replaceAll("/", "");// /은 경로로 취급되기 때문에 주의!
		name = name.replace("*", "");
		URL url = new URL(srcPath);
		InputStream is = url.openStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		
		// 출력스트림
		FileOutputStream fos = new FileOutputStream("d:/temp/images/"+name+"imageBtWeb1.jpg");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		while(true) {
			int read = bis.read();
			if(read == -1)
				break;
			bos.write(read);
			
		}
		bos.flush();
		bos.close();
		fos.flush();
		fos.close();
		bis.close();
		is.close();
		System.out.println("완료");
	}
	// DB 사용할 sql 문 작성하기
	String dataCreate(String prodCode, String prodName, String prodPrice) {
		String sql = "insert into tbl_product (prod_code, prod_name, prod_price, prod_image)"
				+ "values( '"
				+ prodCode+"', '"
				+ prodName+"', "
				+ prodPrice+", '"
				+ prodName+".jpg' "
				+ ");";
		return sql;
	}

}
