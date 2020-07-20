package com.example.EpamReactive;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BlogInterface extends ReactiveMongoRepository<Blog, String>
{
	public Flux<Blog> findByBlogCreator(String blogCreator);
	public Mono<Blog> findBy_id(ObjectId _id);
}
