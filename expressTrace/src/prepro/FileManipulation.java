package prepro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;

import po.Trace;

public class FileManipulation implements IFile {

	@Override
	public boolean WriteJson(String filename, String content) {
		// TODO Auto-generated method stub
		File file = new File("D:/junior/expressTrace/WebRoot/json/" + filename);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		FileWriter fw;
		
		try {
			fw = new FileWriter(file);
			System.out.println(filename+": "+fw.getEncoding());
			BufferedWriter out = new BufferedWriter(fw);
			out.write(content);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public String readJson(String name) {
		// TODO Auto-generated method stub
		String content = "";
		File file = new File("D:/junior/expressTrace/WebRoot/json/" + name);
		try {
			content = FileUtils.readFileToString(file, "gb2312");
//			System.out.println(name+": " + content);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}
	
	
	public String readJson(String name,String encoding) {
		// TODO Auto-generated method stub
		String content = "";
		File file = new File("D:/junior/expressTrace/WebRoot/json/" + name);
		try {
			content = FileUtils.readFileToString(file, encoding);
			System.out.println(name+":" + content);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

	public String readAjax(HttpServletRequest request) {

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line = null;
		StringBuilder sb = new StringBuilder();
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = sb.toString();
		return data;
	}

}
