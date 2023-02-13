layui.use(['table', 'tree'], function () {
    var $ = layui.jquery,
        table = layui.table,
        tree = layui.tree;


    /**
     * 查询部门列表
     */
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
                    reloadTableData($("input[name='daptName']").val(),$("input[name='empid']").val(),obj.data.id)
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
        toolbar: "#toolbarDemo",
        id : "employeeList",
        cols: [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'empNum',  title: '员工编号'},
            {field: 'empName', title: '姓名'},
            {field: 'userName', title: '用户名'},
            {field: 'email',  title: '邮箱'},
            {field: 'qq',  title: 'QQ'},
            {field: 'weixin',  title: '微信'},
            {field: 'education',  title: '学历'},
            {field: 'graduateSchool',  title: '毕业院校'},
            {field: 'deptName', width: 100, title: '部门'},
            {field: 'titleName', width: 100, title: '职位'},
            {field: 'empStatus', width: 100, title: '职位状态'},
           /* {field: 'createTime', width: 150, title: '创建时间'},
            {field: 'updateTime', width: 150, title: '更新时间'},*/
            {templet: '#employee-oper', align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    // 多条件搜索
    $(".search_btn").on("click",function(){
        reloadTableData(
            $("input[name='empName']").val(),
            $("input[name='empNum']").val(),
            $("input[name='deptId']").val()
        )
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



    table.on('toolbar(employees)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAddEmployeeDialog();
                break;
            case "del":
                //delBranches(checkStatus.data);
                break;
        };
    });

    //监听工具条
    table.on('tool(employees)', function (obj) {
        var data = obj.data,layEvent = obj.event;
        if (layEvent === 'info') {
            openEmployeeInfoDialog(data.id);
        } else if (layEvent === 'edit') {
            // 记录修改
            openUpdateEmployeeDialog(data.id);
        } else if (layEvent === 'del') {
            layer.confirm('确定删除当前员工？', {icon: 3, title: "员工管理"}, function (index) {
                $.post(ctx + "/employee/delete", {id: data.id}, function (data) {
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




    function openAddEmployeeDialog() {
        var url = ctx + "/employee/addEmployeePage";
        var title = "员工管理-添加员工";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["800px", "450px"],
            maxmin: true,
            content: url
        });
    }


    function openUpdateEmployeeDialog(id) {
        var url = ctx + "/employee/updateEmployeePage?id="+id;
        var title = "员工管理-员工信息更新";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["800px", "450px"],
            maxmin: true,
            content: url
        });
    }


    function openEmployeeInfoDialog(id){
        var url = ctx + "/employee/infoPage?id="+id;
        var title = "员工管理-员工详情";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["800px", "450px"],
            maxmin: true,
            content: url
        });
    }



})