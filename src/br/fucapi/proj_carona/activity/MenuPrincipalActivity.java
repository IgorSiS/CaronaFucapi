package br.fucapi.proj_carona.activity;

import br.fucapi.proj_carona.R;
import br.fucapi.proj_carona.activity.paciente.UsuarioActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipalActivity extends Activity implements OnClickListener {

	
	private Button btUsuario;	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Layout do Dashboard
		setContentView(R.layout.menuprincipal);

				
		btUsuario = (Button) findViewById(R.id.btPaciente);
		btUsuario.setOnClickListener(this);
		
				
	}

	@Override
	public void onClick(View v) {
        	//Intent intent = new Intent(this, AgenteActivity.class);
			
    		
    		Intent intent2 = new Intent(this, UsuarioActivity.class);
    		if(v == btUsuario) {
    			startActivity(intent2);
    		}
    		    		
    		
	}
}