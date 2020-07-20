package com.example.EpamReactive;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class EPAMController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("/SubmitBlog")
	public void SubmitBlog(@RequestParam("BlogCreator") String BlogCreator, 
							@RequestParam(value = "BlogTitle", defaultValue = "No Value") String BlogTitle,
							@RequestParam(value = "BlogText", defaultValue = "No Value") String BlogText)
	{	
		blogService.create(BlogCreator,BlogTitle,BlogText,"B","PARENT");
	}

	@GetMapping("/GetAllBlog")
	public Flux<Blog> GetAll()
	{	
		return blogService.getAll();
	}
	
	
	@GetMapping("/DeleteAll")
	public void DeleteAll()
	{	
		blogService.deleteAll();
	}
	
	@GetMapping("/ViewContent")
	public Flux<Blog> ViewContent(@RequestParam(value = "BlogCreator", defaultValue = "No Value") String BlogCreator)
	{
		return blogService.getByBlogCreator(BlogCreator);
	}
	
	@GetMapping("/UpdateBlog")
	public void UpdateBlog(@RequestParam("Id") ObjectId Id,
			@RequestParam(value = "BlogTitle", defaultValue = "NV") String BlogTitle,
			@RequestParam(value = "BlogText", defaultValue = "NV") String BlogText)
	{
		blogService.update(Id, BlogTitle, BlogText);
	}
		
		
	@GetMapping("/CommentBlog")
	public void CommentBlog(@RequestParam("Id") String Id,
							@RequestParam("BlogCommentCreator") String BlogCommentCreator,
							@RequestParam("BlogCommentText") String BlogCommentText)
	{		
		blogService.create(BlogCommentCreator,"",BlogCommentText,"C",Id);
	}
}
