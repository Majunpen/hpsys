package com.hp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hp.dto.TreeDto;
import com.hp.pojo.Menu;
import com.hp.mapper.MenuMapper;
import com.hp.pojo.Permission;
import com.hp.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hp.service.IPermissionService;
import com.utils.AssertUtil;
import com.utils.PageResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    //根据角色拥有的id，查询角色拥有的权限
    @Autowired
    private IPermissionService permissionService;
    @Override
    public List<TreeDto> queryAllMenus(Integer roleId) {
        List<TreeDto> treeDtos = this.baseMapper.queryAllMenus();
        //根据角色的id，查询角色所拥有的菜单 --- List<菜单的id>
        List<Integer> roleHasMenuIds = permissionService.queryRoleHasAllMenuIdsByRoleId(roleId);
        if(null != roleHasMenuIds && roleHasMenuIds.size() > 0){ //查询到了结果
            treeDtos.forEach(treeDto -> {
                if(roleHasMenuIds.contains(treeDto.getId())){
                    //说明当前角色，分配了该菜单
                    treeDto.setChecked(true);
                }
            });
        }
        return treeDtos;
    }

    @Override
    public Map<String, Object> menuList() {
        List<Menu> menus = this.baseMapper.selectAllMenus();
        return PageResultUtils.getResult((long)menus.size(),menus);
    }


        @Override
        public void saveMenu(Menu menu) {
            AssertUtil.isTrue(StringUtils.isBlank(menu.getMenuName()), "请输入菜单名!");
            Integer grade = menu.getGrade();
            AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法!");
            //同一层级下的菜单不能重复
            AssertUtil.isTrue(null != this.queryMenuByGradeAndMenuName(menu.getGrade(), menu.getMenuName()),
                    "同一层级下菜单不能重复");
            //是否有上级菜单
            if (grade != 0) {
                Integer parentId = menu.getParentId();
                AssertUtil.isTrue(null == parentId || null == this.getById(parentId), "请指定上级菜单!");
            }
            AssertUtil.isTrue(StringUtils.isBlank(menu.getOptValue()), "请输入权限码!");
            //权限码不能重复
            AssertUtil.isTrue(null != this.queryMenuByOptValue(menu.getOptValue()), "权限码重复！");
            menu.setIsValid(1); //可用的
            //日期就不用再处理
            AssertUtil.isTrue(!(this.save(menu)), "菜单添加失败");
        }

    @Override
    public void updateMenu(Menu menu) {
        //待更新的记录是否存在
        AssertUtil.isTrue(null == menu.getId() || null == this.getById(menu.getId()),"待更新的记录不存在!");
        AssertUtil.isTrue(StringUtils.isBlank(menu.getMenuName()),"请输入菜单名!");
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1|| grade==2),"菜单层级不合法!");
        Menu temp = this.queryMenuByGradeAndMenuName(grade, menu.getMenuName());
        if(null != temp){
            AssertUtil.isTrue(!(temp.getId().equals(menu.getId())),"该层级下菜单已经存在");
        }
        //是否有上级菜单
        if(grade != 0){
            Integer parentId = menu.getParentId();
            AssertUtil.isTrue(null == parentId || null == this.getById(parentId),"请指定上级菜单!");
        }
        AssertUtil.isTrue(StringUtils.isBlank(menu.getOptValue()),"请输入权限码!");
        temp = this.queryMenuByOptValue(menu.getOptValue());
        if(null != temp){
            AssertUtil.isTrue(!(temp.getId().equals(menu.getId())),"权限码已经存在");
        }

        //修改的日期交给mybatisplus处理
        AssertUtil.isTrue(!(this.updateById(menu)),"菜单更新失败!");

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteMenu(Integer id) {
        Menu menu = this.getById(id);
        AssertUtil.isTrue(null == menu,"待删除的记录不存在");
        /**
         * 如果存在菜单,如果有子菜单，则不让删除
         */
        int count = this.count(new QueryWrapper<Menu>().eq("parent_id", id)
                .eq("is_valid", 1));
        AssertUtil.isTrue(count > 0,"存在子菜单，不支持删除操作!");
        //删除带单，则权限也需要删除
        count = this.permissionService.count(new QueryWrapper<Permission>().eq("menu_id", id));
        if(count > 0){
            AssertUtil.isTrue(!(permissionService.remove(new QueryWrapper<Permission>().eq("menu_id",id))),"菜单删除失败");
        }
        menu.setIsValid(0); //0是逻辑删除
        AssertUtil.isTrue(!(this.updateById(menu)),"菜单删除失败");
    }

    //权限码不能重复
        private Menu queryMenuByOptValue(String optValue) {
            return this.getOne(new QueryWrapper<Menu>()
                    .eq("opt_value",optValue)
                    .eq("is_valid",1));
        }

        //同一层级下的菜单不能重复
        private Menu queryMenuByGradeAndMenuName(Integer grade, String menuName) {
            return this.getOne(new QueryWrapper<Menu>()
                    .eq("grade",grade)
                    .eq("menu_name",menuName)
                    .eq("is_valid",1));
        }


}
