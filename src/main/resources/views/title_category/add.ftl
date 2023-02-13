<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">上级职位</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   value="${(titleCategory.titleNum)!"顶级"}"   readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">职位名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="titleName" id="titleName"
                   placeholder="请输入职位名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">职位编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input deptid"
                   lay-verify="required" name="titleNum" id="titleNum"
                   placeholder="请输入职位编号">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addTitleCategory">确认
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg"
               href="javascript:void(0)">取消</a>
        </div>
    </div>
    <input type="hidden" name="level" value="${level!"-1"}"/>
    <input type="hidden" name="parentId" value="${parentId!"0"}"/>
</form>
<script type="text/javascript" src="${ctx}/js/title_category/add.js"></script>
</body>
</html>