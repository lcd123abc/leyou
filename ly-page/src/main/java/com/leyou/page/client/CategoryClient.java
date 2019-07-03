package com.leyou.page.client;


import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@FeignClient("item-service")
public interface CategoryClient extends CategoryApi {
}
