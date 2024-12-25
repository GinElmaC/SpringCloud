//package com.atguigu.cloud.myGateway;
//
//import jakarta.validation.constraints.NotEmpty;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {
//    @Override
//    public GatewayFilter apply(MyGatewayFilterFactory.Config config) {
//        //这里我们模拟请求头式过滤器，当请求头中有某个字段时就返行，不管值是多少。
//        //这个return是固定写法
//        return new GatewayFilter() {
//            @Override
//            //这里的exchange就类似于上下文，里面有请求的相应信息，chain就是过滤器链
//            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//                //ServerHttpRequest继承了HttpRequest，可以获取请求信息并且有更加丰富的方法
//                //获取请求的所有信息
//                ServerHttpRequest request = exchange.getRequest();
//                System.out.println("进入了自定义网管过滤器");
//                //如果请求参数中有"atguigu"的字段
//                if(request.getQueryParams().containsKey("atguigu")){
//                    //放行，交给过滤器链的下一个过滤器
//                    return chain.filter(exchange);
//                }else{
//                    //不放行
//                    exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
//                    return exchange.getResponse().setComplete();
//                }
//            }
//        };
//    }
//
//    public static class Config{
//        @Setter@Getter@NotEmpty
//        private String status;
//    }
//
//    @Override
//    public List<String> shortcutFieldOrder() {
//        return Arrays.asList("status");
//    }
//
//    public MyGatewayFilterFactory(){
//        super(Config.class);
//    }
//}
