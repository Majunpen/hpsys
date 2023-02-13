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
        url: ctx + '/titleCategory/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose: true,
        treeLinkage: false,
        cols: [[
            {type: 'numbers'},
            {field: 'titleName', width: 260, title: '职位名称'},
            {field: 'titleNum', width: 100, title: '职位编号'},
            {field: 'parentTitleName', width: 100, title: '上级职位'},
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
            openAddTitleCategoryDialog(parseInt(data.level) + 1, data.id);
        } else if (layEvent === 'edit') {
            // 记录修改
            openUpdateTitleCategoryDialog(data.id);
        } else if (layEvent === 'del') {
            layer.confirm('确定删除当前职位？', {icon: 3, title: "职位管理"}, function (index) {
                $.post(ctx + "/titleCategory/delete", {id: data.id}, function (data) {
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
                openAddTitleCategoryDialog(-1, 0);
                break;
        }
        ;
    });


    function openAddTitleCategoryDialog(level, parentId) {
        var url = ctx + "/titleCategory/addTitleCategoryPage?level=" + level + "&parentId=" + parentId;
        var title = "职位管理-添加职位";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["600px", "400px"],
            maxmin: true,
            content: url
        });
    }

    function openUpdateTitleCategoryDialog(id) {
        var url = ctx + "/titleCategory/updateTitleCategoryPage?id="+id;
        var title = "职位管理-更新更新";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "450px"],
            maxmin: true,
            content: url
        });
    }


})