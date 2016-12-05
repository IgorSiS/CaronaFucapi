package br.fucapi.proj_carona.model.dao;

import java.util.ArrayList;
import java.util.List;
import br.fucapi.proj_carona.R;
import br.fucapi.proj_carona.model.bean.TipoUsuario;
import br.fucapi.proj_carona.model.bean.Usuario;
import br.fucapi.proj_carona.sqlite.SQLiteDatabaseHelper;
import br.fucapi.proj_carona.utils.SpinnerObject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.widget.Toast;

public class UsuarioDAO extends AbstractDataBase{
		
	//Constante para log no LogCat
	private static final String TAG = "CADASTRO_USUARIO";
	
	
    private Context context;    
	public UsuarioDAO (Context context){
		
		//Chamando o construtor que sabe acessar o BD
		super(context);
		this.context = context;
	
	}
	
	 /** 
	 * metodo responsavel pelo cadastro do usuario
	 * */
	public void cadastrar (Usuario usuario){
		//objeto para armazenar os valores dos camopos
		ContentValues values = new ContentValues();	
		
		getWritableDatabase().beginTransaction();
		
		try{
			
			//Definicao dos valores dos campos
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NOME.db_nome_campo, usuario.getNome());										
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SEXO.db_nome_campo, usuario.getSexo() );						
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CPF.db_nome_campo, usuario.getCpf());
			
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo, usuario.getRegistroAcademico());
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CURSO.db_nome_campo, usuario.getNomeCurso());
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_TURNO.db_nome_campo, usuario.getTurno());
			
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CELULAR.db_nome_campo, usuario.getCelular());
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CONTATO_EMERGENCIA.db_nome_campo, usuario.getContatoEmergencia());		
						
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ENDERECO.db_nome_campo , usuario.getEndereco());
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NUMERO.db_nome_campo, usuario.getNumero());
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CEP.db_nome_campo, usuario.getCep());
				
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_FOTO.db_nome_campo, usuario.getFoto());	
						
						
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_LOGIN.db_nome_campo, usuario.getLogin());
			values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SENHA.db_nome_campo, usuario.getSenha());
			
						
			
			//Inserir dados do usuario
			getWritableDatabase().insert(SQLiteDatabaseHelper.TABLE_USUARIO.TABLE_NAME, null, values);
			
			getWritableDatabase().setTransactionSuccessful();// marks a commit
									
			
		}catch(SQLiteConstraintException e) {
			
			Toast.makeText(context, "Usuario: "+usuario.getCpf() +" ja existente", Toast.LENGTH_LONG).show();
			
			
		}catch(SQLException sqlE) {	
			Log.e(TAG, "SqlException: "+sqlE.getMessage() );	
		}finally{
			getWritableDatabase().endTransaction();
			
			
			Log.i(TAG, "Usuario Cadastrado: "+ usuario.getNome() );
			Log.i(TAG, "cpf: "+ usuario.getCpf() );
			
			
			Log.i(TAG, "Login: "+ usuario.getLogin() );
			Log.i(TAG, "Senha: "+ usuario.getSenha() );
			Log.i(TAG, "endereco: "+ usuario.getEndereco() );
			Log.i(TAG, "numero: "+ usuario.getNumero());
			Log.i(TAG, "cep: "+ usuario.getCep() );
								
			Log.i(TAG, "celular: "+ usuario.getCelular() );			
			Log.i(TAG, "sexo: "+ usuario.getSexo() );		
				
		}
	}
	
	/** 
	 * metodo responsavel pela listagem dos usuarios na tela
	 * */
	public List<Usuario> listarAmigos(long idUsuario){
		//Colecao de usuarios
		List<Usuario> lista = new ArrayList<Usuario>();
		//Definicao da instrucao SQL
		String sql = null;
						
			sql = "Select " +
					SQLiteDatabaseHelper.TABLE_USUARIO.ALL_FIELDS_TABLE_USUARIO + " "+					
					" from "+SQLiteDatabaseHelper.TABLE_USUARIO.TABLE_NAME +" " +					
					"where "+SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ID.db_nome_campo+" in ("
							+ "select "+SQLiteDatabaseHelper.TABLE_AMIGOS.FIELD_IDAMIGO.db_nome_campo+" from "
							+SQLiteDatabaseHelper.TABLE_AMIGOS.TABLE_NAME + " where "+ SQLiteDatabaseHelper.TABLE_AMIGOS.FIELD_IDUSUARIO.db_nome_campo + " = '"+idUsuario+"' " 
							+ ")"
							+ "order by "+SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NOME.db_nome_campo +" ";		
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){
				Usuario amigos = new Usuario();
				
				
				//Carregar os atributos dos usuarios
				amigos.setId( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ID.db_nome_campo) )); 
								
				amigos.setNome(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NOME.db_nome_campo)));
				amigos.setSexo( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SEXO.db_nome_campo )) );								
				amigos.setCpf(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CPF.db_nome_campo)));
				
				amigos.setRegistroAcademico(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo)));
				amigos.setNomeCurso(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CURSO.db_nome_campo)));
				amigos.setTurno(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_TURNO.db_nome_campo)));
				
				
				amigos.setCelular(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CELULAR.db_nome_campo )));
				amigos.setContatoEmergencia(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CONTATO_EMERGENCIA.db_nome_campo )));
				
				
				amigos.setEndereco(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ENDERECO.db_nome_campo )));
				amigos.setNumero(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NUMERO.db_nome_campo )));												
				amigos.setCep(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CEP.db_nome_campo ))); 
				
												
				amigos.setFoto(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_FOTO.db_nome_campo)));
				
				
				amigos.setLogin( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_LOGIN.db_nome_campo )) );
				amigos.setSenha( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SENHA.db_nome_campo )) );
				
				//usuario.setObservacao( cursor.getString(cursor.getColumnIndex("observacao")) );
										
				//Adiciona um novo usuario a lista
				lista.add(amigos);
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());		
		}finally{
			cursor.close();
		}
		return lista;
	}
	
	/** 
	 * metodo responsavel pela exclusao de usuarios
	 * */
	public void deletar(Usuario usuario) {
		//Array de parametros
		String[] args = {usuario.getId().toString()};
		
		//Exclusao do usuario
		getWritableDatabase().delete(SQLiteDatabaseHelper.TABLE_USUARIO.TABLE_NAME, "id=?", args);
		Log.i(TAG, "Usuario Deletado: " +usuario.getNome());
	}
	
	/** 
	 * metodo responsavel pela atualizacao de usuarios
	 * */
	public void alterar(Usuario usuario) {
		ContentValues values = new ContentValues();
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NOME.db_nome_campo , usuario.getNome());
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SEXO.db_nome_campo , usuario.getSexo() );
		
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CPF.db_nome_campo, usuario.getCpf());
		
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo, usuario.getRegistroAcademico());
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CURSO.db_nome_campo, usuario.getNomeCurso());
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_TURNO.db_nome_campo, usuario.getTurno());
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CELULAR.db_nome_campo , usuario.getCelular());
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CONTATO_EMERGENCIA.db_nome_campo, usuario.getContatoEmergencia());
		
		
		values.put( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ENDERECO.db_nome_campo , usuario.getEndereco());
		values.put( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NUMERO.db_nome_campo , usuario.getNumero());
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CEP.db_nome_campo , usuario.getCep());
						
		values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_FOTO.db_nome_campo, usuario.getFoto());		
		//values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_LOGIN.db_nome_campo, usuario.getLogin());
		//values.put(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SENHA.db_nome_campo, usuario.getSenha());
		
		
			
		// Colecao de valores de parametros do SQL
		String[] args = { usuario.getId().toString() };

		// Altera dados do Aluno no BD
		getWritableDatabase().update(SQLiteDatabaseHelper.TABLE_USUARIO.TABLE_NAME, values, "id=?", args);
		Log.i(TAG, "Usuario alterado: " + usuario.getNome());
	}
	
	public Usuario getUsuario(String login, String senha){
		//Colecao de usuarios
		Usuario usuario = null;
		
		//Log.i(TAG, "Login = " + login );
		//Log.i(TAG, "Senha = " + senha );
		
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_USUARIO.TABLE_NAME+
				" where "+SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_LOGIN.db_nome_campo+"='"+login+"' and "+
				SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SENHA.db_nome_campo+"='"+senha+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				usuario = new Usuario();			
				
				
				//Carregar os atributos dos usuarios
				usuario.setId( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ID.db_nome_campo) )); 
								
				usuario.setNome(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NOME.db_nome_campo)));
				usuario.setSexo( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SEXO.db_nome_campo )) );
				
				
				usuario.setCpf(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CPF.db_nome_campo)));
				
				usuario.setRegistroAcademico(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo)));
				usuario.setNomeCurso(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CURSO.db_nome_campo)));
				usuario.setTurno(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_TURNO.db_nome_campo)));
				
				usuario.setCelular(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CELULAR.db_nome_campo )));
				usuario.setContatoEmergencia(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CONTATO_EMERGENCIA.db_nome_campo)));
				
				usuario.setEndereco(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ENDERECO.db_nome_campo )));
				usuario.setNumero(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NUMERO.db_nome_campo )));
				usuario.setCep(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CEP.db_nome_campo ))); 
				
				
				usuario.setFoto(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_FOTO.db_nome_campo)));
				
				usuario.setLogin( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_LOGIN.db_nome_campo )) );
				usuario.setSenha( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SENHA.db_nome_campo )) );
				
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return usuario;
	}
	
	public Usuario getUsuario(long id){
		//Colecao de usuarios
		Usuario usuario = null;		
		//Definicao da instrucao SQL
		String sql = "Select * from "+SQLiteDatabaseHelper.TABLE_USUARIO.TABLE_NAME+" where "+SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ID.db_nome_campo+"='"+id+"' ";
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			if(cursor.moveToNext()){				
				
usuario = new Usuario();			
				
				
				//Carregar os atributos dos usuarios
				usuario.setId( cursor.getLong(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ID.db_nome_campo) )); 
								
				usuario.setNome(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NOME.db_nome_campo)));
				usuario.setSexo( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SEXO.db_nome_campo )) );
				
				
				usuario.setCpf(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CPF.db_nome_campo)));
				
				usuario.setRegistroAcademico(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo)));
				usuario.setNomeCurso(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CURSO.db_nome_campo)));
				usuario.setTurno(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_TURNO.db_nome_campo)));
				
				usuario.setCelular(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CELULAR.db_nome_campo )));
				usuario.setContatoEmergencia(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CONTATO_EMERGENCIA.db_nome_campo)));
				
				usuario.setEndereco(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ENDERECO.db_nome_campo )));
				usuario.setNumero(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NUMERO.db_nome_campo )));
				usuario.setCep(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_CEP.db_nome_campo ))); 
				
				
				usuario.setFoto(cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_FOTO.db_nome_campo)));
				
				usuario.setLogin( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_LOGIN.db_nome_campo )) );
				usuario.setSenha( cursor.getString(cursor.getColumnIndex( SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_SENHA.db_nome_campo )) );
				
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return usuario;
	}
	
	
	public List <SpinnerObject> getUsuarioForSpinner(TipoUsuario tipoUsuario){
	    
		//Colecao de bairros
		List<SpinnerObject> list_usuario = new ArrayList<SpinnerObject>();		
		//Definicao da instrucao SQL
		
		
		//primeiro elemento da lista
		String text = context.getString(R.string.usuario_prompt);
		list_usuario.add(new SpinnerObject(0, text));
				
		//Definicao da instrucao SQL		
		String sql = "Select " +
				SQLiteDatabaseHelper.TABLE_USUARIO.ALL_FIELDS_TABLE_USUARIO + " "+					
				" from "+SQLiteDatabaseHelper.TABLE_USUARIO.TABLE_NAME +" " +									
				"order by "+SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NOME.db_nome_campo +" ";		
	
		
		//Objeto que reebe os registros do banco de dados
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);
		try{
			while(cursor.moveToNext()){				
				//Adiciona um novo bairro a lista								
				list_usuario.add ( new SpinnerObject ( 
						cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_ID.db_nome_campo) ) , 
						cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.TABLE_USUARIO.FIELD_NOME.db_nome_campo) )
						) );
			}
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			cursor.close();
		}
		return list_usuario;
	    	    
	}

	
	
}
