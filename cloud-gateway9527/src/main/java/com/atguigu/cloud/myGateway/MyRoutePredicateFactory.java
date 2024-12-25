package com.atguigu.cloud.myGateway;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.AfterRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {
    //空参构造调用super
    public MyRoutePredicateFactory(){
        super(MyRoutePredicateFactory.Config.class);
    }

    //config内部类
    @Validated
    public static class Config{
        @Setter
        @Getter
        @NotEmpty
        private String userType;
    }

    //配置shortcuts的需要
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    /**
     * 重写apply方法
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //获取请求参数中key是userType的数据，没有则获取null
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                //判断是否为null
                if(userType == null){
                    return false;
                }
                //判断是否为我们config规定的会员等级
                if(userType.equalsIgnoreCase(config.getUserType())){
                    return true;
                }
                return false;
            }
        };
    }
}
