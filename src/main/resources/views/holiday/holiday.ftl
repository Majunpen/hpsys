<!DOCTYPE html>
<html>
<head>
    <title>我的请假</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="title"
                           class="layui-input
						searchVal" placeholder="请假标题" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="startTime" id="startTime"
                           class="layui-input
						searchVal" placeholder="开始时间" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="endTime" id="endTime"
                           class="layui-input
						searchVal" placeholder="结束时间" />
                </div>
                <div class="layui-input-inline">
                    <select name="status" id="status">
                        <option value="">请假状态</option>
                        <option value="1">已提交</option>
                        <option value="2">审批中</option>
                        <option value="3">已拒绝</option>
                        <option value="4">已完成</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="holidaiesList" class="layui-table"  lay-filter="holidaies"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                请假
            </a>
            <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del">
                <i class="fa fa-trash-o"></i>
                删除
            </a>
        </div>
    </script>
    <!--操作-->
    <script id="holidayListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="info" lay-event="info">查看详情</a>

        <a class="layui-btn layui-btn-xs layui-btn-danger  data-count-delete" lay-event="del">
            <i class="fa fa-trash-o"></i>
            删除
        </a>
    </script>


</form>




<script type="text/javascript" src="${ctx}/js/holiday/holiday.js"></script>
</body>
</html>