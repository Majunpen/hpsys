layui.use(['table', 'tree'], function () {
    var $ = layui.jquery,
        table = layui.table,
        tree = layui.tree;



    $.ajax({
        type:"post",
        url:ctx+"/dept/findAllDept",
        success:function (data){
            tree.render({
                elem: '#dept',
                onlyIconControl:true,
                data:data,
                click:function (obj){
                    $("div .layui-tree-txt").css("background","transparent");
                    $("div [data-id="+obj.data.id+"]").children().children().children().eq(1).css("background","red");
                    // 点击部门id 加入隐藏域
                    $("input[name='deptId']").val(obj.data.id);
                    reloadTableData($("input[name='empName']").val(),$("input[name='empNum']").val(),obj.data.id)
                }
            })
        }
    })

    // 渲染表格
    table.render({
        elem: '#employeeList',
        url : ctx+'/employee/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        id : "employeeList",
        cols: [[
            {field: 'userName',  title: '用户名'},
            {field: 'id',  title: '员工id'},
            {field: 'empNum',  title: '员工工号'},
            {field: 'empName', title: '姓名'},
            {field: 'deptName', width: 100, title: '部门'},
            {templet: '#employee-oper', align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });


    // 多条件搜索
    $(".search_btn").on("click",function(){
        reloadTableData($("input[name='empName']").val(),$("input[name='empNum']").val(),$("input[name='deptId']").val())
    });

    $(".reset_btn").on("click",function(){
        /**
         * 清空搜索条件
         */
        $("input[name='empName']").val("");
        $("input[name='empNum']").val("");
        $("input[name='deptId']").val("");
        // 取消tree 红色标记
        $("div .layui-tree-txt").css("background","transparent");
    });


    function reloadTableData(empName,empNum,deptId){
        table.reload("employeeList",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                empName: empName,
                empNum:empNum,
                deptId:deptId
            }
        })
    }


    //监听工具条
    table.on('tool(employees)', function (obj) {
        var data = obj.data,layEvent = obj.event;
         if (layEvent === 'add') {
              $.post(ctx + "/role/assignRoleToUser", {roleId:$("input[name='roleId']").val(),eId: data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg(data.message);

                        /**
                         * 关闭当前窗口
                         */
                        layer.closeAll("iframe");

                        /**
                         * 父级页面刷新
                         */
                        parent.window.location.reload();

                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                });
        }
    });






})