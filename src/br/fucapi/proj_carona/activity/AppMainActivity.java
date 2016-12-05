package br.fucapi.proj_carona.activity;


import java.util.ArrayList;
import java.util.List;

import br.fucapi.proj_carona.R;
import br.fucapi.proj_carona.activity.paciente.UsuarioDadosActivity;
import br.fucapi.proj_carona.controller.NavigationController;
import br.fucapi.proj_carona.eula.Eula;
import br.fucapi.proj_carona.fragment.LoginFragment;
import br.fucapi.proj_carona.fragment.MenuPrincipalFragment;
import br.fucapi.proj_carona.fragment.UsuarioFragment;
import br.fucapi.proj_carona.model.bean.Usuario;
import br.fucapi.proj_carona.utils.PutExtras;
import br.fucapi.proj_carona.utils.RequestCodes;
import proj_caronanavdrawer.AbstractNavDrawerActivity;
import proj_caronanavdrawer.NavDrawerActivityConfiguration;
import proj_caronanavdrawer.NavDrawerAdapter;
import proj_caronanavdrawer.NavDrawerItem;
import proj_caronanavdrawer.NavMenuItem;
import proj_caronanavdrawer.NavMenuSection;

//import com.myappnavdrower.friends.FriendMainFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class AppMainActivity extends AbstractNavDrawerActivity {
	
	private Usuario usuarioLogado = null;		
	private Fragment fragManager=null;
	private FragmentTransaction  transaction;
	private boolean doubleBackToExitPressedOnce=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
					
		
		if ( savedInstanceState == null ) {
			
			fragManager = new MenuPrincipalFragment();
			Bundle args = new Bundle();			
			args.putSerializable(PutExtras.USUARIO_LOGADO, usuarioLogado);
			fragManager.setArguments(args);
	
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragManager ).commit();
			
					
			//getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragManager ).commit();
			
			Eula.show(this, R.string.eula_title, R.string.eula_accept, R.string.eula_refuse);
		}
	}
	
	@Override
	protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
		
		
		List<NavDrawerItem> listMenu = new ArrayList<NavDrawerItem>();
		
		usuarioLogado = super.getUsuarioLogado();
		
		NavDrawerItem secaoCadastro = NavMenuSection.create( 100, "Cadastros");
		
		NavDrawerItem menuUsuario = NavMenuItem.create( RequestCodes.MENU_USUARIO , getString(R.string.menu_usuario), R.drawable.ic_man, true, this);		
		NavDrawerItem menuBuscarAmigos = NavMenuItem.create(RequestCodes.MENU_BUSCAR_AMIGOS, getString(R.string.menu_buscar_amigos) , R.drawable.ic_localizar_amigos, true, this);
		NavDrawerItem menuAmigos = NavMenuItem.create(RequestCodes.MENU_AMIGOS, getString(R.string.menu_amigos) , R.drawable.ic_amigos, true, this);
		NavDrawerItem menuCarona = NavMenuItem.create(RequestCodes.MENU_OFERECER_CARONA,getString(R.string.menu_oferecer_carona), R.drawable.ic_oferecer_carona, true, this);						
		NavDrawerItem menuBuscarCarona = NavMenuItem.create(RequestCodes.MENU_BUSCAR_CARONA,getString(R.string.menu_buscar_carona), R.drawable.ic_localizar_carona, true, this);
		
		NavDrawerItem secaoApp = NavMenuSection.create(200, getString(R.string.aplicacao));
		
		//NavDrawerItem menuLogin = NavMenuItem.create(RequestCodes.MENU_LOGIN, getString(R.string.menu_login), R.drawable.navdrawer_friends, true, this);
		
		NavDrawerItem menuAvaliar = NavMenuItem.create(202, "Avaliar este app", "navdrawer_rating", false, this);
		NavDrawerItem menuEULA = NavMenuItem.create(203, getString(R.string.licenca_uso), "navdrawer_eula", false, this);
		NavDrawerItem menuSair = NavMenuItem.create(204, "Quit", "navdrawer_quit", false, this);
		
		
		
		if(usuarioLogado!=null){
				
				listMenu.add(secaoCadastro);
				listMenu.add(menuUsuario);
				listMenu.add(menuBuscarAmigos);				
				listMenu.add(menuAmigos);
				listMenu.add(menuCarona);
				listMenu.add(menuBuscarCarona);
				
								
				listMenu.add(secaoApp);
				//listMenu.add(menuLogin);
				listMenu.add(menuAvaliar);
				listMenu.add(menuEULA);
				listMenu.add(menuSair);				
				
				
						
		}else{
			
		}
				
		
		NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
		navDrawerActivityConfiguration.setMainLayout(R.layout.main);
		navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
		navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
		navDrawerActivityConfiguration.setNavItems(listMenu);
		navDrawerActivityConfiguration.setDrawerShadow(R.drawable.drawer_shadow);		
		navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
		navDrawerActivityConfiguration.setDrawerCloseDesc(R.string.drawer_close);
		navDrawerActivityConfiguration.setBaseAdapter(
			new NavDrawerAdapter(this, R.layout.navdrawer_item, listMenu ));
		return navDrawerActivityConfiguration;
	}
	
	@Override
	public void onBackPressed() {
		
		if(fragManager instanceof MenuPrincipalFragment){        	
        	
    		if (doubleBackToExitPressedOnce) {
    	        super.onBackPressed();
    	        return;
    	    }
    		Toast.makeText(this, "Precione novamente para sair", Toast.LENGTH_SHORT).show();
    	    //Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
    		this.doubleBackToExitPressedOnce = true;
    	    
    	    new Handler().postDelayed(new Runnable() {

    	        @Override
    	        public void run() {
    	            doubleBackToExitPressedOnce=false;                       
    	        }
    	    }, 2000);
    		
        	
        	
        }else{
    		super.onBackPressed();
    		fragManager = new MenuPrincipalFragment();
    		Bundle args = new Bundle();			
    		args.putSerializable(PutExtras.USUARIO_LOGADO, usuarioLogado);
    		fragManager.setArguments(args);

    		
    		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragManager ).commit();
    		setNoItemChecked();        	
        	
        }	    
				
		
		
	}
	
	@Override
	protected void onNavItemSelected(int id) {
		Intent intent;		
		Bundle args;				
		
		
		switch ((int)id) {
		
		case RequestCodes.MENU_USUARIO:		
			
			if(usuarioLogado!=null){
				
				intent = new Intent(this, UsuarioDadosActivity.class);
				intent.putExtra(PutExtras.USUARIO_SELECIONADO, usuarioLogado);
				this.startActivityForResult(intent, RequestCodes.ALTERAR_USUARIO);												
			}
						
			//getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, frag ).commit();			
			break;
		case RequestCodes.MENU_AMIGOS:
			//getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new AgenteFragment()).commit();			//
			
			fragManager = new UsuarioFragment();
			
			transaction = getSupportFragmentManager().beginTransaction();
			 
			args = new Bundle();			
    		args.putSerializable(PutExtras.USUARIO_LOGADO, usuarioLogado);
    		fragManager.setArguments(args);
			
	        transaction.replace(R.id.content_frame, fragManager );
	        transaction.addToBackStack(null);
	 
	        transaction.commit();
			
			break;
		case RequestCodes.MENU_BUSCAR_AMIGOS:
			/*
			fragManager = new MedicoFragment();
			
			transaction = getSupportFragmentManager().beginTransaction();
			 
	        transaction.replace(R.id.content_frame, fragManager );
	        transaction.addToBackStack(null);
	 
	        transaction.commit();*/
			
			//getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MedicoFragment()).commit();			
			break;
		case RequestCodes.MENU_BUSCAR_CARONA:
			/*
			fragManager = new MedicoFragment();
			
			transaction = getSupportFragmentManager().beginTransaction();
			 
	        transaction.replace(R.id.content_frame, fragManager );
	        transaction.addToBackStack(null);
	 
	        transaction.commit();*/
			
			//getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MedicoFragment()).commit();			
			break;	
		case RequestCodes.MENU_OFERECER_CARONA:
			/*
			fragManager = new MedicoFragment();
			
			transaction = getSupportFragmentManager().beginTransaction();
			 
	        transaction.replace(R.id.content_frame, fragManager );
	        transaction.addToBackStack(null);
	 
	        transaction.commit();*/
			
			//getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MedicoFragment()).commit();			
			break;
			
		
		case 201:
			//NavigationController.getInstance().showSettings(this);
			break;
		case 202:
			NavigationController.getInstance().startAppRating(this);
			break;
		case 203:
			NavigationController.getInstance().showEula(this);
			break;
		case 204:
			NavigationController.getInstance().showExitDialog(this);
			break;
					
		case RequestCodes.MENU_LOGIN:			
			
			fragManager = new LoginFragment();
			transaction = getSupportFragmentManager().beginTransaction();
			 
	        transaction.replace(R.id.content_frame, fragManager );
	        transaction.addToBackStack(null);
	 
	        transaction.commit();
	        
			//getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();								
			break;
			
		
		}
		
		
			
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RequestCodes.PREFERENCES_RESULTCODE) {
			Toast.makeText(this, "Back from preferences", Toast.LENGTH_SHORT)
					.show();

		}
		if (requestCode == RequestCodes.ALTERAR_USUARIO) {
			
			if(resultCode == Activity.RESULT_OK) {
				//Toast.makeText(this, "Back from Activity Save", Toast.LENGTH_SHORT).show();
				usuarioLogado = (Usuario) data.getSerializableExtra(PutExtras.ALTERAR_USUARIO);
                //Log.e("REQUEST", nomeUBS);
                
            }
						
		}
		
		
	}
}
