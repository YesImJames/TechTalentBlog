package com.techtalentsouth.repository;

import org.springframework.data.repository.CrudRepository;

import com.techtalentsouth.model.BlogPost;

public interface BlogPostRepository 
	extends CrudRepository<BlogPost, Long>{
		
	BlogPost getById(Long id);

	
}
