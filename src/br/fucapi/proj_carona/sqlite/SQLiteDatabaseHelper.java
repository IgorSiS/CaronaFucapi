package br.fucapi.proj_carona.sqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.fucapi.proj_carona.model.bean.TipoUsuario;

/**
 * @author
 *
 */
public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

	/**
	 * 
	 */	
	
   
	//Constantes para auxiliar o controle de versoes
	
	 // Common column names
    private static final String KEY_ID = "id";    
    
    public static interface TABLE_USUARIO {        
        
        String TABLE_NAME = "Usuario";  
        
        public static interface FIELD_ID{
        	String db_nome_campo = KEY_ID;
        	String tipo = "INTEGER PRIMARY KEY";        	
        }
        
        public static interface FIELD_NOME{
        	String db_nome_campo = "nome";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_SEXO{
        	String db_nome_campo = "sexo";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_CPF{
        	String db_nome_campo = "cpf";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_REGISTRO_ACADEMICO{
        	String db_nome_campo = "registro_academico";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_CURSO{
        	String db_nome_campo = "curso";
        	String tipo = "TEXT";        	
        }
        
        
        public static interface FIELD_TURNO{
        	String db_nome_campo = "turno";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_CELULAR{
        	String db_nome_campo = "celular";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_CONTATO_EMERGENCIA{
        	String db_nome_campo = "contato_emergencia";
        	String tipo = "TEXT";        	
        }
                                       
        public static interface FIELD_ENDERECO{
        	String db_nome_campo = "endereco";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_NUMERO{
        	String db_nome_campo = "numero";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_CEP{
        	String db_nome_campo = "cep";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_FOTO{
        	String db_nome_campo = "foto";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_LOGIN{
        	String db_nome_campo = "login";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_SENHA{
        	String db_nome_campo = "senha";
        	String tipo = "TEXT";        	
        }
        
        
        public static String ALL_FIELDS =
        		TABLE_USUARIO.FIELD_ID.db_nome_campo+","+
                TABLE_USUARIO.FIELD_NOME.db_nome_campo+","+
                TABLE_USUARIO.FIELD_SEXO.db_nome_campo+","+    				
                TABLE_USUARIO.FIELD_CPF.db_nome_campo+","+
                TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo+","+
                		
            	TABLE_USUARIO.FIELD_CURSO.db_nome_campo+","+
            	TABLE_USUARIO.FIELD_TURNO.db_nome_campo+","+
            	TABLE_USUARIO.FIELD_CELULAR.db_nome_campo+","+    		    		
            	TABLE_USUARIO.FIELD_CONTATO_EMERGENCIA.db_nome_campo+","+
            	TABLE_USUARIO.FIELD_ENDERECO.db_nome_campo+","+
            			
            	TABLE_USUARIO.FIELD_NUMERO.db_nome_campo+","+
            	TABLE_USUARIO.FIELD_CEP.db_nome_campo+","+
            	TABLE_USUARIO.FIELD_FOTO.db_nome_campo+","+    		
            			
            	TABLE_USUARIO.FIELD_LOGIN.db_nome_campo+","+
            	TABLE_USUARIO.FIELD_SENHA.db_nome_campo+" "; 
        
        public static String ALL_FIELDS_TABLE_USUARIO =
        		TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_ID.db_nome_campo+","+
        		TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_NOME.db_nome_campo+","+
        		TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_SEXO.db_nome_campo+","+    				
        		TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_CPF.db_nome_campo+","+
        		TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo+","+
        		
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_CURSO.db_nome_campo+","+
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_TURNO.db_nome_campo+","+
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_CELULAR.db_nome_campo+","+    		    		
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_CONTATO_EMERGENCIA.db_nome_campo+","+
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_ENDERECO.db_nome_campo+","+
    						    		
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_NUMERO.db_nome_campo+","+
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_CEP.db_nome_campo+","+
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_FOTO.db_nome_campo+","+    		
    			    		
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_LOGIN.db_nome_campo+","+
    			TABLE_USUARIO.TABLE_NAME +"."+TABLE_USUARIO.FIELD_SENHA.db_nome_campo+" ";  
        
    }
    
      		                                                                                                           
        
    
    // Table Create Statements
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE "
            + TABLE_USUARIO.TABLE_NAME + "(" + 
    		TABLE_USUARIO.FIELD_ID.db_nome_campo + " "+TABLE_USUARIO.FIELD_ID.tipo+","+
            TABLE_USUARIO.FIELD_NOME.db_nome_campo + " "+TABLE_USUARIO.FIELD_NOME.tipo+","+
            TABLE_USUARIO.FIELD_SEXO.db_nome_campo + " "+TABLE_USUARIO.FIELD_SEXO.tipo+","+
            TABLE_USUARIO.FIELD_CPF.db_nome_campo + " "+TABLE_USUARIO.FIELD_CPF.tipo+","+
            TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo + " "+TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.tipo+","+
            TABLE_USUARIO.FIELD_CURSO.db_nome_campo + " "+TABLE_USUARIO.FIELD_CURSO.tipo+","+
            TABLE_USUARIO.FIELD_TURNO.db_nome_campo + " "+TABLE_USUARIO.FIELD_TURNO.tipo+","+
            TABLE_USUARIO.FIELD_CELULAR.db_nome_campo + " "+TABLE_USUARIO.FIELD_CELULAR.tipo+","+
            TABLE_USUARIO.FIELD_CONTATO_EMERGENCIA.db_nome_campo + " "+TABLE_USUARIO.FIELD_CONTATO_EMERGENCIA.tipo+","+
            TABLE_USUARIO.FIELD_ENDERECO.db_nome_campo + " "+TABLE_USUARIO.FIELD_ENDERECO.tipo+","+
            TABLE_USUARIO.FIELD_NUMERO.db_nome_campo + " "+TABLE_USUARIO.FIELD_NUMERO.tipo+","+
            TABLE_USUARIO.FIELD_CEP.db_nome_campo + " "+TABLE_USUARIO.FIELD_CEP.tipo+","+
            TABLE_USUARIO.FIELD_FOTO.db_nome_campo + " "+TABLE_USUARIO.FIELD_FOTO.tipo+","+
            TABLE_USUARIO.FIELD_LOGIN.db_nome_campo + " "+TABLE_USUARIO.FIELD_LOGIN.tipo+","+
            TABLE_USUARIO.FIELD_SENHA.db_nome_campo + " "+TABLE_USUARIO.FIELD_SENHA.tipo+")";

    
    private static final String CREATE_INDEX_TABLE_USUARIO = 
    		"CREATE UNIQUE INDEX index_"+TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo+" on "+TABLE_USUARIO.TABLE_NAME+" ("+TABLE_USUARIO.FIELD_REGISTRO_ACADEMICO.db_nome_campo+"); " +
    		"CREATE UNIQUE INDEX index_"+TABLE_USUARIO.FIELD_CPF.db_nome_campo+" on "+TABLE_USUARIO.TABLE_NAME+" ("+TABLE_USUARIO.FIELD_CPF.db_nome_campo+"); ";     
    
    
    
    /*TABELA DE AMIGOS */
    public static interface TABLE_AMIGOS {        
        
        String TABLE_NAME = "Amigos";  
        
        public static interface FIELD_ID{
        	String db_nome_campo = KEY_ID;
        	String tipo = "INTEGER PRIMARY KEY";        	
        }
        
        public static interface FIELD_IDUSUARIO{
        	String db_nome_campo = "id_usuario";
        	String tipo = "INTEGER";        	
        }
        
        public static interface FIELD_IDAMIGO{
        	String db_nome_campo = "id_amigo";
        	String tipo = "INTEGER";        	
        }
         
        public static String ALL_FIELDS =
        		TABLE_AMIGOS.FIELD_ID.db_nome_campo+","+
        		TABLE_AMIGOS.FIELD_IDUSUARIO.db_nome_campo+","+        		
        		TABLE_AMIGOS.FIELD_IDAMIGO.db_nome_campo+" ";
        
    }
    
    // Table Create Statements
    private static final String CREATE_TABLE_AMIGOS = "CREATE TABLE "
            + TABLE_AMIGOS.TABLE_NAME + "(" + 
    		TABLE_AMIGOS.FIELD_ID.db_nome_campo + " "+TABLE_AMIGOS.FIELD_ID.tipo+","+
            TABLE_AMIGOS.FIELD_IDUSUARIO.db_nome_campo + " "+TABLE_AMIGOS.FIELD_IDUSUARIO.tipo+","+
            TABLE_AMIGOS.FIELD_IDAMIGO.db_nome_campo + " "+TABLE_AMIGOS.FIELD_IDAMIGO.tipo+","+
            "FOREIGN KEY("+TABLE_AMIGOS.FIELD_IDUSUARIO.db_nome_campo+") REFERENCES "+TABLE_USUARIO.TABLE_NAME +"("+TABLE_USUARIO.FIELD_ID.db_nome_campo+"),"+
            "FOREIGN KEY("+TABLE_AMIGOS.FIELD_IDAMIGO.db_nome_campo+") REFERENCES "+TABLE_USUARIO.TABLE_NAME +"("+TABLE_USUARIO.FIELD_ID.db_nome_campo+") )";

        
    
    /*TABELA DE CARONA */
    public static interface TABLE_CARONA {        
        
        String TABLE_NAME = "CARONA";  
        
        public static interface FIELD_ID{
        	String db_nome_campo = KEY_ID;
        	String tipo = "INTEGER PRIMARY KEY";        	
        }
        
        public static interface FIELD_IDUSUARIO{
        	String db_nome_campo = "id_usuario";
        	String tipo = "INTEGER";        	
        }
        
        public static interface FIELD_ORIGEM{
        	String db_nome_campo = "origem";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_DESTINO{
        	String db_nome_campo = "destino";
        	String tipo = "TEXT";        	
        }
        
        
        
        public static interface FIELD_HORAPREVISTA{
        	String db_nome_campo = "hora_prevista";
        	String tipo = "TEXT";        	
        }
        public static interface FIELD_DIAS_CARONA{
        	String db_nome_campo = "dias_carona";
        	String tipo = "TEXT";        	
        }
        public static interface FIELD_NUM_VAGAS{
        	String db_nome_campo = "num_vagas";
        	String tipo = "TEXT";        	
        }
        public static interface FIELD_INFO_ADICIONAIS{
        	String db_nome_campo = "info_adicionais";
        	String tipo = "TEXT";        	
        }
        
                              
            
    }
    
    // Table Create Statements
    private static final String CREATE_TABLE_CARONA = "CREATE TABLE "
            + TABLE_CARONA.TABLE_NAME + "(" + 
    		TABLE_CARONA.FIELD_ID.db_nome_campo + " "+TABLE_CARONA.FIELD_ID.tipo+","+
            TABLE_CARONA.FIELD_IDUSUARIO.db_nome_campo + " "+TABLE_CARONA.FIELD_IDUSUARIO.tipo+","+
            TABLE_CARONA.FIELD_ORIGEM.db_nome_campo + " "+TABLE_CARONA.FIELD_ORIGEM.tipo+","+
            TABLE_CARONA.FIELD_DESTINO.db_nome_campo + " "+TABLE_CARONA.FIELD_DESTINO.tipo+","+
            TABLE_CARONA.FIELD_HORAPREVISTA.db_nome_campo + " "+TABLE_CARONA.FIELD_HORAPREVISTA.tipo+","+
            TABLE_CARONA.FIELD_DIAS_CARONA.db_nome_campo + " "+TABLE_CARONA.FIELD_DIAS_CARONA.tipo+","+
            TABLE_CARONA.FIELD_NUM_VAGAS.db_nome_campo + " "+TABLE_CARONA.FIELD_NUM_VAGAS.tipo+","+
            TABLE_CARONA.FIELD_INFO_ADICIONAIS.db_nome_campo + " "+TABLE_CARONA.FIELD_NUM_VAGAS.tipo+","+
                        
            "FOREIGN KEY("+TABLE_CARONA.FIELD_IDUSUARIO.db_nome_campo+") REFERENCES "+TABLE_USUARIO.TABLE_NAME +"("+TABLE_USUARIO.FIELD_ID.db_nome_campo+") )";
            

   
    
    /*TABELA DE DESTINOS */
    public static interface TABLE_DESTINOS {        
        
        String TABLE_NAME = "Destinos";  
        
        public static interface FIELD_ID{
        	String db_nome_campo = KEY_ID;
        	String tipo = "INTEGER PRIMARY KEY";        	
        }
        
        public static interface FIELD_IDCONDUTOR{
        	String db_nome_campo = "id_condutor";
        	String tipo = "INTEGER";        	
        }
        
        public static interface FIELD_IDCARONA{
        	String db_nome_campo = "id_carona";
        	String tipo = "INTEGER";        	
        }
        
        public static interface FIELD_DESTINO{
        	String db_nome_campo = "destino_carona";
        	String tipo = "TEXT";        	
        }
        
        public static interface FIELD_PONTO_ENCONTRO{
        	String db_nome_campo = "ponto_encontro";
        	String tipo = "TEXT";        	
        }
        public static interface FIELD_VALOR{
        	String db_nome_campo = "valor";
        	String tipo = "REAL";        	
        }
            
    }
    
    // Table Create Statements
    private static final String CREATE_TABLE_DESTINOS = "CREATE TABLE "
            + TABLE_DESTINOS.TABLE_NAME + "(" + 
    		TABLE_DESTINOS.FIELD_ID.db_nome_campo + " "+TABLE_DESTINOS.FIELD_ID.tipo+","+
            TABLE_DESTINOS.FIELD_IDCONDUTOR.db_nome_campo + " "+TABLE_DESTINOS.FIELD_IDCONDUTOR.tipo+","+
            TABLE_DESTINOS.FIELD_IDCARONA.db_nome_campo + " "+TABLE_DESTINOS.FIELD_IDCARONA.tipo+","+
            TABLE_DESTINOS.FIELD_DESTINO.db_nome_campo + " "+TABLE_DESTINOS.FIELD_DESTINO.tipo+","+
            TABLE_DESTINOS.FIELD_PONTO_ENCONTRO.db_nome_campo + " "+TABLE_DESTINOS.FIELD_PONTO_ENCONTRO.tipo+","+
            TABLE_DESTINOS.FIELD_VALOR.db_nome_campo + " "+TABLE_DESTINOS.FIELD_VALOR.tipo+","+
            
            "FOREIGN KEY("+TABLE_DESTINOS.FIELD_IDCONDUTOR.db_nome_campo+") REFERENCES "+TABLE_USUARIO.TABLE_NAME +"("+TABLE_USUARIO.FIELD_ID.db_nome_campo+"),"+
            "FOREIGN KEY("+TABLE_DESTINOS.FIELD_IDCARONA.db_nome_campo+") REFERENCES "+TABLE_USUARIO.TABLE_NAME +"("+TABLE_USUARIO.FIELD_ID.db_nome_campo+") )";

   
    
    
	private static final String TAG = "SqlLiteDataBase" ;

	
    		
	private static final String CREATE_DUMP_AMIGOS = 
    		"INSERT INTO "+TABLE_AMIGOS.TABLE_NAME+
    		"("+TABLE_AMIGOS.ALL_FIELDS+") "+
    		"VALUES  "
    		+ "(1,1,2)," 
    		+ "(2,1,3),"
    		+ "(3,1,4),"
    		+ "(4,2,1),"
    		+ "(5,2,3),"
    		+ "(6,3,4);";    		
					 

	private static final String CREATE_DUMP_USUARIOS = 
    		"INSERT INTO "+TABLE_USUARIO.TABLE_NAME+
    		"("+TABLE_USUARIO.ALL_FIELDS+") "+
    		"VALUES  "
    		+ "(1,'LINCOLN','MASCULINO','111.222.222-22','111','CCP','NOTURNO','(92)999771820','(92)999771820','RUA XYZ','NUM X','00000-000',NULL,'lincoln@gmail.com','123')," 
    		+ "(2,'RAIMUNDO','MASCULINO','222.222.222-22','222','CCP','NOTURNO','(92)999771820','(92)999771820','RUA XYZ','NUM X','00000-000',NULL,'raimundo@gmail.com','123')," 
    		+ "(3,'LUCAS','MASCULINO','333.222.222-22','333','CCP','NOTURNO','(92)999771820','(92)999771820','RUA XYZ','NUM X','00000-000',NULL,'lucas@gmail.com','123')," 
    		+ "(4,'MARCOS','MASCULINO','444.222.222-22','444','CCP','NOTURNO','(92)999771820','(92)999771820','RUA XYZ','NUM X','00000-000',NULL,'marcos@gmail.com','123')," 
    		+ "(5,'IGOR','MASCULINO','555.222.222-22','555','CCP','NOTURNO','(92)999771820','(92)999771820','RUA XYZ','NUM X','00000-000',NULL,'igor@gmail.com','123');" ;

	
	/**
	 * @param context androdi context
	 * @param name database name
	 * @param factory cursor factory
	 * @param version database version
	 */
	public SQLiteDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
		context.deleteDatabase(DatabaseInfo.DATABASE_NAME);
		//super(context,DatabaseInfo.DATABASE_NAME,null,DatabaseInfo.DATABASE_VERSION);
	}

	/** 
	 * {@inheritDoc}
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.i(TAG, "create database");
					
		database.execSQL(CREATE_TABLE_USUARIO);
		database.execSQL(CREATE_TABLE_CARONA);
		database.execSQL(CREATE_TABLE_AMIGOS);
		database.execSQL(CREATE_TABLE_DESTINOS);
		
		
		database.execSQL(CREATE_INDEX_TABLE_USUARIO);
		
									
		database.execSQL(CREATE_DUMP_USUARIOS);
		database.execSQL(CREATE_DUMP_AMIGOS);
		
	}

	/**
	 * {@inheritDoc}
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.i(TAG, "upgrade database from {"+oldVersion+"} to {"+newVersion+"}");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO.TABLE_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_CARONA.TABLE_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_AMIGOS.TABLE_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_DESTINOS.TABLE_NAME);		
		
		//Chamando o metodo de construcao da base de dados
		onCreate(database);
	}
	
	
	
	@Override
	public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub		
		Log.i(TAG, "Downgrade database from {"+oldVersion+"} to {"+newVersion+"}");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO.TABLE_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_CARONA.TABLE_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_AMIGOS.TABLE_NAME);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_DESTINOS.TABLE_NAME);				
		//Chamando o metodo de construcao da base de dados
		onCreate(database);
	}
}