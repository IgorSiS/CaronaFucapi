package br.fucapi.proj_carona.activity.usuario;

import java.util.List;
import br.fucapi.proj_carona.R;
import br.fucapi.proj_carona.adapter.ListaAdapter;
import br.fucapi.proj_carona.model.bean.Paciente;
import br.fucapi.proj_carona.model.bean.TipoUsuario;
import br.fucapi.proj_carona.model.bean.Usuario;
import br.fucapi.proj_carona.model.dao.UsuarioDAO;
import br.fucapi.proj_carona.utils.PutExtras;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class UsuarioActivity extends Activity {

	//Definicao das constantes
	private final String TAG = "CADASTRO_USUARIO";
	
	//Atributos de Tela
	private ListView lvListagem;
	
	//Colecao de pacientes a serem exibidos na tela
	private List<Usuario> listaAmigos;
	
	//ArrayAdapter para adaptar lista em View
	private ListaAdapter adapter;
	
	//Definicao do Layout de exibicao da lista
	//private int adapterLayout = android.R.layout.simple_list_item_1;
	
	//Usuario selecionando com o click longo
	private Usuario usuarioSelecionado = null;	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usuario);
		
		//getActionBar().setHomeButtonEnabled(true);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Ligacao dos componentes de TELA aos atributos da Activity
		lvListagem = (ListView) findViewById(R.id.lvListagem);
		//Informa que a ListView tem um menu de contexto
		registerForContextMenu(lvListagem);
		
		//Metodo do click longo
		lvListagem.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				//Marca o usuario selecionado
				usuarioSelecionado = (Usuario) adapter.getItemAtPosition(posicao);
				Log.i(TAG, "Usuario Selecionado ListView.LongClick()"
						+ usuarioSelecionado.getNome());
				return false;
			}
		});

		// Metodo que "escuta" o evento de Click SIMPLES
		lvListagem.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				Intent form = new Intent(UsuarioActivity.this,
						UsuarioDadosActivity.class);

				usuarioSelecionado = (Usuario) lvListagem
						.getItemAtPosition(posicao);

				form.putExtra(PutExtras.USUARIO_SELECIONADO, usuarioSelecionado);

				startActivity(form);
			}
		});	
	}

	protected void onSaveInstanceState(Bundle outState){
		//Inclusao da lista paciente no objeto Bundle
	//	outState.putStringArrayList(PACIENTES_KEY, (ArrayList<Usuario>) listaPaciente);
		//Persistencia do objeto bundle
		super.onSaveInstanceState(outState);
		Log.i(TAG, "onsaveRestoreState(): "	+ listaAmigos);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		//Verifica o item do menu selecionado
		switch(item.getItemId()){
			//Verifica se foi selecionado um item novo
			case R.id.menu_novo:
				//Especialista em mudanca de tela
				Intent intent = new Intent(UsuarioActivity.this,
						UsuarioDadosActivity.class);
				//Carrega a nova tela
				startActivity(intent);
				
				return false;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Definicao do menu inflater
		MenuInflater inflater = this.getMenuInflater();
		
		//Inflar um XML
		inflater.inflate(R.menu.usuario, menu);
		
		return true;
	}
	
	public void carregarLista(){
		//Criacao do objeto DAO
		UsuarioDAO dao = new UsuarioDAO(this);
		//Chamado do metodo listar
		this.listaAmigos = dao.listarAmigos(1);
		//fim da conexao do DB
		dao.close();
		
		//O objeto arrayadapter converte lista em view
		this.adapter = new ListaAdapter(this, listaAmigos);
		//associacao do adapter ao listView
		this.lvListagem.setAdapter(adapter);
	}
	
	private void excluirUsuario() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Confirma a exclusao de: "
				+ usuarioSelecionado.getNome());

		builder.setPositiveButton("Sim", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int witch) {
				UsuarioDAO dao = new UsuarioDAO(UsuarioActivity.this);
				dao.deletar(usuarioSelecionado);
				dao.close();
				carregarLista();
				usuarioSelecionado = null;
			}
		});
		builder.setNegativeButton("Nao", null);
		AlertDialog dialog = builder.create();
		dialog.setTitle("Confirmacao de exclusao");
		dialog.show();
	}
	
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);
		
		getMenuInflater().inflate(R.menu.menu_contexto, menu);
			
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		
				switch (item.getItemId()) {
			case R.id.menu_editar:
				
				Intent form = new Intent(UsuarioActivity.this,
						UsuarioDadosActivity.class);				
				form.putExtra(PutExtras.USUARIO_SELECIONADO, usuarioSelecionado);

				startActivity(form);	
				break;
					
			case R.id.menu_deletar:
				excluirUsuario();
				break;
			default:
				break;
		}
					
		return super.onContextItemSelected(item);
	}
	protected void onResume(){
		super.onResume();
		this.carregarLista();
	}
}
