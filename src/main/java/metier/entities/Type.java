package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Type implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTyp;
	private String nomTyp;
	
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	@OneToMany(mappedBy = "type")
	private List<Instrument> instruments;

	public Type() {
		super();
	}

	public Type(String nomTyp, Date dateCreation) {
		super();
		this.nomTyp = nomTyp;
		this.dateCreation = dateCreation;
	}

	public Long getIdTyp() {
		return idTyp;
	}

	public void setIdTyp(Long idTyp) {
		this.idTyp = idTyp;
	}

	public String getNomTyp() {
		return nomTyp;
	}

	public void setNomTyp(String nomTyp) {
		this.nomTyp = nomTyp;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}
}