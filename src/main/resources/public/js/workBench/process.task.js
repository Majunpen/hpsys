layui.use(['form', 'layer', 'laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laydate = layui.laydate,
        $ = layui.jquery;

    laydate.render({
        elem: '#time',
        type: "date",
        range: true
    });


    /**
     * 请假类别信息
     */
    $.ajax({
        type: "post",
        url: ctx + "/holidayType/list",
        success: function (data) {
            if (data !== null) {
                $.each(data, function (index, item) {
                    if ($("#ht").val() == item.id) {
                        $("#holidayType").append("<option value='" + item.id + "' selected='selected'>" + item.holidayType + "</option>");
                    } else {
                        $("#holidayType").append("<option value='" + item.id + "' >" + item.holidayType + "</option>");
                    }
                });
            }
            //重新渲染
            form.render("select")
        }
    })





    /**
     * 不同意
     */
    form.on("submit(disAgree)", function () {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            type: "post",
            url: ctx + "/workBench/processTask",
            data:{
                holidayApplyId: $("input[name='holidayApplyId']").val(),
                taskId: $("input[name='taskId']").val(),
                remark: $("#remark").val(),
                stepPass: false
            },
            dataType:"json",
            success: function (data) {
                if (data.code == 200) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("操作成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.parent.location.reload();
                    }, 500);
                } else {
                    layer.msg(
                        data.message, {
                            icon: 5
                        }
                    );
                }
            }
        })
        return false;
    });

    form.on("submit(agree)", function () {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.ajax({
            type: "post",
            url: ctx + "/workBench/processTask",
            data:{
                holidayApplyId: $("input[name='holidayApplyId']").val(),
                taskId: $("input[name='taskId']").val(),
                remark: $("#remark").val(),
                stepPass: true
            },
            dataType:"json",
            success: function (data) {
                if (data.code == 200) {
                    setTimeout(function () {
                        top.layer.close(index);
                        top.layer.msg("操作成功！");
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.parent.location.reload();
                    }, 500);
                } else {
                    layer.msg(
                        data.message, {
                            icon: 5
                        }
                    );
                }
            }
        })
        return false;
    });




    $("#closeDlg").click(function () {
        // iframe 页面关闭 添加parent
        parent.layer.closeAll();
    })


});