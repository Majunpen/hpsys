layui.use(['table','layer','form',"element"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
           form =layui.form,
           element = layui.element,
        table = layui.table;
    //角色列表展示
    var  tableIns = table.render({
        elem: '#roleList',
        url : ctx+'/role/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "roleListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:30},
            {field: "id", title:'编号',align:"center", minWidth:30},
            {field: 'roleName', title: '角色名', minWidth:30, align:"center"},
            {field: 'createTime', title: '创建时间', minWidth:40, align:"center"},
            {field: 'updateTime', title: '更新时间', minWidth:40, align:"center"},
            /*{field: 'status', title: '状态', align:'center',minWidth:50,templet: "#formateStatus"},*/
            {title: '操作', minWidth:200, templet:'#roleListBar',fixed:"right",align:"center"}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("roleListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                roleName: $("input[name='roleName']").val(),
                status: $("#sta").val()
            }
        })
    });


    //头工具栏事件
    table.on('toolbar(roles)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAddOrUpdateRoleDialog();
                break;
            case "del":
                delRole(checkStatus.data);
                break;
        };
    });







    function delRole(datas) {
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
                url:ctx+"/role/delete",
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



    /**
     * 行监听
     */
    table.on("tool(roles)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit") {
            openAddOrUpdateRoleDialog(obj.data.id);
        }else if(layEvent === "assign"){
            openAssignUserTab(obj.data.id,obj.data.roleName);
        }else if(layEvent === "grant"){
            openAddGrantDialog(obj.data.id);
        }else if(layEvent === "del") {
            layer.confirm('确定删除当前角色？', {icon: 3, title: "角色管理"}, function (index) {
                $.post(ctx+"/role/delete",{ids:obj.data.id},function (data) {
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


    function openAssignUserTab(id,roleName){
        if(!parent.layuimini.checkTab("role/assignUserPage?roleId="+id, true)){
            parent.layuimini.addTab("role/assignUserPage?roleId="+id,"role/assignUserPage?roleId="+id,"角色分配-"+roleName);
        }
        parent.layui.element.tabChange('layuiminiTab', "role/assignUserPage?roleId="+id);
        parent.layuimini.tabRoll();
    }


    // 打开添加页面
    function openAddOrUpdateRoleDialog(id){
        var url  =  ctx+"/role/addOrUpdateRolePage";
        var title="角色管理-角色添加";
        if(id){
            url = url+"?id="+id;
            title="角色管理-角色更新";
        }
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","280px"],
            maxmin:true,
            content : url
        });
    }

    function openAddGrantDialog(roleId){
        var url  =  ctx+"/role/toAddGrantPage?roleId="+roleId;
        var title="角色管理-角色授权";
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","280px"],
            maxmin:true,
            content : url
        });
    }



    form.on('switch(switchStatus)', function(){
            var roleId =$(this).attr("data-val");
            console.log(roleId);
            status = this.checked?1:2;
            layer.confirm('确定更新当前角色状态？', {icon: 3, title: "角色管理"}, function (index) {
                $.post(ctx+"/role/disableRole",{id:roleId,status:status},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功！");
                        tableIns.reload();
                    }else{
                        layer.msg(data.message, {icon: 5});
                    }
                });
        })
    });

});
