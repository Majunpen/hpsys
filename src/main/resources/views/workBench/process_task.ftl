<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<div class="layui-row">
    <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 50%">
        <form class="layui-form">
            <div class="layui-form-item layui-row ">
                <label class="layui-form-label">标题名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input title"
                           lay-verify="required" name="title" id="title" value="${(holidayApply.title)!}"
                           readonly="readonly">
                </div>
            </div>
            <div class="layui-form-item layui-row">
                <label class="layui-form-label">发起人</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           name="name" id="name" value="${(user.empName)!}" readonly="readonly">
                </div>
            </div>
            <div class="layui-form-item layui-row">
                <label class="layui-form-label">请假类型:</label>
                <div class="layui-input-block">
                    <select name="holidayType" id="holidayType">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item layui-row">
                <label class="layui-form-label">请假时间:</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           readonly="readonly" value="${(holidayApply.startTime)?date}-${(holidayApply.endTime)?date}">
                </div>
            </div>
            <div class="layui-form-item layui-row">
                <label class="layui-form-label">请假原因</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input reason"
                           name="reason" id="reason" value="${holidayApply.reason}">
                </div>
            </div>
            <div class="layui-form-item layui-row">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea name="info" id="info" readonly="readonly" class="layui-textarea">${(holidayApply.remark)!}</textarea>
                </div>
            </div>

            <div class="layui-form-item layui-row">
                <label class="layui-form-label">审批人意见</label>
                <div class="layui-input-block">
                    <textarea name="remark" id="remark" class="layui-textarea"></textarea>
                </div>
            </div>

            <br/>
            <br/>
            <div class="layui-form-item layui-row">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg layui-btn-danger" lay-submit=""
                            lay-filter="disAgree">不同意
                    </button>
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="agree">同意
                    </button>
                </div>
            </div>


            <!--
               任务id
            -->
            <input type="hidden" id="ht" value="${holidayApply.holidayType}"/>
            <input type="hidden" name="taskId" value="${taskId!}"/>
            <input type="hidden" name="holidayApplyId" value="${holidayApply.id}"/>
        </form>
    </div>


    <div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width:45%;padding-left: 100px">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>流程进度:
                <#if holidayApply.status.value==1>
                    <font style="color: #FFA300">已提交</font>
                <#elseif holidayApply.status.value==2>
                    <font style="color: #FFA300">审批中</font>
                <#elseif holidayApply.status.value==3>
                    <font style="color: red">已拒绝</font>
                <#elseif holidayApply.status.value==4>
                    <font style="color: #00CE6D">已通过</font>
                </#if>
            </legend>
        </fieldset>
        <ul class="layui-timeline">
            <li class="layui-timeline-item">
                <!--
                   请假发起  橙黄色提醒
                -->
                <i class="layui-icon layui-timeline-axis" style="background: #00CE6D">
                </i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">发起人: ${(user.empName)!}</h3>
                    <p>
                        ${holidayApply.submitTime?datetime}
                    </p>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis" style="background:
                <#if managerCheck??>
                    <#if managerCheck.result.value == 1 >
                            #00CE6D
                    <#else>
                            red
                    </#if>
                <#else>
                        #FFA300
                </#if> "></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">部门领导: ${manager.empName}</h3>
                    <#if managerCheck??>
                        <p>
                            ${managerCheck.createTime?datetime}
                        </p>
                    </#if>
                </div>
            </li>

            <#if holidayApply.days gt 3 >
                <li class="layui-timeline-item">
                    <i class="layui-icon layui-timeline-axis" style="background:
                    <#if bossCheck??>
                        <#if bossCheck.result.value == 3 >
                                #00CE6D
                        <#else>
                                red
                        </#if>
                    <#else>
                            #FFA300
                    </#if>
                            "></i>
                    <div class="layui-timeline-content layui-text">
                        <h3 class="layui-timeline-title">总经理:   ${boss.empName}</h3>
                        <#if bossCheck??>
                            <p>
                                ${bossCheck.createTime?datetime}
                            </p>
                        </#if>
                    </div>
                </li>
            </#if>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis" style="background:
                <#if hrCheck??>
                    <#if hrCheck.result.value == 5 >
                            #00CE6D
                    <#else>
                            red
                    </#if>
                <#else>
                        #FFA300
                </#if>
                        "></i>
                <div class="layui-timeline-content layui-text  ">
                    <h3 class="layui-timeline-title">行政经理: <#list hrs as user>${user.empName}&ensp;</#list></h3>
                    <#if hrCheck??>
                        <p>
                            ${hrCheck.createTime?datetime}
                        </p>
                    </#if>
                </div>
            </li>
        </ul>
        <fieldset class="layui-elem-field layui-field-title">
            <legend></legend>
        </fieldset>
        <li>
            <h3 style="color: red">备注:请假超过三天(>=3),总经理加入审批</h3>
        </li>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/workBench/process.task.js"></script>
</body>
</html>