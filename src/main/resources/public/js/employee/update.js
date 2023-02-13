layui.use(['form', 'layer','laydate',"tableSelect"], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laydate = layui.laydate,
        tableSelect = layui.tableSelect,
        $ = layui.jquery;



    laydate.render({
        elem: '#birthday'
    });

    laydate.render({
        elem: '#onboarddate'
    });




    /**
     * 职位
     */
    $.ajax({
        type:"post",
        url:ctx+"/titleCategory/queryAllTitleCategories",
        success:function (data){
            if (data!== null) {
                $.each(data, function(index, item) {
                    if($("input[name='titleType']").val()==item.id){
                        $("#titleCategoryId").append("<option value='"+item.id+"' selected='selected'>"+item.titleName+"</option>");
                    }else{
                        $("#titleCategoryId").append("<option value='"+item.id+"' >"+item.titleName+"</option>");
                    }
                });
            }
            //重新渲染
            form.render("select")
        }
    })



    /**
     * 职位状态信息
     */
    $.ajax({
        type:"post",
        url:ctx+"/employeeStatus/queryAllEmployeeStatus",
        success:function (data){
            if (data!== null) {
                $.each(data, function(index, item) {
                    if($("input[name='empStatusId']").val()==item.id){
                        $("#employStatusId").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
                    }else{
                        $("#employStatusId").append("<option value='"+item.id+"' >"+item.name+"</option>");
                    }
                });
            }
            //重新渲染
            form.render("select")
        }
    })





    tableSelect.render({
        elem: '#deptName',
        checkedKey: "id",
        table: {
            url: ctx+"/dept/list",
            cols: [[
                { type: 'radio' },
                { field: 'deptNum', title: '部门编号' },
                { field: 'deptName', title: '部门名称' }
            ]]
        },
        done: function (elem, data) {
            var NEWJSON = []
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.deptName)
            })
            elem.val(NEWJSON.join(","))
            $("#deptId").val(data.data[0].id);
        }
    })







    form.on("submit(updateEmployee)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //弹出loading
        $.post(ctx+"/employee/update", data.field, function (res) {
            if (res.code == 200) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 500);
            } else {
                layer.msg(
                    res.message, {
                        icon: 5
                    }
                );
            }
        });
        return false;
    });

    $("#closeDlg").click(function (){
        // iframe 页面关闭 添加parent
        parent.layer.closeAll();
    })
});