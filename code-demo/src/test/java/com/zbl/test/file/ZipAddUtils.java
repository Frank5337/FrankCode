package com.zbl.test.file;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 向zip压缩包中追加文件
 * @author renguochao
 *
 */
@Component
@SuppressWarnings("all")
public class ZipAddUtils {

	/**
	 * 参数1：原始路径
	 * 参数2：.zip结束后的路径：例：D:\\temp\\playbook_1936_拍照商品照片.zip\\11\\22。此时11\\22\\文件名称就是参数2
	 * 参数3：固定写死的zip路径
	 * @author renguochao
	 * @create 2019年8月5日
	 * @param args
	 */
//	public static void main(String[] args) {
//		String zipUrl="D:/temp/playbook_1936_拍照商品照片.zip";
//		String fromUrl="C:/图片/000.png";
//		String toUrl="33/44/000.png";
//		try {
//			boolean result = add(zipUrl,fromUrl ,toUrl);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
	public static boolean add(String zipUrl,String fromUrl,String toUrl) throws IOException {
		Map <String,String> env = new HashMap <>(); 
	    env.put("create","true");
	    //使用语法定位文件系统 
	    //在java.net.JarURLConnection中定义
	    Path path = Paths.get(zipUrl);
	    URI uri = URI.create("jar:"+path.toUri());
	   try(FileSystem zipfs = FileSystems.newFileSystem(uri,env)){
	        Path externalTxtFile = Paths.get(fromUrl);
	        Path pathInZipfile = zipfs.getPath(toUrl);
	        //将文件复制到zip文件中
			Files.copy(externalTxtFile,pathInZipfile,StandardCopyOption.REPLACE_EXISTING);
			return true;
	    }catch (Exception e) {
	    	return false;
		}
	}
}
