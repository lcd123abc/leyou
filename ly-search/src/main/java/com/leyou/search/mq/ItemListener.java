package com.leyou.search.mq;

import com.leyou.search.service.SearchService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemListener {
    @Autowired
    private SearchService searchService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "search.user.insert.queue", durable = "true"),
            exchange = @Exchange(name = "ly.user.exchange",type = ExchangeTypes.TOPIC),
            key = {"user.insert","user.update"}
    ))
    public void ListenInsertOrUpdate(Long spuId){
        if(spuId == null){
            return;
        }
        //处理消息，对索引库进行新增或者修改
        searchService.createOrUpdateIndex(spuId);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "search.user.delete.queue", durable = "true"),
            exchange = @Exchange(name = "ly.user.exchange",type = ExchangeTypes.TOPIC),
            key = {"user.delete"}
    ))
    public void ListenDelete(Long spuId){
        if(spuId == null){
            return;
        }
        //处理消息，对索引库进行删除
        searchService.deleteIndex(spuId);
    }
}
