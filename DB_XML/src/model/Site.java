package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


@Entity
@NamedQuery(name="Site.findAll", query="SELECT s FROM Site s")
@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Site  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;

	@XmlAttribute
	private double latitude;

	@XmlAttribute
	private double longitude;

	@XmlAttribute
	private String name;

	@OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="tower")
	private List<Tower> towers;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tower> getTowers() {
		return this.towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

	public Site(double latitude, double longitude, String name,
			List<Tower> towers) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.towers = towers;
	}

	public Site() {
		super();
	}

}