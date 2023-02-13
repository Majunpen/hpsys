<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">

</head>
<body class="childrenBody">
<div class="layui-row">
    <div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 50%">
                <form class="layui-form" >
                    <input name="userId" type="hidden" value="${(currUser.id)!}"/>
                    <div class="layui-form-item layui-row ">
                        <label class="layui-form-label">标题名称</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input title"
                                   lay-verify="required" name="title" id="title"   placeholder="请输入请假标题">
                        </div>
                    </div>
                    <div class="layui-form-item layui-row">
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input userName"
                                   name="userName" id="userName" value="${(currUser.empName)!}" readonly="readonly">
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
                            <input type="text" class="layui-input" name="time"
                                   id="time" readonly="readonly"   placeholder="yyyy-MM-dd">
                        </div>
                    </div>
                    <div class="layui-form-item layui-row">
                        <label class="layui-form-label">请假原因</label>
                        <div class="layui-input-block">
                            <textarea name="reason" id="reason"
                                      placeholder="请假原因" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item layui-row">
                        <label class="layui-form-label">备注</label>
                        <div class="layui-input-block">
                            <textarea name="remark" id="remark"
                                      placeholder="输入备注信息(可选)" class="layui-textarea"></textarea>
                        </div>
                    </div>


                    <br/>
                    <br/>
                    <div class="layui-form-item layui-row">
                        <div class="layui-input-block">
                            <button class="layui-btn layui-btn-lg" lay-submit=""
                                    lay-filter="addDraft">存草稿
                            </button>
                            <button class="layui-btn layui-btn-lg" lay-submit=""
                                    lay-filter="addHoliday">提交
                            </button>
                        </div>
                    </div>
                </form>
    </div>



    <div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width:45%;padding-left: 100px" >
        <fieldset class="layui-elem-field layui-field-title">
            <legend>流程进度:   <font style="color: #FFA300">未提交</font></legend>
        </fieldset>
        <ul class="layui-timeline">
            <li class="layui-timeline-item">
                <!--
                   请假发起  橙黄色提醒
                -->
                <i class="layui-icon layui-timeline-axis" style="background: #FFA300"></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">发起人:  ${(currUser.empName)!}</h3>
                    <#--<p>
                        2017-01-20 15:37:32
                    </p>-->
                </div>
            </li>
            <!--
            background: #00CE6D
            -->
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis" style="background: #D0CCC7"></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">部门领导:   ${manager.empName}</h3>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis" style="background: #D0CCC7"></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">总经理:   ${boss.empName}</h3>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis" style="background: #D0CCC7"></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">行政经理:   <#list hrs as user>${user.empName}&ensp;</#list></h3>
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
<script type="text/javascript" src="${ctx}/js/holiday/add.holiday.js"></script>
</body>
</html>