package lv.venta.contoller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("/three-products")	//localhost:8080/three-products
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
		model.addAttribute("packet-error", "wrong ID");
		return "error-page";	//will call error-page.html
	}
}
