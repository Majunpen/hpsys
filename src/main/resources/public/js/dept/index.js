layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treeTable = layui.treetable;

    // 渲染表格
    treeTable.render({
        treeColIndex: 1,
        treeSpid: 0,
        treeIdName: 'id',
        treePidName: 'parentId',
        elem: '#dept-table',
        url: ctx + '/dept/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose: true,
        treeLinkage: false,
        cols: [[
            {type: 'numbers'},
            {field: 'deptName', width: 260, title: '部门名称'},
            {field: 'deptNum', width: 100, title: '部门编号'},
            {field: 'manager', width: 100, title: '部门经理'},
            {field: 'parentDeptName', width: 100, title: '上级部门'},
            {field: 'createTime', width: 150, title: '创建时间'},
            {field: 'updateTime', width: 150, title: '更新时间'},
            {templet: '#dept-oper', align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });


    //监听工具条
    table.on('tool(dept-table)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'add') {
            openAddDeptDialog(parseInt(data.level) + 1, data.id);
        } else if (layEvent === 'edit') {
            // 记录修改
            openUpdateDeptDialog(data.id);
        } else if (layEvent === 'del') {
            layer.confirm('确定删除当前部门？', {icon: 3, title: "部门管理"}, function (index) {
                $.post(ctx + "/dept/delete", {id: data.id}, function (data) {
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

    table.on('toolbar(dept-table)', function (obj) {
        switch (obj.event) {
            case "expand":
                treeTable.expandAll('#dept-table');
                break;
            case "fold":
                treeTable.foldAll('#dept-table');
                break;
            case "add":
                openAddDeptDialog(-1, 0);
                break;
        }
        ;
    });


    function openAddDeptDialog(level, parentId) {
        var url = ctx + "/dept/addDeptPage?level=" + level + "&parentId=" + parentId;
        var title = "部门管理-添加部门";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["800px", "550px"],
            maxmin: true,
            content: url
        });
    }

    function openUpdateDeptDialog(id) {
        var url = ctx + "/dept/updateDeptPage?id="+id;
        var title = "部门管理-更新部门";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "450px"],
            maxmin: true,
            content: url
        });
    }


})