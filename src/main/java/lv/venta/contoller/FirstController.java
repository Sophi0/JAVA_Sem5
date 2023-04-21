package lv.venta.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

	
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
		return "product-page";
	}
}
