package com.techtalentsouth.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techtalentsouth.model.BlogPost;
import com.techtalentsouth.repository.BlogPostRepository;

@Controller
public class BlogPostController {

	@Autowired
	BlogPostRepository blogPostRepository;
	
	@GetMapping("/")
	public String homePage (BlogPost blogPost, Model model) {
		ArrayList<BlogPost> posts = new ArrayList<>();
		posts =(ArrayList<BlogPost>) this.blogPostRepository.findAll();
		
		model.addAttribute("posts", posts);
		
		return "blogpost/list.html";
	}
	
	@GetMapping("/new")
	public String index(BlogPost blogPost) {

		return "blogpost/new.html";
	}
	
	@GetMapping("/edit/{blogId}")
	public String editPage(@PathVariable Long blogId, BlogPost blogPost) {
		BlogPost dbEntry = new BlogPost();
		dbEntry = this.blogPostRepository.getById(blogId);
		
		blogPost.setId(dbEntry.getId());
		blogPost.setTitle(dbEntry.getTitle());
		blogPost.setBlogEntry(dbEntry.getBlogEntry());
		blogPost.setAuthor(dbEntry.getAuthor());
		
		return "blogpost/edit2.html";
		
		
		
	}
	
	@PostMapping("/new")
	public String addBlogEntry (BlogPost blogPost, Model model) {
		this.blogPostRepository.save(blogPost);
		
		model.addAttribute("id", blogPost.getId());
		model.addAttribute("title", blogPost.getTitle());
		model.addAttribute("author", blogPost.getAuthor());
		model.addAttribute("blogEntry", blogPost.getBlogEntry());
		
		return "blogpost/result.html";
	}
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
    	this.blogPostRepository.deleteById(id);
    	
    	ArrayList<BlogPost> posts = new ArrayList<>();
    	posts = (ArrayList<BlogPost>) this.blogPostRepository.findAll();
    	
    	model.addAttribute("posts", posts);
    	
        return "blogpost/list.html";

    }
	
}
