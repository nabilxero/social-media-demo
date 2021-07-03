package com.bs23test.socialmediademo.mapper;

import com.bs23test.socialmediademo.dto.PostDto;
import com.bs23test.socialmediademo.model.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostDto, Post>{
    PostDto toDto(Post post);
    Post toEntity(PostDto postDto);
}
