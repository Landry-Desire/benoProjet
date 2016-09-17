package benoProjet.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="User")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8897117888817851683L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Size(max=30)
	private String nom;
	@Column(length=30)
	private String prenom;
	private String hierachie;
	private String groupeId;
	private String mdp;
	private String email;
	private Boolean valide;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getGroupeId() {
		return groupeId;
	}
	public void setGroupeId(String groupeId) {
		this.groupeId = groupeId;
	}
	public String getHierachie() {
		return hierachie;
	}
	public void setHierachie(String hierachie) {
		this.hierachie = hierachie;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getValide() {
		return valide;
	}
	public void setValide(Boolean valide) {
		this.valide = valide;
	}
}
