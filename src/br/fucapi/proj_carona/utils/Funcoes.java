package br.fucapi.proj_carona.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Funcoes {

	public static boolean validarDados(View view, String mensagem) {
		  if (view instanceof EditText) {
		   EditText edTexto = (EditText) view;
		   Editable texto = edTexto.getText();
		   if (texto != null) {
		    String strTexto = texto.toString();
		    if (!TextUtils.isEmpty(strTexto)) {
		     return true;
		    }
		   }
		   // em qualquer outra condição é gerado um erro
		   edTexto.setError(mensagem);
		   edTexto.setFocusable(true);
		   edTexto.requestFocus();
		   return false;
		  }
		  return false;
		 }
	
	 public static boolean validarDateFormat(View pView, String pDateFormat, String pMessage) {
			 if (pView instanceof EditText) {
				 EditText edText = (EditText) pView;
				 Editable text = edText.getText();
			 
				 if (text != null) {
					 String strText = text.toString();
					 
					 if (!TextUtils.isEmpty(strText)) {
						 
						 SimpleDateFormat format = new SimpleDateFormat(pDateFormat);						 						 
						 format.setLenient(false);
						 Calendar cal = Calendar.getInstance();
						 
						 try {							 
							 int currentYear = cal.get(Calendar.YEAR);
							 Log.d("TAG", "# thisYear : " + currentYear);
							 
							 cal.setTime(format.parse(strText));
							 
							 int fieldYear = cal.get(Calendar.YEAR);
							 
							 if(fieldYear>=currentYear){								 								 
								 edText.setError(pMessage);
								 edText.setFocusable(true);
								 edText.requestFocus();
								 return false;
								 
							 }else if(fieldYear < (currentYear - 1000) ){ //(ano corrente - mil anos)
								 edText.setError(pMessage);
								 edText.setFocusable(true);
								 edText.requestFocus();
								 
								 return false;
							 }
							 
							 return true;
						 } catch (java.text.ParseException e) {
							e.printStackTrace();
						}
					 }
				 }

				 // em qualquer outra condição é gerado um erro
				 edText.setError(pMessage);
				 edText.setFocusable(true);
				 edText.requestFocus();
				 return false;
			 }
			 return false;
		}		
	
	
	public static void hideKeyboard(Activity activity) {
		try {
	        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
	        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
	    } catch (Exception e) {
	    	Log.e("hideKeyboard", "Exception lançada no metodo hideKeyboard");	        
	    }
	    
	}
	
	public static void hideKeyboard(View view) {
		try {
	        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
	        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
	    } catch (Exception e) {
	    	Log.e("hideKeyboard", "Exception lançada no metodo hideKeyboard");	        
	    }
	    
	}
	
	public static void showKeyboard(View view) {
		try {
	        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
	        inputMethodManager.showSoftInput( view , InputMethodManager.SHOW_IMPLICIT );
	    } catch (Exception e) {
	    	Log.e("hideKeyboard", "Exception lançada no metodo hideKeyboard");	        
	    }
	    
	}
	
	public static void showKeyboard(Activity activity) {
		try {
	        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
	        inputMethodManager.showSoftInputFromInputMethod( activity.getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_IMPLICIT );
	    } catch (Exception e) {
	    	Log.e("hideKeyboard", "Exception lançada no metodo hideKeyboard");	        
	    }
	    
	}
	

	
	public static int resolveBitmapOrientation(String bitmapFile) throws IOException {
        ExifInterface exif = null;
        exif = new ExifInterface(bitmapFile);

        return exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
    }
	
	public static Bitmap applyOrientation(Bitmap bitmap, int orientation) {
        int rotate = 0;
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_270:
                rotate = 270;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                rotate = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotate = 90;
                break;
            default:
                return bitmap;
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix mtx = new Matrix();
        mtx.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }
	
	
	
}