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
                   value="${(parentTitleCategory.titleName)!"顶级"}"   readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">部门名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" name="titleName" id="titleName"
                   value="${(titleCategory.titleName)!""}"  placeholder="请输入职位名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">职位编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input deptid"
                   lay-verify="required" name="titleNum"
                   value="${(titleCategory.titleNum)!""}" placeholder="请输入职位编号">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updateTitleCategory">确认
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>

    <input type="hidden" name="id" value="${titleCategory.id}"/>
    <input type="hidden" name="parentId" value="${titleCategory.parentId}"/>
    <input type="hidden" name="level" value="${titleCategory.level}"/>
</form>
<script type="text/javascript" src="${ctx}/js/title_category/update.js"></script>
</body>
</html>