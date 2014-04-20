package model;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;

@Entity
@NamedQuery(name="Tower.findAll", query="SELECT t FROM Tower t")
@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Tower  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;

	@XmlAttribute
	private double height;

	@XmlAttribute
	private String name;

	@XmlAttribute
	private int sides;

	@OneToMany(mappedBy="tower", cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="equipment")
	private List<Equipment> equipments;

	@ManyToOne
	@JoinColumn(name="siteId")
	@XmlTransient
	private Site site;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSides() {
		return this.sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}

	public List<Equipment> getEquipments() {
		return this.equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Tower(double height, String name, int sides,
			List<Equipment> equipments, Site site) {
		super();
		this.height = height;
		this.name = name;
		this.sides = sides;
		this.equipments = equipments;
		this.site = site;
	}

	public Tower() {
		super();
	}

}