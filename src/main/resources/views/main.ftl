<html>
<head>
    <meta charset="utf-8">
    <title>宏鹏在线办公平台</title>
    <#include "common.ftl">
</head>
<body class="layui-layout-body layuimini-all">
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo" align="left">
            <a href="" >
                <img src="images/logo.png" alt="logo" >
                <h1 style="margin: 0 0 0 3px">宏鹏在线办公平台</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <#--<li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>-->
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.username)!'admin'}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/account/tosettingPage" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/account/toPasswordPage"
                           data-title="修改密码"
                           data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="${ctx}/signout" class="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">

                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 工作台</span>  </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="holidayApply/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 我的请假</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-2" data-tab="workBench/toTaskListPage" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 我的代办</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" >
                                    <i class="fa fa-user"></i>
                                    <span class="layui-left-nav"> 流程管理</span>
                                    <span class="layui-nav-more"></span>
                                </a>
                                <dl class="layui-nav-child">
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-3" data-tab="processDefinition/index" target="_self">
                                            <i class="fa fa-user"></i>
                                            <span class="layui-left-nav">流程部署</span>
                                        </a>
                                    </dd>
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="workBench/toTaskListPage" target="_self">
                                            <i class="fa fa-user"></i><span class="layui-left-nav"> 流程实例</span>
                                        </a>
                                    </dd>
                                </dl>
                            </dd>
                        </dl>
                    </li>

                    <li class="layui-nav-item">
                           <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-shield"></i><span class="layui-left-nav"> 系统权限</span> <span class="layui-nav-more"></span></a>
                           <dl class="layui-nav-child">
                                   <dd>
                                       <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-5" data-tab="employee/index" target="_self"><i class="fa fa-users"></i><span class="layui-left-nav"> 用户管理</span></a>
                                   </dd>

                                   <dd class="">
                                       <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-6" data-tab="role/index" target="_self"><i class="fa fa-street-view"> </i><span class="layui-left-nav"> 角色管理</span></a>
                                   </dd>
                               <@security.authorize access="hasAuthority('6040')">
                                   <dd class="">
                                       <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-7" data-tab="menu/index" target="_self"><i class="fa fa-list-ul"> </i><span class="layui-left-nav"> 菜单管理</span></a>
                                   </dd>
                               </@security.authorize>
                           </dl>
                       </li>
                    <li class="layui-nav-item">
                           <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-cogs"></i><span class="layui-left-nav"> 基础设置</span> <span class="layui-nav-more"></span></a>
                           <dl class="layui-nav-child">
                                   <dd class="">
                                       <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-7" data-tab="dept/index" target="_self"><i class="fa fa-sitemap"> </i><span class="layui-left-nav"> 部门管理</span></a>
                                   </dd>
                                   <dd class="">
                                       <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-8" data-tab="titleCategory/index" target="_self"><i class="fa fa-newspaper-o"> </i><span class="layui-left-nav"> 职位管理</span></a>
                                   </dd>
                                   <dd class="">
                                       <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-9" data-tab="employeestatus/index" target="_self"><i class="fa fa-soccer-ball-o"> </i><span class="layui-left-nav"> 职位状态管理</span></a>
                                   </dd>
                           </dl>
                       </li>


                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>


            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
