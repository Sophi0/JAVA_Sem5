package lv.venta.contoller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.services.ICRUDProductService;

@Controller
public class FirstController {
	
	@Autowired
	private ICRUDProductService CRUDservice;

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
		model.addAttribute("packet", CRUDservice.retrieveAllProducts());
		return "three-products";		//will show products-page.html
	}
	

	//TODO controller for localhost:8080/all-products-find?id=2
	@GetMapping("/all-products-find")	//localhost:8080/all-products-find?id=2
	public String getAllProductsFindFunc(@RequestParam("id") long id, Model model) {	//obligati tiesi tadu patu nosaukumu iekavas rakstit, kas ir pec ? zime; lai nesajaukt, lietojam tadu pasu nosaukumu visur
		try {
			Product prod = CRUDservice.retrieveProductById(id);
			model.addAttribute("packet", prod);
			return "one-product";	//will call one-product.html
		}
		catch (Exception e){
			model.addAttribute("packetError", e.getMessage());
			return "error-page";	//will call error-page.html
		}
	}
	

	//TODO controller for localhost:8080/all-products/2
	@GetMapping("/all-products/{id}")	//localhost:8080/all-products-find/2
	public String getAllProductsByIdFunc(@PathVariable("id") long id, Model model) {	
		try {
			Product prod = CRUDservice.retrieveProductById(id);
			model.addAttribute("packet", prod);
			return "one-product";	//will call one-product.html
		}
		catch (Exception e){
			model.addAttribute("packetError", e.getMessage());
			return "error-page";	//will call error-page.html
		}
	}
	
	@GetMapping("/add-product") //localhost:8080/add-product
	public String getAddProductFunc(Model model) {
		model.addAttribute("product", new Product());	//send and empty product
		return "add-product-page";	//will call add-product-page.html
	}
	
	@PostMapping("/add-product")
	public String postAddProductFunc(Product product) {	//product nosaukums jaasakrit ar nosaukumu, kurs atrodas html failaa; product with all parameters 
		//TODO verify if this product already exists
		try {
			CRUDservice.addNewProduct(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
			return "redirect:/all-products";
		}
		catch (Exception e){
			return "redirect:/error";	
		}
	}

	@GetMapping("/update-product/{id}")	//localhost:8080/update-product/2
	public String getUpdateProductFunc(@PathVariable("id") long id, Model model) {
		try {
			Product prod = CRUDservice.retrieveProductById(id);
			model.addAttribute("product", prod);
			return "update-product-page";
		}
		catch (Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";	//will call error-page.html
		}
	}

	@PostMapping("/update-product/{id}")
	public String postUpdateProductFunc(@PathVariable("id") long id, Product product) {	//edited product
		try {
			CRUDservice.updateById(id, product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
			return "update-product-page";
		}
		catch (Exception e) {
			return "error-page";
		}
	}
	
	@GetMapping("/error")	//localhost:8080/error
	public String getErrorFunc(Model model) {
		model.addAttribute("packetError", "Wrong id");
		return "error-page";	//will call error-page.html
	}
	
	@GetMapping("/delete-product/{id}")	//localhost:8080/delete-product/2
	public String getDeleteProductFunc(@PathVariable("id") long id, Model model) {
		try {
			CRUDservice.deleteProductById(id);
			model.addAttribute("packet", CRUDservice.retrieveAllProducts());
			return "three-products";
		}
		catch(Exception e) {
			model.addAttribute("packetError", e.getMessage());
			return "error-page";
		}
	}

}
