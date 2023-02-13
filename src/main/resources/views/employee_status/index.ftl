<!DOCTYPE html>
<html>
<head>
    <title>职位状态管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="name"
                           class="layui-input
						searchVal" placeholder="职位" />
                </div>
                <a class="layui-btn search_btn layui-btn-sm" data-type="reload"><i class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="employeeStatusList" class="layui-table"  lay-filter="employeeStatus"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">

            <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="add">
                <i class="fa fa-plus"></i>
                添加
            </a>
            <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">
                <i class="fa fa-trash-o"></i>
                删除
            </a>
        </div>
    </script>
    <!--操作-->
    <script id="employeeStatusBar" type="text/html">
        <a class="layui-btn layui-btn-xs   data-count-edit" lay-event="edit">
            <i class="fa fa-pencil-square-o"></i>
            编辑
        </a>
        <a class="layui-btn layui-btn-xs layui-btn-danger  data-count-delete" lay-event="del">
            <i class="fa fa-trash-o"></i>
            删除
        </a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/employee_status/index.js"></script>

</body>
</html>