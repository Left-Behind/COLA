package com.alibaba.craftsman.common.lazy.test;

import com.alibaba.craftsman.common.lazy.LazyPending;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class TestLazyManager {


    /**
     * 返回当前数量
     * @param data
     * @return
     */
    public int putData(DataParam data) {
       return dataParamPending.doPending(data);
    }


    private DataParamPending dataParamPending;

    @PostConstruct
    private void init() {
        dataParamPending = new DataParamPending(100, 0);
    }

    private class DataParamPending extends LazyPending<DataParam> {
        /** 日志包大小*/
        private int packageSize = 100;
        /** 线程数量*/
        private int callThreadNum = 10;

        public DataParamPending(int packageSize, int callThreadNum) {
            this.packageSize = packageSize;
            this.callThreadNum = callThreadNum;
        }

        @Override
        public String getName() {
            return "log-param";
        }

        @Override
        public int callThreads() {
            return this.callThreadNum;
        }

        @Override
        public int packageSize() {
            return this.packageSize;
        }

        @Override
        public void call(List<DataParam> sendData) {
            // 发送逻辑
            log.info("DataParamPending call size:{}, sendData:{}", sendData.size(), JSONObject.toJSONString(sendData));

        }
    }

}
