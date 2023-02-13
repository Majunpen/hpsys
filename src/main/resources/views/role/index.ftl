<!DOCTYPE html>
<html>
<head>
	<title>角色管理</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="roleName"
						   class="layui-input
					searchVal" placeholder="角色名" />
				</div>
				<div class="layui-input-inline">
					<select name="sta" id="sta">
						<option value="">角色状态</option>
						<option value="1">正常</option>
						<option value="2">禁用</option>
					</select>
				</div>
				<a class="layui-btn search_btn" data-type="reload"><i
							class="layui-icon">&#xe615;</i> 搜索</a>
			</div>
		</form>
	</blockquote>
	<table id="roleList" class="layui-table"  lay-filter="roles">

	</table>

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
	<script id="roleListBar" type="text/html">
		<a class="layui-btn layui-btn-xs   data-count-edit" lay-event="edit">
			<i class="fa fa-pencil-square-o"></i>
			编辑
		</a>
		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="assign">
			<i class="fa fa-user-plus"></i>
			分配用户
		</a>
		<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="grant">
			<i class="fa fa-user-secret"></i>
			授权
		</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger  data-count-delete" lay-event="del">
			<i class="fa fa-trash-o"></i>
			删除
		</a>

	</script>


	<script type="text/html" id="formateStatus">
		{{#  if(d.status === 1){ }}
			<input type="checkbox"checked="checked" name="status" data-val="{{d.id}}" lay-skin="switch" class="aaa" lay-filter="switchStatus" lay-text="正常|禁用">
		{{#  } else { }}
			<input type="checkbox" name="status" lay-skin="switch" data-val="{{d.id}}" class="aaa" lay-filter="switchStatus" lay-text="正常|禁用">
		{{#  } }}
	</script>
</form>
<script type="text/javascript" src="${ctx}/js/role/index.js"></script>

</body>
</html>