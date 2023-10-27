package com.blog.app.help;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    @Autowired
    private ModelMapper mapper;
    public static <V,U>PageableResponse<V> getPageable(Page<U> page, Class<V> type)
    {
        List<U> entities = page.getContent();
        List<V> dto = entities.stream().map(entity -> new ModelMapper().map(entity, type)).collect(Collectors.toList());
        PageableResponse pageableResponse=new PageableResponse<>();
        pageableResponse.setContent(dto);
        pageableResponse.setPageNumber(page.getNumber());
        pageableResponse.setLastPage(page.isLast());
        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setPageSize(page.getSize());
        pageableResponse.setTotalElements(page.getTotalElements());
        return pageableResponse;
    }
}
