<!DOCTYPE html>
<html>
<head>
    <title>我的待办</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="userName"
                           class="layui-input
						searchVal" placeholder="请假标题" />
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="tasksList" class="layui-table"  lay-filter="tasks"></table>

    <!--操作-->
    <script id="tasksListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="doIt" lay-event="doIt">办理</a>
    </script>
</form>




<script type="text/javascript" src="${ctx}/js/workBench/task.list.page.js"></script>
</body>
</html>