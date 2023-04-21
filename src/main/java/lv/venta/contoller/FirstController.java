package lv.venta.contoller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

	private ArrayList<Product> products = new ArrayList<>(List.of(
			new Product("Orange", "fresh", 3.4f, 10), 
			new Product("Kiwi", "green", 5.5f, 3), 
			new Product("Pear", "yellow", 8.3f, 7)));

	@GetMapping("/hello") //localhost:8080/hello
	public String getHelloFunc() {
		System.out.println("Sveiki!");
		return "hello-page"; //there will be hello-page.html
	}
	

	@GetMapping("/msg")  //localhost:8080/msg
	public String getMsgFunc(Model model) {
		model.addAttribute("packet", "Message from Sonja");
		return "msg-page";	//will show msg-page.html
	}
	
	@GetMapping("/one-product")  //localhost:8080/one-product
	public String getOneProductFunc(Model model) {
		Product prod = new Product("Apple", "tasty", 1.2f, 9);
		model.addAttribute("packet", prod);
		return "one-product"; 	//will show one-product.html
	}
	
	//TODO create an ArrayList with 3 products
	@GetMapping("/all-products")	//localhost:8080/three-products
	public String getThreeProductsFunc(Model model) {
		model.addAttribute("packet", products);
		return "three-products";		//will show products-page.html
	}
	
	
	//TODO controller for localhost:8080/all-products-find?id=2
	@GetMapping("/all-products-find")	//localhost:8080/all-products-find?id=2
	public String getAllProductsFindFunc(@RequestParam("id") long id, Model model) {	//obligati tiesi tadu patu nosaukumu iekavas rakstit, kas ir pec ? zime; lai nesajaukt, lietojam tadu pasu nosaukumu visur
		if(id > 0) {
			for(Product temp : products) {
				if(temp.getId() == id) {
					model.addAttribute("packet", temp);
					return "one-product";	//will call one-product.html
				}
			}
		}
		model.addAttribute("packetError", "wrong ID");
		return "error-page";	//will call error-page.html
	}
	
	//TODO controller for localhost:8080/all-products/2
	@GetMapping("/all-products/{id}")	//localhost:8080/all-products-find/2
	public String getAllProductsByIdFunc(@PathVariable("id") long id, Model model) {	
		if(id > 0) {
			for(Product temp : products) {
				if(temp.getId() == id) {
					model.addAttribute("packet", temp);
					return "one-product";	//will call one-product.html
				}
			}
		}
		model.addAttribute("packetError", "Wrong ID");
		return "error-page";	//will call error-page.html
	}
	
	@GetMapping("/add-product") //localhost:8080/add-product
	public String getAddProductFunc(Model model) {
		model.addAttribute("product", new Product());	//send and empty product
		return "add-product-page";	//will call add-product-page.html
	}
	
	@PostMapping("/add-product")
	public String postAddProductFunc(Product product) {	//product nosaukums jaasakrit ar nosaukumu, kurs atrodas html failaa; product with all parameters 
		//TODO verify if this product already exists
		Product newProduct = new Product(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
		products.add(newProduct);	//pievienojam produktus pie product list
		
		return "redirect:/all-products";	//will call /all-products endpoint
	}
	
	@GetMapping("/update-product/{id}")	//localhost:8080/update-product/2
	public String getUpdateProductFunc(@PathVariable("id") long id, Model model) {
		if(id > 0) {
			for(Product temp : products) {
				if(temp.getId() == id) {
					model.addAttribute("product", temp);
					return "update-product-page";	//will call update-product-page.html
				}
			}
		}
		model.addAttribute("packetError", "Wrong ID");
		return "error-page";
	}

	@PostMapping("/update-product/{id}")
	public String postUpdateProductFunc(@PathVariable("id") long id, Product product) {	//edited product
		for(Product temp : products) {
			if(temp.getId() == id) {
				temp.setTitle(product.getTitle());
				temp.setDescription(product.getDescription());
				temp.setPrice(product.getPrice());
				temp.setQuantity(product.getQuantity());
				
				return "redirect:/all-products/"+id;	//will call localhost:8080/all-products/2 endpoint
			}
		}
		return "redirect:/error";	//will call localhost:8080/error
	}
	
	@GetMapping("/error")	//localhost:8080/error
	public String getErrorFunc(Model model) {
		model.addAttribute("packetError", "Wring id");
		return "error-page";	//will call error-page.html
	}
	}
