package com.atguigu.cloud.resp;


import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class ResultData<T> {
    //返回的状态码
    private String code;
    //返回的信息
    private String message;
    //返回的数据
    private T data;
    //返回的时间戳
    private long timestamp;

    public ResultData(){
        this.timestamp= System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data){
        ResultData<T> resultData = new ResultData<>();
        resultData.setData(data)
                .setCode(ReturnCodenum.RC200.getCode())
                .setMessage(ReturnCodenum.RC202.getMessage());
        return resultData;
    }

    public static <T> ResultData<T> success(){
        ResultData resultData = new ResultData();
        resultData.setCode(ReturnCodenum.RC200.getCode())
                .setMessage(ReturnCodenum.RC202.getMessage());
        return resultData;
    }

    public static <T> ResultData<T> fail(String code,String message){
        ResultData resultData = new ResultData();

        resultData.setCode(code);
        resultData.setMessage(message);
        return resultData;
    }
}
