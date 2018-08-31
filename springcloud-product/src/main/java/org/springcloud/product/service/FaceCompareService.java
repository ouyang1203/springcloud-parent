package org.springcloud.product.service;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.opencv_core.CvHistogram;
import org.bytedeco.javacpp.opencv_core.IplImage;

import static org.bytedeco.javacpp.helper.opencv_imgproc.cvCalcHist;
import static org.bytedeco.javacpp.opencv_core.CV_HIST_ARRAY;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.CV_COMP_CORREL;
import static org.bytedeco.javacpp.opencv_imgproc.cvCompareHist;
import static org.bytedeco.javacpp.opencv_imgproc.cvNormalizeHist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 基于javacv的图片对比
 * */
public class FaceCompareService {
	private static Logger log_ = LoggerFactory.getLogger(FaceCompareService.class);
	
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		log_.info("compare start");
		try {
//			grayImage("D:/java/javacv/test/cyx1.jpg");
//			double result = CmpPic("D:/java/javacv/test/hx1.png","D:/java/javacv/test/hx2.png");
//			double result = CmpPic("D:/java/javacv/test/ldh1.jpg","D:/java/javacv/test/ldh3.jpg");
//			double result = CmpPic("D:/java/javacv/test/cyx1.jpg","D:/java/javacv/test/cyx2.jpg");
			double result = CmpPic("D:/java/javacv/test/zj1.jpg","D:/java/javacv/test/zj2.jpg");
			log_.info("compare result is {}",result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis();
		log_.info("compare end ,total cost time is {} secends",(t2-t1)/1000);
	}
	
	/**
     * 特征对比
     * 对比的两张图片必须是灰度图
     * @param file1 人脸特征
     * @param file2 人脸特征
     * @return 相似度
     */
	 public static double CmpPic(String file1, String file2) throws Exception{
		 int l_bins = 20;
	     int hist_size[] = {l_bins};

	     float v_ranges[] = {0, 100};
	     float ranges[][] = {v_ranges};
	     
	     String newfile1 = grayImage(file1);
	     String newfile2 = grayImage(file2);
	     
	     IplImage Image1 = cvLoadImage(newfile1, CV_LOAD_IMAGE_GRAYSCALE);
         IplImage Image2 = cvLoadImage(newfile2, CV_LOAD_IMAGE_GRAYSCALE);
 
         IplImage imageArr1[] = {Image1};
         IplImage imageArr2[] = {Image2};

         CvHistogram Histogram1 = CvHistogram.create(1, hist_size, CV_HIST_ARRAY, ranges, 1);
         CvHistogram Histogram2 = CvHistogram.create(1, hist_size, CV_HIST_ARRAY, ranges, 1);

         cvCalcHist(imageArr1, Histogram1, 0, null);
         cvCalcHist(imageArr2, Histogram2, 0, null);

         cvNormalizeHist(Histogram1, 100.0);
         cvNormalizeHist(Histogram2, 100.0);

         return cvCompareHist(Histogram1, Histogram2, CV_COMP_CORREL);
	 }
	 
	 /**
	  * 将图片变成灰度图,并返回灰度图全路径
	  * @param sourceFile
	  * @return targetFile
	  * */
	 public static String grayImage(String filePath)throws Exception{
		 String newfile = "";
		 File file = new File(filePath);
		 String fileName = file.getName();
		 log_.info("old file name is {}",fileName);
		 //拼接新文件名
		 newfile = filePath.substring(0, filePath.lastIndexOf("/"))+"/gray/"+fileName;
		 log_.info("new file name is {}",newfile);
		 File newFile = new File(newfile);
		 if(!newFile.exists()){
			 //灰度图不存在才去转换
			 BufferedImage image = ImageIO.read(file);
			 int width = image.getWidth();
			 int height = image.getHeight();
			 //重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
			 BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
			 
			 for(int i= 0 ; i < width ; i++){
				 for(int j = 0 ; j < height; j++){
					 int rgb = image.getRGB(i, j);
					 grayImage.setRGB(i, j, rgb);
				 }
			 }
			 
			 ImageIO.write(grayImage, "jpg", newFile);
		 }
		 return newfile;
	 }
}
