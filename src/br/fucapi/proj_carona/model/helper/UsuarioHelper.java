package br.fucapi.proj_carona.model.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.fucapi.proj_carona.R;
import br.fucapi.proj_carona.model.bean.Usuario;
import br.fucapi.proj_carona.utils.Funcoes;
import br.fucapi.proj_carona.utils.Mask;
import br.fucapi.proj_carona.utils.SpinnerAdapter;
import br.fucapi.proj_carona.utils.SpinnerObject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class UsuarioHelper {
	
	private EditText nome;
	private Spinner sexo;		
	private EditText cpf;
	
	private EditText registroAcademico;

	private EditText nomeCurso;
	private EditText turno;
	
	private EditText celular;
	private EditText contatoEmergencia;
		
	private EditText endereco;

	private EditText numero;
	private EditText cep;	
	
							
	private ImageView foto;
	
	private EditText login;	
	private EditText senha;	
	
	

	private Usuario usuario;
	private Context context;		        		        
	private FragmentActivity fragmentActivity;
	private boolean onCreateFlag = true;
	
    //private Calendar myCalendar;


	private Map<View, String> mapCamposObrigatorios = new LinkedHashMap<View, String>();
	
	public Map<View, String> getMapCamposObrigatorios() {
		return mapCamposObrigatorios;
	}

	public void setMapCamposObrigatorios(Map<View, String> mapCamposObrigatorios) {
		this.mapCamposObrigatorios = mapCamposObrigatorios;
	}

	

	//private ArrayAdapter<String> adapter;
	SpinnerAdapter adapter;
	
	public UsuarioHelper(final FragmentActivity fragmentActivity){
		//Associacao de campos de tela ao controller
		this.context = fragmentActivity;						
		this.fragmentActivity = fragmentActivity;
				
	   	    				
		usuario = new Usuario();
		
		nome = (EditText) fragmentActivity.findViewById(R.id.edNome);
		
		registroAcademico = (EditText) fragmentActivity.findViewById(R.id.edRegistroAcademico);
		nomeCurso = (EditText) fragmentActivity.findViewById(R.id.edNomeCurso);
		turno = (EditText) fragmentActivity.findViewById(R.id.edTurno);
				
		
		foto = (ImageView) fragmentActivity.findViewById(R.id.foto);
				
		cpf = (EditText) fragmentActivity.findViewById(R.id.edCpf);
		cpf.addTextChangedListener(Mask.insert("###.###.###-##", cpf));
		
		
		endereco = (EditText) fragmentActivity.findViewById(R.id.edEndereco);					
		numero = (EditText) fragmentActivity.findViewById(R.id.edNumero);
		
		cep = (EditText) fragmentActivity.findViewById(R.id.edCep);
		cep.addTextChangedListener(Mask.insert("#####-###", cep));
		
		celular = (EditText) fragmentActivity.findViewById(R.id.edCelular);
		celular.addTextChangedListener(Mask.insert("(##)####-####", celular));
		
		contatoEmergencia = (EditText) fragmentActivity.findViewById(R.id.edContatoEmergencia);
		contatoEmergencia.addTextChangedListener(Mask.insert("(##)####-####", contatoEmergencia));
		
		login = (EditText) fragmentActivity.findViewById(R.id.edLogin);
		senha = (EditText) fragmentActivity.findViewById(R.id.edSenha);	
		
		
		sexo = (Spinner) fragmentActivity.findViewById(R.id.spinSexo);		
		
		
		
		sexo.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				if(view !=null){
					
					Funcoes.hideKeyboard(fragmentActivity );
					view.performClick();
					sexo.requestFocusFromTouch();					
					//return true;
				}
				return false;
			}
        });
						
				
		
		List<SpinnerObject> listSexo = new ArrayList<SpinnerObject>();
		listSexo.add( new SpinnerObject(fragmentActivity.getResources().getString(R.string.masculino ) , R.drawable.ic_man ) );
		listSexo.add( new SpinnerObject(fragmentActivity.getResources().getString(R.string.feminino ) , R.drawable.ic_woman ) );
								
		String[] StringArray = new String[listSexo.size()];
		int i=0;
		for(SpinnerObject s: listSexo){
			StringArray[i++] = s.toString();
		}				
		
		adapter = new SpinnerAdapter(fragmentActivity, R.layout.spinner_sexo,StringArray,listSexo );		
	    sexo.setAdapter(adapter);
	    
	    

		mapCamposObrigatorios.put(nome, fragmentActivity.getResources().getString(R.string.erro_campo_requerido));
		mapCamposObrigatorios.put(registroAcademico, fragmentActivity.getResources().getString(R.string.erro_campo_requerido));
		mapCamposObrigatorios.put(cpf, fragmentActivity.getResources().getString(R.string.erro_campo_requerido));					
		mapCamposObrigatorios.put(celular, fragmentActivity.getResources().getString(R.string.erro_campo_requerido));
		mapCamposObrigatorios.put(contatoEmergencia, fragmentActivity.getResources().getString(R.string.erro_campo_requerido));
		//mudar nos helpers, add metodos
		setViewCamposObrigatorios();	
	}
		
public void setViewCamposObrigatorios(){
		
		
		for(final View chave: mapCamposObrigatorios.keySet()){
		    //System.out.println("chave: "+chave+", valor: "+mapaDeCampos.get(chave)+".");
												
			if (chave instanceof EditText) {
				
				final EditText edTexto = (EditText) chave;
				//final Editable texto = edTexto.getText();
								
				
				final Drawable errorIcon = fragmentActivity.getResources().getDrawable(R.drawable.ic_obrigatorio);
		    	errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));
		    	//errorIcon.setColorFilter(Color.RED ,PorterDuff.Mode.MULTIPLY);
		    	
		    	edTexto.setError(null,errorIcon);
				
		    	edTexto.addTextChangedListener(new TextWatcher() {
		    	    public void onTextChanged(CharSequence s, int start, int before, int count) {
		    	    	edTexto.setError(null,errorIcon);
		    	    }
		    	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		    	    	edTexto.setError(null,errorIcon);
		    	    }

		    	    public void afterTextChanged(Editable s) {
		    	        
		    	    	/*		    	    	
		    	    	if(Funcoes.validarDados(chave,  mapCamposObrigatorios.get(chave) ) == true){
		    	    		if(chave.equals(dataNascimento)){		    							    					
		    	    			Funcoes.validarDateFormat(chave, DATE_FORMAT, fragmentActivity.getResources().getString(R.string.erro_data_invalida));
		    	    		}		    				
		    			}
		    	    	*/		    	    			    	    	
		    	    	
		    	    }
		    	});
		    			    	
				
				   
			}   
		}													
		
	}
	
				
			

	public EditText getNome() {
		return nome;
	}

	public void setNome(EditText nome) {
		this.nome = nome;
	}

	public EditText getCpf() {
		return cpf;
	}

	public void setCpf(EditText cpf) {
		this.cpf = cpf;
	}
	
	public EditText getEndereco() {
		return endereco;
	}

	public void setEndereco(EditText endereco) {
		this.endereco = endereco;
	}

	
	public EditText getNumero() {
		return numero;
	}

	public void setNumero(EditText numero) {
		this.numero = numero;
	}

	public EditText getCep() {
		return cep;
	}

	public void setCep(EditText cep) {
		this.cep = cep;
	}


	public EditText getCelular() {
		return celular;
	}

	public void setCelular(EditText celular) {
		this.celular = celular;
	}
	
	
	public ImageView getFoto() {
		return foto;
	}

	public void setFoto(ImageView foto) {
		this.foto = foto;
	}

	

	
	public boolean validar(){
			
		for(View chave: mapCamposObrigatorios.keySet()){
		    //System.out.println("chave: "+chave+", valor: "+mapaDeCampos.get(chave)+".");
			if(Funcoes.validarDados(chave,  mapCamposObrigatorios.get(chave) ) == false){
				return false;
			}
		}
				
			return true;		
		
	}

	public Spinner getSexo() {
		return sexo;
	}

	public void setSexo(Spinner sexo) {
		this.sexo = sexo;
	}
	
	public EditText getRegistroAcademico() {
		return registroAcademico;
	}

	public void setRegistroAcademico(EditText registroAcademico) {
		this.registroAcademico = registroAcademico;
	}

	public EditText getNomeCurso() {
		return nomeCurso;
	}

	public void setNome_curso(EditText nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public EditText getTurno() {
		return turno;
	}

	public void setTurno(EditText turno) {
		this.turno = turno;
	}

	public EditText getContatoEmergencia() {
		return contatoEmergencia;
	}

	public void setContatoEmergencia(EditText contatoEmergencia) {
		this.contatoEmergencia = contatoEmergencia;
	}
	
	public EditText getLogin() {
		return login;
	}

	public void setLogin(EditText login) {
		this.login = login;
	}

	public EditText getSenha() {
		return senha;
	}

	public void setSenha(EditText senha) {
		this.senha = senha;
	}

	public void setNomeCurso(EditText nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public void carregarFoto(String localFoto) {
		try {			
			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();									
			
			// downsizing image as it throws OutOfMemory Exception for larger
			// images
			options.inSampleSize = 8;			
			Bitmap bitmapFoto = BitmapFactory.decodeFile(localFoto,
					options);			
						
			if(bitmapFoto==null){
				Toast.makeText(context, "Impossivel abrir esse arquivo", Toast.LENGTH_SHORT).show();
				return;
			}else{
				
				try {
					bitmapFoto = Funcoes.applyOrientation(bitmapFoto,Funcoes.resolveBitmapOrientation(localFoto) );
					
					if(bitmapFoto.getWidth() < 100){
						bitmapFoto = BitmapFactory.decodeFile(localFoto);
					}
										
				} catch (IOException e) {
					Log.d("IMAGE_NOT_FOUND", e.getMessage() );
					
				}
			}			
				
			usuario.setFoto(localFoto);
			foto.setImageBitmap(bitmapFoto);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	
	public Usuario getUsuario(){
		
		usuario.setNome(getNome().getText().toString());				
		usuario.setSexo( String.valueOf(getSexo().getSelectedItem()) );
		usuario.setCpf(cpf.getText().toString());		
		
		
		usuario.setRegistroAcademico(registroAcademico.getText().toString());		
		usuario.setNomeCurso(nomeCurso.getText().toString());		
		usuario.setTurno(turno.getText().toString());		
		
		usuario.setCelular(getCelular().getText().toString());
		usuario.setContatoEmergencia(getContatoEmergencia().getText().toString());		
		
		
		usuario.setEndereco(getEndereco().getText().toString());
		usuario.setNumero(getNumero().getText().toString());
		usuario.setCep(getCep().getText().toString());
		
					
						
		
		//usuario.setLogin(usuario.getCpf());
		//usuario.setSenha(usuario.getCpf());
		usuario.setLogin(getLogin().getText().toString());
		usuario.setSenha(getSenha().getText().toString());				
				
		return usuario;		
	}

	
	public void setUsuario(Usuario usuario){
		
		getNome().setText(usuario.getNome());		
		
		adapter = (br.fucapi.proj_carona.utils.SpinnerAdapter) getSexo().getAdapter();
		List<SpinnerObject> list_sexo = adapter.getSpinnerObjects();
		int index=0, indexKey=0;		
		for (SpinnerObject sexo : list_sexo) {
			if(sexo.getValue().equals(usuario.getSexo()) ){
				indexKey = index;
				break;	
			}
			index++;					
		}
		getSexo().setSelection(indexKey);
		getCpf().setText(usuario.getCpf());
		
		getRegistroAcademico().setText(usuario.getRegistroAcademico());
		getNomeCurso().setText(usuario.getNomeCurso());
		getTurno().setText(usuario.getTurno());
		
		
		getCelular().setText(usuario.getCelular());
		getContatoEmergencia().setText(usuario.getContatoEmergencia());	
		
		
		getEndereco().setText(usuario.getEndereco());	
		getNumero().setText(usuario.getNumero());
		getCep().setText(usuario.getCep() );
		
		getLogin().setText(usuario.getLogin() );
		getSenha().setText(usuario.getSenha() );

		if (usuario.getFoto() != null) {
			carregarFoto(usuario.getFoto());
		}
		
		
			
		//usuario.setLogin(usuario.getCpf());
		//usuario.setSenha(usuario.getCpf());
		
		
		this.usuario = usuario;	
	//Validando dados
	}
	
	
}