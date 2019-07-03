package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("brand")
public interface BrandApi {
    @GetMapping("/{bid}")
    Brand queryBrandById(@PathVariable("bid") Long id);


    @GetMapping("list")
    List<Brand> queryByIds(@RequestParam("ids") List<Long> ids);
}
