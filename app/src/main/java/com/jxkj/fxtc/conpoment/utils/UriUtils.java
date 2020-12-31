package com.jxkj.fxtc.conpoment.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UriUtils {

	/**
	 * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
	 * @param context
	 * @param imageUri
	 * @author liyeyu
	 * @date 2014-10-12
	 */
	@TargetApi(19)
	public static String getImageAbsolutePath(Activity context, Uri imageUri) {
		if (context == null || imageUri == null)
			return null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
			if (isExternalStorageDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				}
			} else if (isDownloadsDocument(imageUri)) {
				String id = DocumentsContract.getDocumentId(imageUri);
				Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
				return getDataColumn(context, contentUri, null, null);
			} else if (isMediaDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				String selection = MediaStore.Images.Media._ID + "=?";
				String[] selectionArgs = new String[] { split[1] };
				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		} // MediaStore (and general)
		else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
			// Return the remote address
			if (isGooglePhotosUri(imageUri))
				return imageUri.getLastPathSegment();
			return getDataColumn(context, imageUri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
			return imageUri.getPath();
		}
		return null;
	}

	public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
		Cursor cursor = null;
		String column = MediaStore.Images.Media.DATA;
		String[] projection = { column };
		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}
	
	/**
	 * 根据需求的宽和高以及图片实际的宽和高计算SampleSize
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int caculateInSampleSize(Options options, int reqWidth,
                                           int reqHeight)
	{
		int width = options.outWidth;
		int height = options.outHeight;

		int inSampleSize = 1;

		if (width > reqWidth || height > reqHeight)
		{
			int widthRadio = Math.round(width * 1.0f / reqWidth);
			int heightRadio = Math.round(height * 1.0f / reqHeight);

			inSampleSize = Math.max(widthRadio, heightRadio);
		}

		return inSampleSize;
	}
	
	/**
	 * 根据图片的质量进行压缩
	 * 
	 * @param image
	 * @param size
	 *            质量压缩的大小阈值
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image, int size) {
		ByteArrayInputStream isBm = (ByteArrayInputStream) compressImageBackInputStream(
				image, size);
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}
	
	/**
	 * 按质量压缩图片返回一个InputStream
	 * 
	 * @param image
	 * @param size
	 *            质量压缩的大小阈值
	 * @return
	 */
	public static InputStream compressImageBackInputStream(Bitmap image,
                                                           int size) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > size) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			options -= 10;// 每次都减少10
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

		}
		System.out.println(baos.toByteArray().length / 1024);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		return is;
	}
	
	public static Bitmap GetRightOritationNew(String filePath){
		//		Bitmap bitmap  = BitmapFactory.decodeFile(filePath);	

		//优化内存溢出方法1
		Options options = new Options();
//		options.inJustDecodeBounds = true;  
//		BitmapFactory.decodeFile(filePath, options);  
		
//		options.inSampleSize = UriUtils.caculateInSampleSize(options, options.outWidth, options.outHeight);//图片大小，设置越大，图片越不清晰，占用空间越小  
		// 使用获得到的InSampleSize再次解析图片  
		options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		Bitmap bitmap = UriUtils.compressImage(BitmapFactory.decodeFile(filePath, options), 100);
//		ExifInterface exif = null;	
//		try{
//			exif = new ExifInterface(filePath);  
//		}catch (IOException e) {  
//			e.printStackTrace();  
//			Log.e(TAG,e.toString());
//			exif = null;  
//			return bitmap;
//		}			
//		int degree=0;
//		if (exif != null) { 
//			int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,  
//					ExifInterface.ORIENTATION_UNDEFINED);  
//
//			switch (ori) {  
//			case ExifInterface.ORIENTATION_ROTATE_90:  
//				degree = 90;  
//				break;  
//			case ExifInterface.ORIENTATION_ROTATE_180:  
//				degree = 180;  
//				break;  
//			case ExifInterface.ORIENTATION_ROTATE_270:  
//				degree = 270;  
//				break;  
//			default:  
//				degree = 0;  
//				break;  
//			}		    
//		}		
//		if (degree != 0) {  
//			// 旋转图片  
//			Matrix m = new Matrix();    
//			m.setRotate(degree, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);    
//			try {    
//				if(degree==180){
//					
//					Bitmap b2 = Bitmap.createBitmap(    
//							bitmap, 0, 0, 300, 150, m, true);    
//					if (bitmap != b2) {    
//						bitmap.recycle();  //Bitmap操作完应该显示的释放  
//						System.gc();
//						bitmap = b2;    
//					}    
//				}else{
//					Bitmap b2 = Bitmap.createBitmap(    
//							bitmap, 0, 0, 150, 300, m, true);    
//					if (bitmap != b2) {    
//						bitmap.recycle();  //Bitmap操作完应该显示的释放  
//						System.gc();
//						bitmap = b2;    
//					}   
//				}
//			} catch (OutOfMemoryError ex) {    
//				// 建议大家如何出现了内存不足异常，最好return 原始的bitmap对象。.
//				return bitmap;	
//			}    
//		}		
		return bitmap;		
	}
	
	public static int GetRightOritation(String filePath){
		ExifInterface exif = null;
		try{
			exif = new ExifInterface(filePath);
		}catch (IOException e) {
			e.printStackTrace();  
			exif = null;  
			return 0;
		}			
		int degree=0;
		if (exif != null) { 
			int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_UNDEFINED);

			switch (ori) {  
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;  
				break;  
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;  
				break;  
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;  
				break;  
			default:  
				degree = 0;  
				break;  
			}		    
		}		
		return degree;
	}
	/**
	 * 检查是否存在SDCard
	 *
	 * @return
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static JSONObject getUserInfo() throws JSONException {
		if(StringUtil.isNotBlank(SharedUtils.singleton().get("userInfo", ""))){
			JSONObject jsonObject = new JSONObject(SharedUtils.singleton().get("userInfo", ""));
			return jsonObject;
		}
		return null;
	}
}
