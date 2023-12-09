package com.mains.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mains.bin.Book;
import com.mains.bin.Cart;
import com.mains.repositry.AdminRepositry;
import com.mains.repositry.MyBookRepository;
import com.mains.repositry.UserRepositry;




@Controller
public class handaler {
	@Autowired
	private UserRepositry repo;
	@Autowired
	private MyBookRepository  cartRepository; // Assuming you have a CartRepository
	@Autowired
	 private AdminRepositry adrepo;

	@RequestMapping("/")
	public String dis()
	{
		return "home";
	}
	@RequestMapping("/regis")
	public String display()
	{
		return "registration";
	}
	@PostMapping("/success")
	public String data(@ModelAttribute Book bok) {
	    System.out.println(bok);
	    repo.save(bok);
	    return "/home";
	}
	
	@RequestMapping("/view")
	public String getData(Model m) {
	    List<Book> li = repo.findAll();
	    m.addAttribute("e_data",li);  
	    System.out.println(li);
	    return "view_book";
	}
	
	@RequestMapping("/{id}")
	public String dlt_book(@PathVariable Integer id)
	{
		repo.deleteById(id);
		
		return "redirect:/admin";
		
	}
	 @GetMapping("/edit/{id}")
		public String editData( @PathVariable Integer id, Model m)
		{
			Book bok= repo.findById(id).orElse(null);
			m.addAttribute("book",bok); 
			return "update";
		}
	 @PostMapping("/update/{id}")
	 public String updateData(@PathVariable Integer id,@ModelAttribute Book updatedemp)
	 {
		 Book bok= repo.findById(id).orElse(null);
		 if(bok!=null)
		 {   
			 bok.setId(updatedemp.getId());
			 bok.setTitle(updatedemp.getTitle());
			 bok.setAuthor(updatedemp.getAuthor());
			 bok.setPrice(updatedemp.getPrice());
			 repo.save(bok);
			 
		 }
		 return "redirect:/admin";
	 }

	 
	 @RequestMapping("/cart")
	 public String getMyBooks(Model model) {
		    List<Cart> list =cartRepository.findAll();
		    model.addAttribute("cartItems", list);
		    System.out.print(list);
		    return "cart";
		}

	
	 @RequestMapping("/my_cart/{id}")
		public String getMyList(@PathVariable("id") int id) {
			Book b=repo.getById(id);
			Cart mb=new Cart(b.getId(),b.getTitle(),b.getAuthor(),b.getPrice());
			cartRepository.save(mb);
			return "redirect:/cart";
		}
	 
	 @RequestMapping("/deletecart/{id}")
		public String deleteMyList(@PathVariable("id") int id) {
		 cartRepository.deleteById(id);
			return "redirect:/cart";
		}
	 
	 @RequestMapping("/delete")
		public String deleteRecord(@RequestParam("id")Integer id) {
		 repo.deleteById(id);
			return "redirect:/view";
		}
	 
	 
	
	 
	/*@RequestMapping("/admin")
	public String avi()
	{
		return "/adminview";
	}*/
	
	
	@RequestMapping("/admin2")
	
		public String admin2()
		{
		return"admin_page";
	}
	
	 @RequestMapping("/login")
	    public String admin() {
	        return "adminlogin";
	    }
	
	 @PostMapping("/login/authenticate")
	    public String authenticate(@RequestParam String password) {
	        if ("admin".equals(password)) {
	            return "redirect:/admin";
	        } else {
	            return "redirect:/login";
	        }
	    }
	 
	 
	 @GetMapping("/search")
	 public String searchBooks(@RequestParam(name = "id", required = false) Integer id, Model model) {
		    if (id != null) {
		        Book book = repo.findById(id).orElse(null);


		        if (book != null) {
		            model.addAttribute("e_data", Collections.singletonList(book));
		        } else {
		            model.addAttribute("e_data", Collections.emptyList());
		        }
		    } else {
		        model.addAttribute("e_data", Collections.emptyList());
		    }

		    return "admin_page";
		}
	 @RequestMapping("/order")
	    public String orderInfo(Model model) {
		 List<Cart> cartItems = cartRepository.findAll();
		    model.addAttribute("cartItems", cartItems);
		    return "orderdet";
	    }
	 
	 @RequestMapping( path = "/placeOrder" , method = RequestMethod.POST)
		public String formHandler( @RequestParam("customerName") String e_customerName,
				                   @RequestParam("address") String address,
				                   @RequestParam("phone")  String phone ,
		                          
		                           Model m) {
		 List<Cart> cartItems =  cartRepository.findAll();
				    m.addAttribute("cartItems", cartItems);
			
			m.addAttribute("NAME", e_customerName);
			m.addAttribute("ADDRESS", address);
			m.addAttribute("PHONE", phone);
			
			System.out.println(e_customerName+"\t"+address+"\t"+phone+"\t");
			
			
			return "Recipt";
		}
}
	
	
	

	