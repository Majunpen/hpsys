<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">上级部门</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   value="${(parentDept.deptName)!"顶级"}"   readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">部门名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="deptName" id="deptName"   placeholder="请输入部门名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">部门编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input deptid"
                   lay-verify="required" name="deptNum" id="deptNum" placeholder="请输入部门编号">
        </div>
    </div>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">部门经理</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       name="mm" id="mm"  autocomplete="off"  placeholder="请指定部门经理">
            </div>
        </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addDept">确认
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>

    <input type="hidden" name="level" value="${level!"0"}"/>
    <input type="hidden" name="parentId" value="${parentId!"0"}"/>
    <input type="hidden" name="managerId" id="managerId"/>
</form>
<script type="text/javascript" src="${ctx}/js/dept/add.js"></script>
</body>
</html>