package com.leyou.cart.service;

import com.leyou.auth.pojo.UserInfo;
import com.leyou.cart.interceptor.UserInterceptor;
import com.leyou.cart.pojo.Cart;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    private final static String KEY_PRIFIX = "cart:uid:";

    public void addCart(Cart cart) {
        //获取登陆用户
        UserInfo user = UserInterceptor.getInfo();
        //获取key
        String key = KEY_PRIFIX + user.getId();
        //hasKey
        //记录数量
        Integer num = cart.getNum();
        String hasKey = cart.getSkuId().toString();
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);
        //判断用户购物车是否存在
        if (operation.hasKey(hasKey)) {
            //存在，修改
            String json = operation.get(hasKey).toString();
            cart = JsonUtils.toBean(json, Cart.class);
            cart.setNum(cart.getNum() + num);
            operation.put(hasKey, JsonUtils.toString(cart));
        }
        //写回redis
        operation.put(hasKey, JsonUtils.toString(cart));
    }

    public List<Cart> queryCartList() {
        //获取登陆用户
        UserInfo user = UserInterceptor.getInfo();
        //获取key
        String key = KEY_PRIFIX + user.getId();
        if (!redisTemplate.hasKey(key)) {
            throw new LyException(ExceptionEnum.CART_NOT_FOUND);
        }
        //取值
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);
        List<Cart> carts = operation.values().stream().map(o -> JsonUtils
                .toBean(o.toString(), Cart.class)).collect(Collectors.toList());
        return carts;
    }

    public void updateNum(Long skuId, Integer num) {
        //获取登陆用户
        UserInfo user = UserInterceptor.getInfo();
        //获取key
        String key = KEY_PRIFIX + user.getId();
        String hasKey = skuId.toString();
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);
        if (!operation.hasKey(hasKey)) {
            throw new LyException(ExceptionEnum.CART_NOT_FOUND);
        }
        Cart cart = JsonUtils.toBean(operation.get(hasKey).toString(), Cart.class);
        cart.setNum(num);

        operation.put(hasKey,JsonUtils.toString(cart));
    }

    public void deleteCart(Long skuId) {
        //获取登陆用户
        UserInfo user = UserInterceptor.getInfo();
        //获取key
        String key = KEY_PRIFIX + user.getId();

        redisTemplate.opsForHash().delete(key,skuId.toString());
    }
}
