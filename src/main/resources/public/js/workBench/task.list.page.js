layui.use(['table','layer',"form"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    var  tableIns = table.render({
        elem: '#tasksList',
        url : ctx+'/workBench/queryMyTaskList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "tasksListTable",
        cols : [[
            {field: "userName", title:'用户名',fixed:"true", width:100},
            {field: 'title', title: '标题', minWidth:100, align:'center'},
            {field: 'holidayType', title: '请假类型', align:'center'},
            {field: 'days', title: '请假天数', align:'center'},
            {field: 'reason', title: '原因', align:'center'},
            {field: 'startTime', title: '请假开始时间', align:'center'},
            {field: 'endTime', title: '请假结束时间', align:'center'},
            {field: 'createTime', title: '流程发起时间', align:'center'},
            {field: 'status', title: '流程进度', align:'center',templet: function (d){
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
                        return "<font style=\'color: orchid\'>已完成</font>"
                    }
                }},
            {field: 'updateTime', title: '更新时间', align:'center'},
            {title: '操作', minWidth:100, templet:'#tasksListBar',fixed:"right",align:"center"}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("holidaiesListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                userName: $("input[name='userName']").val(),  //用户名
            }
        })
    });


    //头工具栏事件
/*    table.on('toolbar(holidaies)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAddHolidayDialog();
                break;
        };
    });*/


    /**
     * 行监听
     */
    table.on("tool(tasks)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "doIt") {
            openProcessTaskPageDialog(obj.data.id,obj.data.taskId);
        }
    });


    // 打开添加用户页面
    function openProcessTaskPageDialog(id,taskId){
        var url  =  ctx+"/workBench/toProcessTaskPage?id="+id+"&taskId="+taskId;
        var title="工作台-请假办理";
        layui.layer.open({
            title : title,
            type : 2,
            area:["850px","550px"],
            maxmin:true,
            content : url
        });
    }



});
