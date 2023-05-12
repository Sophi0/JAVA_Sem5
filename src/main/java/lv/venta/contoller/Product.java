package lv.venta.contoller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name = "product_table")	//this will be a table in DB; mes rakstam table galvena klases nosaukuma
@Entity
public class Product {
	
	@Column(name = "Title") //H2 -> TITlE, Mysql -> title
	@NotNull
	@Size(min = 3, max = 150)
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latinletters and space")  	//only latin letters; + nozime neierobezoti daudz simbolus
	//katru parametru validejam
	//piemeram, lai title nebutu null un uzstaditas min un max vertibas
	private String title;
	
	@Column(name = "description")
	@NotNull
	@Size(min = 5, max = 400)
	@Pattern(regexp = "[A-Z]{1}[a-z0-9\\ ]+", message = "Only latinletters and space")
	private String description;
	
	@Column(name = "price")
	@Min(0)
	@Max(10000)
	private float price;
	
	@Column(name = "quantity")
	@Min(0)
	@Max(1000000)
	private int quantity;
	
	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getId() {
		return id;
	}
	

	public Product(String title, String description, float price, int quantity) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Product() {}

	@Override
	public String toString() {
		return "" + title + " " + description + " " + price + " " + quantity;
	}
}
