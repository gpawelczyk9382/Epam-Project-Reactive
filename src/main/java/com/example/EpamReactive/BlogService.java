package com.example.EpamReactive;


import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@AllArgsConstructor
public class BlogService {

	@Autowired
	private BlogInterface blogInterface;
	
	//Create operation
	public void create(String BlogCreator, String BlogTitle, String BlogText, String BlogType, String BlogParent) {
	    Date date = new Date();	     
	    blogInterface.save(new Blog(ObjectId.get(), BlogCreator, BlogTitle, BlogText, date,BlogType,BlogParent)).subscribe();
	}
	
	//Retrieve all records operation
	public Flux<Blog> getAll(){
		return blogInterface.findAll().switchIfEmpty(Flux.empty());	
	}
	
	public Mono<Blog> getById(ObjectId Id){
		return blogInterface.findBy_id(Id);		
	}
	
	
	//Update blog record
	public void update(ObjectId Id, String BlogTitle, String BlogText) {

        blogInterface.findBy_id(Id)
                .flatMap(blog -> {
            		if(!BlogTitle.equals("NV")) {
            			blog.setBlogTitle(BlogTitle);
            		}

            		if(!BlogText.equals("NV")) {
            			blog.setBlogText(BlogText);
            		}
                    return blogInterface.save(blog);
                }).subscribe();
		
	}
	
	public void deleteAll(){
		blogInterface.deleteAll().subscribe();
	}
	

	public Flux<Blog> getByBlogCreator(String blogCreator){		
		return blogInterface.findByBlogCreator(blogCreator).switchIfEmpty(Flux.empty())
				.sort( (obj2, obj1) -> obj1.getBlogPublishDt().compareTo(obj2.getBlogPublishDt()));
		
	}
	
}
