package com.lyh.licenseworkflow.system.engine.handler;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * decision节点条件判断，确定流程方向
 * User: kevin
 * Date: 12-5-16
 * Time: 上午11:40
 */
public class JbpmDecisionHandler implements DecisionHandler {
    @Override
    public String decide(OpenExecution execution) {
        Map<String, Object> map = execution.getVariables();
        Long money = (Long)map.get("money");
        Integer licenseType = (Integer)map.get("licenseType");
        String outcome = "临时License";
        //合同金额大于（含等于）50W且生成永久License需要再次确认
        if (money >= 50 && 1==licenseType) {
            outcome = "永久License需要再次确认";
        }
        return outcome;
    }
}
