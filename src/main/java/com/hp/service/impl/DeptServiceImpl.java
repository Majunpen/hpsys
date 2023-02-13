package com.hp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hp.dto.DeptDto;
import com.hp.pojo.Dept;
import com.hp.mapper.DeptMapper;
import com.hp.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xerces.internal.parsers.DTDParser;
import com.utils.AssertUtil;
import com.utils.PageResultUtils;
import javafx.geometry.VPos;
import org.apache.commons.lang3.StringUtils;
import org.mockito.internal.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-01-05
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    public Map<String, Object> deptList() {
        //查询所有部门
        List<Dept> depts = this.baseMapper.selectAllDepts();
        //封装
       return PageResultUtils.getResult( (long) depts.size(),depts);
       /* Map<String,Object> map= new HashMap<>();
        map.put("total",depts.size());
        map.put("list",depts);
        return map;*/
    }

    @Override
    public void saveDept(Dept dept) {
        //检查参数是否正确
        checkParams(dept.getDeptName(),dept.getDeptNum());
        AssertUtil.isTrue(null !=this.findDeptByDeptNum(dept.getDeptNum()),"部门编号不可重复");
        Dept temp = this.findDeptByDeptNameAndLevel(dept.getDeptName(), dept.getLevel());
        AssertUtil.isTrue(null !=temp && (dept.getParentId().equals(temp.getParentId())),"同一级部门名称已经存在");
        /*dept.setCreateTime(new Date());
        dept.setUpdateTime(new Date());*/
        dept.setStatus(1);
        AssertUtil.isTrue(!(this.save(dept)),"添加失败");
    }

    @Override
    public void updateDept(Dept dept) {
      //非空判断 部门
      AssertUtil.isTrue(null ==this.baseMapper.selectById(dept.getId()),"待更新的部门不存在");
      checkParams(dept.getDeptName(),dept.getDeptNum());

        Dept temp = this.findDeptByDeptNum(dept.getDeptNum());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(dept.getId())),"部门编号已经存在");

        temp=this.findDeptByDeptNameAndLevel(dept.getDeptName(),dept.getLevel());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(dept.getId())) && (dept.getParentId().equals(temp.getParentId())),"同一级目录下部门名不能重复");

      /*  dept.setUpdateTime(new Date());*/
        AssertUtil.isTrue(!(this.updateById(dept)),"记录更新失败 ");
    }

    @Override
    public void deleteDept(Integer id) {
        //删除部门 ，跟据id查询要删除的部门记录
        Dept dept = this.baseMapper.selectById(id);
        AssertUtil.isTrue(null ==dept,"待删除的部门不存在");
        //判断改记录下是否有子集
        AssertUtil.isTrue(this.count(new QueryWrapper<Dept>() //count查看记录条数
                .eq("parent_id",dept.getId()) //查看是否有子部门
                .eq("status",1))>0 //判断是否有数据
                ,"存在子部门，暂不支持删除操作");
        dept.setStatus(0);//status 设置为0就表示删除
        AssertUtil.isTrue(!(this.updateById(dept)),"部门删除失败");

    }
    //查询所有部门
    @Override
    public List<DeptDto> findAllDept() {
        ArrayList<DeptDto> results = new ArrayList<>();
        //查询所有的根节点下的子节点
        List<DeptDto> roots = this.baseMapper.findDeptDtoByParentDeptId(0);//这是一级节点
        if(!CollectionUtils.isEmpty(roots)){ //集合不为空，且集合中有数据
            //遍历一级节点下的子节点
            for (DeptDto root : roots) {
                //根据一级节点下的子节点
                results.add(findSubDeptDto(root));//将一级加入到集合中

            }
        }
        return results;
    }
    private DeptDto findSubDeptDto(DeptDto root){
        List<DeptDto> list = this.baseMapper.findDeptDtoByParentDeptId(root.getId()); //这是一级
        List<DeptDto> children = root.getChildren();
        if(!CollectionUtils.isEmpty(list)){
            for (DeptDto dto : list) {
               this.findSubDeptDto(dto);
               root.getChildren().add(dto);
                }
            }

        return root ;

    }


    private Dept findDeptByDeptNum(String deptNum) {
        //部门编号不可重复
        return this.baseMapper.selectOne(new QueryWrapper<Dept>().eq("dept_num",deptNum));

    }

    private Dept findDeptByDeptNameAndLevel(String deptName,Integer level){
        return this.baseMapper.selectOne(new QueryWrapper<Dept>()
                .eq("dept_name",deptName)
                .eq("level",level)
                .eq("status",1));

    }

    /**
     * 添加部门检查参数
     * @param deptName 部门名称
     * @param deptNum 部门编号
     */
    private void checkParams(String deptName, String deptNum) {
        AssertUtil.isTrue(StringUtils.isBlank(deptName),"请输入部门名称");
        AssertUtil.isTrue(StringUtils.isBlank(deptNum),"请输入部门编号");
        //部门名称的重复验证
       /* Dept dept_name = this.baseMapper.selectOne(new QueryWrapper<Dept>().eq("dept_name", deptName));
        AssertUtil.isTrue(dept_name !=null,"部门名称重复");

        Dept dept_num = this.baseMapper.selectOne(new QueryWrapper<Dept>().eq("dept_num", deptNum));
        AssertUtil.isTrue(dept_num !=null,"部门编号重复");*/


    }
}
