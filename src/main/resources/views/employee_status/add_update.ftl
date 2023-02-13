<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">

</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(employeeStatus.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">职位状态 </label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                    name="name" id="name" value="${(employeeStatus.name)!}" placeholder="请输入职位状态名">
        </div>
    </div>
    <br/>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateEmployeeStatus">确认
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"
               id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/employee_status/add.update.js"></script>
</body>
</html>