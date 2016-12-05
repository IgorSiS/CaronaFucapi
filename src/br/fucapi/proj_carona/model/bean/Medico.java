package br.fucapi.proj_carona.model.bean;

@SuppressWarnings("serial")
public class Medico extends Usuario {

	private String crm;

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
}
