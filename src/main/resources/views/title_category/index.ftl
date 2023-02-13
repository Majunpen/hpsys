<html>
<head>
    <title>职位管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
    <table id="dept-table" class="layui-table" lay-filter="dept-table"></table>

    <!-- 操作列 -->
    <script type="text/html" id="dept-oper">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">子职位</a>
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>



    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="expand">
                <i class="layui-icon">&#xe608;</i>
                展开
            </a>
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="fold">
                <i class="layui-icon">&#xe608;</i>
                折叠
            </a>
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加
            </a>
        </div>
    </script>

    <script type="text/javascript" src="${ctx}/js/title_category/index.js"></script>
</body>
</html>