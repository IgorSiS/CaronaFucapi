package br.fucapi.proj_carona.activity.paciente;

import br.fucapi.proj_carona.R;
import br.fucapi.proj_carona.model.bean.Usuario;
import br.fucapi.proj_carona.model.dao.UsuarioDAO;
import br.fucapi.proj_carona.model.helper.UsuarioHelper;
import br.fucapi.proj_carona.utils.PutExtras;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class UsuarioDadosActivity extends AbstractUsuarioDadosActivity {

	//Atributos para manipulacao de tela
	private UsuarioHelper helper;
	private Usuario usuarioParaSerAlterado = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.setContentView(R.layout.usuariodados);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.pacientedados);
				
		//Criacao do objeto helper
		 helper = (UsuarioHelper) getHelper();				
		// Busca o paciente a ser alterado
		 usuarioParaSerAlterado = (Usuario) getIntent().getSerializableExtra(
		PutExtras.USUARIO_SELECIONADO);
		
		if (usuarioParaSerAlterado != null) {
			// Atualiza a tela com dados do Aluno
			helper.setUsuario(usuarioParaSerAlterado);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Definicao do menu inflater
		MenuInflater inflater = this.getMenuInflater();
		
		//Inflar um XML
		inflater.inflate(R.menu.menu_formularios, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // go home
	        	// ir Para o menu principal	            
	            /*
	        	Intent intent = new Intent(this, PacienteActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);*/
	            return true;
	        case R.id.menu_salvar:			
				//Utilizando o helper
				Usuario usuario = (Usuario) helper.getUsuario();
				//Criacao do objeto DAO
				UsuarioDAO dao = new UsuarioDAO(UsuarioDadosActivity.this);
				//Validando os campos
				if(helper.validar()==true){		
					// Verificacao para salvar ou cadastrar o aluno
					if (usuario.getId() == null) {
						dao.cadastrar(usuario);
					} else {
						dao.alterar(usuario);
						Intent result = new Intent();
		                result.putExtra( PutExtras.ALTERAR_USUARIO ,usuario);
		                setResult(Activity.RESULT_OK, result);
					}//Fechando a conexao com o BD
					dao.close();
					//Encerrando a activity
					finish();
										
					
				}
				return true;
			case R.id.menu_cancelar:			
				finish();
				return true;
	    }
	    return super.onOptionsItemSelected(item);
	}

	@Override
	public UsuarioHelper abstractHelper() { 
		return new UsuarioHelper(this);
	}
	
}
