<!DOCTYPE html>
<html>
<head>
	<title>用户角色分配</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">


<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<!-- 左树 -->
		<div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width:18%" >

			<div class="layui-card-body">
				<fieldset class="layui-elem-field layui-field-title" >
					<legend style="font-size: 15px">组织机构</legend>
				</fieldset>

				<div id="dept" >
				</div>
			</div>
		</div>
		<!-- 右表 -->
		<div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 80%">
			<div class="layui-card">
				<div class="layui-card-body">
					<form class="layui-form" >
						<blockquote class="layui-elem-quote quoteBox">
							<form class="layui-form">
								<div class="layui-inline">
									<div class="layui-input-inline">
										<input type="text" name="empName"
											   class="layui-input
					searchVal" placeholder="员工姓名" />
									</div>
									<div class="layui-input-inline">
										<input type="text" name="empNum"
											   class="layui-input
					searchVal" placeholder="员工编号" />
									</div>
									<a class="layui-btn reset_btn" data-type="reset"><i class="fa fa-retweet"></i> 重置</a>
									<a class="layui-btn search_btn" data-type="reload"><i
												class="layui-icon">&#xe615;</i> 搜索</a>
								</div>

								<input type="hidden" name="deptId"/>
							</form>
						</blockquote>
						<table id="employeeList" class="layui-table"  lay-filter="employees"></table>

						<#--操作-->
						<script id="employee-oper" type="text/html">
							<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="add">
								<i class="fa fa-plus"></i>
								分配
							</a>
						</script>
					</form>

				</div>
			</div>
		</div>

	</div>
</div>



<input type="hidden" name="roleId" value="${roleId!""}"/>




<script type="text/javascript" src="${ctx}/js/role/assign.select.user.js"></script>
</body>
</html>