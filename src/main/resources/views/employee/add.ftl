<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <fieldset class="layui-elem-field">
        <legend>联系信息</legend>
        <div class="layui-form-item layui-row">
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="empName"
                               required="required" placeholder="请输入员工姓名">
                    </div>
                </div>
            </div>

            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">入职时间</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="onBoardDate"
                               id="onBoardDate" readonly="readonly" lay-verify="date"
                               placeholder="yyyy-MM-dd">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="gender" value="男" title="男" checked="">
                        <input type="radio" name="gender" value="女" title="女">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">出生日期</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="birthday" id="birthday"
                               readonly="readonly" lay-verify="date" placeholder="yyyy-MM-dd">
                    </div>
                </div>
            </div>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>联系信息</legend>
        <div class="layui-form-item layui-row">
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">QQ</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="qq" placeholder="请输入QQ">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">微信</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="weixin" placeholder="请输入微信号">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">Email</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="email" required="required" placeholder="请输入Email">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="mobile" required="required" placeholder="请输入手机号">
                    </div>
                </div>
            </div>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>职业信息</legend>
        <div class="layui-form-item layui-row">
            <div class="layui-col-xs12">
                <div class="layui-field-box">
                    <label class="layui-form-label">部门</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input"
                               name="deptName" id="deptName"  autocomplete="off"  placeholder="请指定部门">
                        <input type="hidden" name="deptId" id="deptId" >
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">工作地点</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="location" id="location" >
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">职位</label>
                    <div class="layui-input-block">
                            <select name="titleCategoryId" id="titleCategoryId"
                                    lay-filter="titleCategoryId">
                            <option value="">职位</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">职位状态</label>
                    <div class="layui-input-block">
                        <select  name="employStatusId" id="employStatusId">
                            <option value="">职位状态</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </fieldset>


    <fieldset class="layui-elem-field">
        <legend>教育信息</legend>
        <div class="layui-form-item layui-row">
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">毕业院校</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="graduateSchool" placeholder="毕业院校">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="layui-field-box">
                    <label class="layui-form-label">学历</label>
                    <div class="layui-input-block">
                        <select  name="education" id="education">
                            <option value="中专">中专</option>
                            <option value="大专">大专</option>
                            <option value="本科">本科</option>
                            <option value="硕士">硕士</option>
                            <option value="博士">博士</option>
                        </select>
                    </div>
                </div>
            </div>

        </div>
    </fieldset>


    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addEmployee">确认
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>

</form>
<script type="text/javascript" src="${ctx}/js/employee/add.js"></script>
</body>
</html>