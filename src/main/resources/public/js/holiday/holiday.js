layui.use(['table','layer','laydate',"form"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        table = layui.table;

    laydate.render({
        elem: '#startTime',
        type: "date"
    });


    laydate.render({
        elem: '#endTime',
        type: "date"
    });



    var  tableIns = table.render({
        elem: '#holidaiesList',
        url : ctx+'/holidayApply/queryMyHolidayApply',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "holidaiesListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:30},
            {field: "id", title:'编号',align:"center", minWidth:30},
            {field: 'holidayType', title: '请假类别', minWidth:100, align:"center",templet: function (d){
                    if(d.holidayType ==1){
                        return "春节"
                    }
                    else if(d.holidayType == 2){
                        return "国庆节"
                    }
                    else if(d.holidayType ==3){
                        return "调休"
                    }
                    else if(d.holidayType ==4){
                        return "婚假"
                    }
                    else if(d.holidayType ==5){
                        return "产假"
                    }
                    else if(d.holidayType ==6){
                        return "事假"
                    }
                }},
            {field: 'title', title: '标题', minWidth:100, align:"center"},
            {field: 'submitTime', title: '提交时间', minWidth:100, align:"center"},
            {field: 'startTime', title: '请假开始时间', minWidth:100, align:'center'},
            {field: 'endTime', title: '请假结束时间', align:'center'},
            {field: 'status', title: '审批进度', align:'center',templet: function (d){
                    if(d.status ==1){
                        return "<font style=\'color: gray\'>已提交</font>"
                    }
                    else if(d.status == 2){
                        return "<font style=\'color: #FFA300\'>审批中</font>"
                    }
                    else if(d.status ==3){
                        return "<font style=\'color: red\'>已拒绝</font>"
                    }
                    else if(d.status ==4){
                        return "<font style=\'color: green\'>已完成</font>"
                    }
                }},
            {title: '操作', minWidth:100, templet:'#holidayListBar',fixed:"right",align:"center"}
        ]]
    });





    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("holidaiesListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                title: $("input[name='title']").val(),  //请假标题
                startTime: $("input[name='startTime']").val(),  //开始时间
                endTime: $("input[name='endTime']").val(),  //结束时间
                status: $("#status").val(),  //流程状态
            }
        })
    });


    //头工具栏事件
    table.on('toolbar(holidaies)', function(obj){
        switch(obj.event){
            case "add":
                openAddHolidayDialog();
                break;
            case "del":
                var checkStatus = table.checkStatus(obj.config.id);
                delHolidayApply(checkStatus.data);
                break;
        };
    });


    /**
     * 行监听
     */
    table.on("tool(holidaies)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "info") {
            openHolidayInfoDialog(obj.data.id);
        }else if(layEvent === "del") {
            layer.confirm('确定删除当前请假记录？', {icon: 3, title: "我的请假"}, function (index) {
                if(obj.data.status== "1"){
                    layer.msg("请假记录已提交，暂时无法删除!", {icon: 5});
                    return;
                }
                if(obj.data.status== "2"){
                    layer.msg("请假记录处于审核中，暂时无法删除!", {icon: 5});
                    return;
                }
                $.post(ctx+"/holidayApply/delete",{ids:obj.data.id},function (data) {
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


    function openHolidayInfoDialog(id){
        var url  =  ctx+"/holidayApply/toHolidayInfoPage?id="+id;
        var title="我的请假-请假详情";
        layui.layer.open({
            title : title,
            type : 2,
            area:["850px","550px"],
            maxmin:true,
            content : url
        });
    }


    // 打开添加页面
    function openAddHolidayDialog(){
        var url  =  ctx+"/holidayApply/addHolidayPage";
        var title="我的请假-添加请假单";
        layui.layer.open({
            title : title,
            type : 2,
            area:["850px","700px"],
            maxmin:true,
            content : url
        });
    }


    function delHolidayApply(datas) {
        if(datas.length==0){
            layer.msg("请选择删除记录!", {icon: 5});
            return;
        }

        layer.confirm('确定删除选中的请假记录？', {
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
                url:ctx+"/holidayApply/delete",
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






})
