<!DOCTYPE html>
<html>
<head>
	<title>角色分配</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">
	<input type="hidden" name="roleId" value="${roleId!""}"/>
	<blockquote class="layui-elem-quote quoteBox">
			<form class="layui-form">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="ename" id="ename"
							   class="layui-input
					searchVal" placeholder="员工姓名" />
					</div>
					<div class="layui-input-inline">
						<input type="text" name="enum" id="enum"
							   class="layui-input searchVal" placeholder="员工编号" />
					</div>
					<a class="layui-btn reset_btn" data-type="reset"><i class="fa fa-retweet"></i> 重置</a>
					<a class="layui-btn search_role_btn" data-type="reload"><i
								class="layui-icon">&#xe615;</i> 搜索</a>
				</div>
			</form>
	</blockquote>


	<table id="accountList" class="layui-table"  lay-filter="employees">

	</table>


	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">

			<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="add">
				<i class="fa fa-plus"></i>
				添加用户
			</a>
		</div>
	</script>
	<!--操作-->
	<script id="assigntBar" type="text/html">
		<a class="layui-btn layui-btn-xs layui-btn-danger  data-count-delete" lay-event="cancel">
			<i class="fa fa-trash-o"></i>
			取消分配
		</a>
	</script>
<script type="text/javascript" src="${ctx}/js/role/assign.js"></script>

</body>
</html>