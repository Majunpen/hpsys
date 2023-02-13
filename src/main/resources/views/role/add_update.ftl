<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(role.id)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">角色名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="roleName" id="roleName"  value="${(role.roleName)!}" placeholder="角色名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">角色状态</label>
        <div class="layui-input-block">
            <#if (role.status)??>
                  <input type="checkbox" <#if role.status==1>checked="checked"</#if>  name="status" lay-skin="switch" lay-filter="switchTest" lay-text="启用|禁用">
                <#else>
                    <input type="checkbox"  name="status" checked="checked" lay-skin="switch" lay-filter="switchTest" lay-text="启用|禁用">
            </#if>

        </div>
    </div>


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateRole">确认
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/role/add.update.js"></script>
</body>
</html>