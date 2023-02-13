layui.use(['table','layer',"form"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var  tableIns = table.render({
        elem: '#employeeStatusList',
        url : ctx+'/employeeStatus/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "employeeStatusList",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'name', title: '职位名称', minWidth:50, align:"center"},
            {field: 'createTime', title: '创建时间', minWidth:50, align:"center"},
            {field: 'updateTime', title: '更新时间', minWidth:50, align:"center"},
            {title: '操作', minWidth:150, templet:'#employeeStatusBar',fixed:"right",align:"center"}
        ]]
    });
    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("employeeStatusList",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                name: $("input[name='name']").val()
            }
        })
    });


    //头工具栏事件
    table.on('toolbar(employeeStatus)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAddOrUpdateEmployeeStatusDialog();
                break;
            case "del":
                delEmployeeStatus(checkStatus.data);
                break;
        };
    });


    /**
     * 行监听
     */
    table.on("tool(employeeStatus)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit") {
            openAddOrUpdateEmployeeStatusDialog(obj.data.id);
        }else if(layEvent === "del") {
            layer.confirm('确定删除当前记录？', {icon: 3, title: "职位状态管理"}, function (index) {
                $.post(ctx+"/employeeStatus/delete",{ids:obj.data.id},function (data) {
                        if(data.code==200){
                            layer.msg("操作成功！");
                            tableIns.reload();
                        }else{
                            layer.msg(data.message, {icon: 5});
                        }
                });
            })
        }
    });


    // 打开添加|更新职位状态页面
    function openAddOrUpdateEmployeeStatusDialog(id){
        var url  =  ctx+"/employeeStatus/addOrUpdateEmployeeStatusPage";
        var title="职位状态管理-添加职位状态";
        if(id){
            url = url+"?id="+id;
            title="职位状态管理-更新职位状态";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["500px","300px"],
            maxmin:true,
            content : url
        });
    }


    /**
     * 批量删除
     * @param datas
     */
    function delEmployeeStatus(datas) {
        if(datas.length==0){
            layer.msg("请选择删除记录!", {icon: 5});
            return;
        }

        layer.confirm('确定删除选中的用户记录？', {
            btn: ['确定','取消'] //按钮
        }, function(index){
            layer.close(index);
            var ids= "ids=";
            for(var i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    ids=ids+datas[i].id+"&ids=";
                }else {
                    ids=ids+datas[i].id
                }
            }
            $.ajax({
                type:"post",
                url:ctx+"/employeeStatus/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        tableIns.reload();
                    }else{
                        layer.msg(data.message, {icon: 5});
                    }
                }
            })
        });
    }


});
