package br.fucapi.proj_carona.init;


import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import br.fucapi.proj_carona.sqlite.SQLiteDatabaseFactory;


public class AppInit {

	/**
	 * App init
	 */
	private static final AppInit instance = new AppInit();
	
	private AppInit() {
		
	}
	
	public static AppInit getInstance() {
		return instance;
	}
	
	/**
	 * @param context
	 * @throws NameNotFoundException
	 */
	public void init( Context context ) throws NameNotFoundException {
		SQLiteDatabaseFactory.getInstance().init( context, true, true );
	}
}
