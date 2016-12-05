package br.fucapi.proj_carona.fragment;

import br.fucapi.proj_carona.R;
import br.fucapi.proj_carona.activity.AppMainActivity;
import br.fucapi.proj_carona.model.bean.TipoUsuario;
import br.fucapi.proj_carona.model.bean.Usuario;
import br.fucapi.proj_carona.utils.DashboardLayout;
import br.fucapi.proj_carona.utils.RequestCodes;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuPrincipalFragment extends Fragment implements OnClickListener {

	private Button btUsuario;
	private Button btAmigos;
	private Button btCarona;
				
	
	private Usuario usuarioLogado = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(this.getArguments() !=null){
			usuarioLogado = (Usuario) this.getArguments().getSerializable("USUARIO_LOGADO");
		}
		
		setHasOptionsMenu(true); //adicionar itens ao OptionsMenu
				
		
	}
	    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		//Log.d("FragmentLifecycle", "onCreateView savedInstanceState is " + (savedInstanceState == null?"":"not ") + "null");
		View layout = inflater.inflate(R.layout.dinamic_menuprincipal, container, false);								
		
		/*
		btMedico = (Button) layout.findViewById(R.id.btMedico);
		btMedico.setOnClickListener(this);
		btAgente = (Button) layout.findViewById(R.id.btAgente);
		btAgente.setOnClickListener(this);
		btPaciente = (Button) layout.findViewById(R.id.btPaciente);
		btPaciente.setOnClickListener(this);
		*/
		
		DashboardLayout dash = (DashboardLayout) layout.findViewById(R.id.dashboard);
		LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f);
		/*Botao Paciente*/
		btUsuario = new Button(this.getActivity());		
		btUsuario.setText( getResources().getString(R.string.menu_usuario) );
		Drawable drawableTopPaciente = getResources().getDrawable(R.drawable.ic_man);
		btUsuario.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopPaciente , null, null);
		btUsuario.setOnClickListener(this);		
		btUsuario.setLayoutParams(layoutParams);								
		btUsuario.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		
		
		/*Botao Amigos*/
		btAmigos = new Button(this.getActivity());		
		btAmigos.setText( getResources().getString(R.string.menu_amigos) );
		Drawable drawableTopAgente = getResources().getDrawable(R.drawable.ic_amigos);
		btAmigos.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopAgente , null, null);
		btAmigos.setOnClickListener(this);					
		btAmigos.setLayoutParams(layoutParams);						
		btAmigos.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		//btAgente.setWidth(30);
		
		
		/*Botao Medico*/
		btCarona = new Button(this.getActivity());		
		btCarona.setText( getResources().getString(R.string.menu_oferecer_carona) );
		Drawable drawableTopMedico = getResources().getDrawable(R.drawable.ic_oferecer_carona);
		btCarona.setCompoundDrawablesWithIntrinsicBounds(null, drawableTopMedico , null, null);
		btCarona.setOnClickListener(this);		
		btCarona.setLayoutParams(layoutParams);
		btCarona.setBackgroundColor(getResources().getColor( android.R.color.transparent ) );
		
			dash.addView(btUsuario);
			dash.addView(btAmigos);		
			dash.addView(btCarona);				
			
				
		//((AppMainActivity)MenuPrincipalFragment.this.getActivity()).selectItem( RequestCodes.MENU_AGENTE );
		
		/*
		btcoletadados = (Button) layout.findViewById(R.id.btcoletadados);
		btcoletadados.setOnClickListener(this);
		*/
		return layout;		
	}
		

	public void onSaveInstanceState(Bundle outState){
		//Inclusao da lista paciente no objeto Bundle
	//	outState.putStringArrayList(PACIENTES_KEY, (ArrayList<Usuario>) listaPaciente);
		//Persistencia do objeto bundle
		super.onSaveInstanceState(outState);		
	}
	
		
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		  inflater.inflate(R.menu.menu_principal, menu);
	}
			

	
	@Override
	public void onClick(View view) {
			 
			if(view.equals(btUsuario)) {				    			
				((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_USUARIO );    			
			}else if(view.equals(btAmigos)) {    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_AMIGOS );
    		}else if(view.equals(btCarona)) {    			    			
    			((AppMainActivity)MenuPrincipalFragment.this.getActivity()).executeItem( RequestCodes.MENU_OFERECER_CARONA );    			    			
    		}
    		    				
    		
	}
}