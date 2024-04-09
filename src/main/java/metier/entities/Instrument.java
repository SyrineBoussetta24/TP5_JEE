package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "INSTRUMENTS")
public class Instrument implements Serializable {
	
	@Id
	@Column (name="ID_INSTRUMENT")
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idInstrument;
	
	@Column (name="NOM_INSTRUMENT")
	private String nomInstrument;
	private double prix;
	
	  @ManyToOne
	private Type type;
	public Instrument(String nomInstrument, double prix, Type type) {
		super();
		this.nomInstrument = nomInstrument;
		this.prix = prix;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Instrument() {
		super();
	}

	public Instrument(String nomInstrument, double prix) {
		super();
		this.nomInstrument = nomInstrument;
		this.prix = prix;
	}

	public Long getIdInstrument() {
		return idInstrument;
	}

	public void setIdInstrument(Long idInstrument) {
		this.idInstrument = idInstrument;
	}

	public String getNomInstrument() {
		return nomInstrument;
	}

	public void setNomInstrument(String nomInstrument) {
		this.nomInstrument = nomInstrument;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Instrument [idInstrument=" + idInstrument + ", nomInstrument=" + nomInstrument + ", prix=" + prix + "]";
	}
}
