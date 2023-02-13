layui.use(['form', 'layer',"tableSelect"], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        tableSelect = layui.tableSelect,
        $ = layui.jquery;

    tableSelect.render({
        elem: '#mm',
        checkedKey: "id",
        searchType: 'more', //开启多条件搜索
        searchList: [
            {searchKey: 'empName', searchPlaceholder: "员工姓名"},
            {searchKey: 'empNum', searchPlaceholder: "员工编号"}
        ],
        table: {
            url: ctx+"/employee/list",
            cols: [[
                { type: 'radio' },
                { field: 'id', title: 'ID' },
                { field: 'empNum', title: '员工编号' },
                { field: 'empName', title: '姓名' }
            ]]
        },
        done: function (elem, data) {
            var NEWJSON = []
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.empName)
            })
            elem.val(NEWJSON.join(","))
            /**
             * 隐藏域保存值
             */
            $("#managerId").val(data.data[0].id);
        }
    })


    form.on("submit(addDept)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //弹出loading
        $.post(ctx+"/dept/save", data.field, function (res) {
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