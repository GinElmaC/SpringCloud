//package com.atguigu.cloud.myGateway;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//@Slf4j
//public class MyGlobalFilter implements GlobalFilter, Ordered {
//    public static final String BEGIN_VISIT_TIME = "begin_visit_time";//开始调用方法的时间
//
//    //TODO这段代码用到了响应式编程，现在还不懂，过段时间补票再看一遍
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //先记录下访问接口的开始时间
//        //exchange就相当于我们web里的上下文context
//        exchange.getAttributes().put(BEGIN_VISIT_TIME,System.currentTimeMillis());
//        //返回各个统计的结果给后台
//        return chain.filter(exchange).then(Mono.fromRunnable(()->{
//            Long begin =  exchange.getAttribute(BEGIN_VISIT_TIME);
//            if(begin != null){
//                log.info("访问接口主机: " + exchange.getRequest().getURI().getHost());
//                log.info("访问接口端口: " + exchange.getRequest().getURI().getPort());
//                log.info("访问接口URL: " + exchange.getRequest().getURI().getPath());
//                log.info("访问接口URL参数: " + exchange.getRequest().getURI().getRawQuery());
//                log.info("访问接口时长: " + (System.currentTimeMillis() - begin) + "ms");
//                System.out.println();
//            }
//        }));
//    }
//
//    /**
//     * 数字越小这个过滤器的优先级越高
//     * @return
//     */
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
