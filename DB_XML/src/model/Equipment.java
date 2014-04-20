package model;


import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@NamedQuery(name="Equipment.findAll", query="SELECT e FROM Equipment e")
@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Equipment  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;

	@XmlAttribute
	private String brand;

	@XmlAttribute
	private String description;

	@XmlAttribute
	private String name;

	@XmlAttribute
	private double price;

	@ManyToOne
	@JoinColumn(name="towerId")
	@XmlTransient
	private Tower tower;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Tower getTower() {
		return this.tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public Equipment(String brand, String description, String name,
			double price, Tower tower) {
		super();
		this.brand = brand;
		this.description = description;
		this.name = name;
		this.price = price;
		this.tower = tower;
	}

	public Equipment() {
		super();
	}

}