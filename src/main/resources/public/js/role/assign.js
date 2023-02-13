layui.use(['table'], function () {
    var $ = layui.jquery,
        table = layui.table;
    // 渲染表格
    table.render({
        elem: '#accountList',
        url : ctx+'/accountRole/list?roleId='+$("input[name='roleId']").val(),
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "accountList",
        cols: [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'userName',  title: '账户'},
            {field: 'empNum',  title: '员工编号'},
            {field: 'empName', title: '姓名'},
            {templet: '#assigntBar', align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });



    // 多条件搜索
    $(".search_role_btn").on("click",function(e){
        // 阻值冒泡
        layui.stope(e);

        /**
         * 清空搜索条件
         */
        table.reload("accountList",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                empName: $("#ename").val(),
                empNum:$("#enum").val()
            }
        })
    });






    table.on('toolbar(employees)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAssignDialog();
                break;
        };
    });

    //监听工具条
    table.on('tool(employees)', function (obj) {
        var data = obj.data,layEvent = obj.event;
        if (layEvent === 'cancel') {
            layer.confirm('确定取消当前用户角色？', {icon: 3, title: "角色分配"}, function (index) {
                $.post(ctx + "/role/cancelRoleToUser", {roleId:$("input[name='roleId']").val(),accountId: data.aid}, function (data) {
                    if (data.code == 200) {
                        layer.msg("操作成功！");
                        window.location.reload();
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                });
            })
        }
    });




    function openAssignDialog() {
        var url = ctx + "/role/toSelectUserPage?roleId="+$("input[name='roleId']").val();
        var title = "角色分配-添加用户";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["900px", "600px"],
            maxmin: true,
            content: url
        });
    }


})