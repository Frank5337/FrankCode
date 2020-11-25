//package com.zbl.test.file;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.List;
//
//@Service
//public class BatchTaskFeedBackExportServiceImpl implements BatchTaskFeedBackExportService {
//
//	private Logger log = LoggerFactory.getLogger(BatchTaskFeedBackExportServiceImpl.class);
//
//	@Autowired
//	private TaskStoreBatchItemsService storeBatchItemsService;
//
//	@Autowired
//	private ServiceConfig serviceConfig;
//
//	@Override
//	public ResultVO<?> createPhotoZip(Long taskId,List<BatchExportPhotoDTO> exportPhotoList,String randomStr) {
//		log.info("BatchTaskFeedBackExportServiceImpl ----> createPhotoZip 【START】");
//		log.info("REQUEST taskId:【{}】",taskId);
//
//		if(taskId == null || exportPhotoList == null || StringUtils.isBlank(randomStr)) {
//			log.error("【文件打包】【失败】生成ZIP文件，传入参数为空  taskId:【{}】",taskId);
//			log.info("BatchTaskFeedBackExportServiceImpl <---- createPhotoZip 【E N D】");
//			return ResultVOUtil.error(ErrorEnum.IS_CHECK_WRITE_IS_NULL);
//		}
//
//		//文件夹目录
//		String takephotoFeedbackPath = serviceConfig.getTakephotoFeedbackPath();
//		//压缩文件夹后目录
//		String takephotozipFeedbackPath = serviceConfig.getTakephotozipFeedbackPath();
//		//照片存储路径
//		String uploadFeedbackPath = serviceConfig.getUploadFeedbackPath();
//
//		String zipName = null;
//		String tozip = null;
//		try {
//			BatchExportPhotoDTO batchExportPhotoDTO = null;
//			String dirPath = null;
//			File file = null;
//			StringBuffer sbf = new StringBuffer(takephotoFeedbackPath);
//
//			//创建根目录
//			File rootFile = new File(sbf.append(randomStr).append("/").append(taskId).toString());
//			if(!rootFile.exists() || !rootFile.isDirectory()) {
//				rootFile.mkdirs();
//			}
//
//
//			for(int cnt =0;cnt<exportPhotoList.size();cnt ++) {
//				batchExportPhotoDTO = exportPhotoList.get(cnt);
//				sbf.setLength(0);
//				//创建各文件目录
//				dirPath = sbf.append(takephotoFeedbackPath).append(randomStr).append("/").append(taskId).append("/").append(batchExportPhotoDTO.getClubId()).append("/").toString();
//				file =   File(dirPath);
//				if(!file.exists() || !file.isDirectory()) {
//					file.mkdirs();
//				}
//			}
//			log.info("【文件打包】文件夹创建成功,taskId:{},path:{}",taskId,dirPath);
//
//			int num23=(int)((Math.random()*9+1)*100000);
//			String valueOf23 = String.valueOf(num23);
//
//			//压缩完成zip名称
//			zipName=taskId+"_"+valueOf23+"_PicturesGoods.zip";
//
//			log.info("【文件打包】taskId:{},生成zip文件名称为【{}】",taskId,zipName);
//			//生成如上路径文件夹的ZIP文件
//			String srcDir = takephotoFeedbackPath + randomStr + "/" + taskId;
//			tozip = takephotozipFeedbackPath + zipName;
//			FileOutputStream fos1= new FileOutputStream(new File(tozip));
//			ZipUtils.toZip(srcDir, fos1,true);
//			log.info("【文件打包】taskId:{},zip文件生成成功,zipName:【{}】",taskId,zipName);
//
//			//往压缩文件包中传输图片
//			List<String> photoList = null;
//			//图片存储路径(绝对路径)
//			String picturePath = null;
//			//图片存储路径(相对路径)
//			String pictureUrl = null;
//
//			for(int cnt =0;cnt<exportPhotoList.size();cnt ++) {
//				batchExportPhotoDTO = exportPhotoList.get(cnt);
//				photoList = batchExportPhotoDTO.getPhotoList();
//				if(photoList != null && !photoList.isEmpty()) {
//					for (int num =0;num < photoList.size();num ++) {
//						pictureUrl = photoList.get(num);
//						picturePath = uploadFeedbackPath + pictureUrl;
//						//将文件拷贝到zip文件中
//						ZipAddUtils.add(tozip, picturePath, pictureUrl);
//					}
//				}
//			}
//			log.info("【文件打包】taskId:{},文件输入至zip文件完成,zipName:【{}】",taskId,zipName);
//		} catch (Exception e) {
//			log.info("【文件打包】【失败】taskId:{},exception:",taskId,e.toString());
//			log.info("BatchTaskFeedBackExportServiceImpl <---- createPhotoZip 【E N D】");
//			log.error("【文件打包】【失败】taskId:{}反馈图片生成zip文件异常,exception:{}",taskId,e);
//			return ResultVOUtil.error(ErrorEnum.EXPORT_SYSTEM_EXCEPTION,e.toString(),tozip);
//		}
//
//		log.info("【文件打包成功】taskId:【{}】",taskId);
//		log.info("BatchTaskFeedBackExportServiceImpl <---- createPhotoZip 【E N D】");
//		return ResultVOUtil.success(zipName);
//	}
//
//
//}
