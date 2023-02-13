package com.hp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hp.pojo.TitleCategory;
import com.hp.mapper.TitleCategoryMapper;
import com.hp.service.ITitleCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.utils.AssertUtil;
import com.utils.PageResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-01-10
 */
@Service
public class TitleCategoryServiceImpl extends ServiceImpl<TitleCategoryMapper, TitleCategory> implements ITitleCategoryService {

    @Override
    public Map<String, Object> titleCategoryList() {
        List<TitleCategory> list = this.baseMapper.selectAllTitleCategory();
        return PageResultUtils.getResult(list.size(),list);
    }

    @Override
    public void saveTitleCategory(TitleCategory titleCategory) {
        //参数的校验（参数不能为空）
        checkTitleCategoryParams(titleCategory.getTitleName(),titleCategory.getTitleNum());
        //校验是否重复
        AssertUtil.isTrue(null !=this.findTitleCategoryByTitleNum(titleCategory.getTitleNum()),"职位编号不能重复");

        //查询同级别下的职位名称
       TitleCategory temp=this.findTitleCategoryByTitleNameAndLevel(titleCategory.getTitleName(),titleCategory.getLevel());
        AssertUtil.isTrue(null !=temp &&(temp.getParentId().equals(temp.getParentId())),"同一级别下职位名称已经存在");
        titleCategory.setUpdateTime(new Date());
        titleCategory.setCreateTime(new Date());
        titleCategory.setStatus(1);
        AssertUtil.isTrue(!(this.save(titleCategory)),"职位记录添加是失败");

        
    }

    @Override //职位的修改
    public void updateTitleCategory(TitleCategory titleCategory) {
        AssertUtil.isTrue(null == this.baseMapper.selectById(titleCategory.getId()),"待更新的记录不存在");
        checkTitleCategoryParams(titleCategory.getTitleName(),titleCategory.getTitleNum());
        TitleCategory temp= this.findTitleCategoryByTitleNum(titleCategory.getTitleNum());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(titleCategory.getId())),"职位号不能重复");
        temp= this.findTitleCategoryByTitleNameAndLevel(titleCategory.getTitleName(),titleCategory.getLevel());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(titleCategory.getId())),"同一级别下职位名已经存在");
        titleCategory.setUpdateTime(new Date());
        AssertUtil.isTrue(!(this.updateById(titleCategory)),"职位记录更新失败");

    }

    /**
     * 删除部门
     * @param id
     */
    @Override
    public void delete(Integer id) {
        TitleCategory titleCategory = this.baseMapper.selectById(id);
        //判断穿过来的id不能为空
        AssertUtil.isTrue(null == titleCategory,"没有该职位");
        //检查改职位下是否有子职位
        AssertUtil.isTrue(this.count(new QueryWrapper<TitleCategory>()
                .eq("parent_id",titleCategory.getId())
                .eq("status",1))>0,"存在子职位");
        titleCategory.setStatus(0);
        AssertUtil.isTrue(!(this.updateById(titleCategory)),"部门列表删除失败");
    }

    @Override //查询所有职位
    public List<TitleCategory> queryAllTitleCategories() {
        return this.baseMapper.selectList(new QueryWrapper<TitleCategory>());
    }

    /**
     * 查询同一级别职位下的名称
     * @param titleName
     * @param level
     * @return
     */
    private TitleCategory findTitleCategoryByTitleNameAndLevel(String titleName, Integer level) {
        return  this.baseMapper.selectOne(new QueryWrapper<TitleCategory>()
                .eq("title_name",titleName)
                .eq("level",level)
                .eq("status",1));
    }

    private TitleCategory findTitleCategoryByTitleNum(String titleNum) {
        return this.baseMapper.selectOne(new QueryWrapper<TitleCategory>()//必须要查不到到
                .eq("title_num",titleNum)
                .eq("status",1));
    }

    private void checkTitleCategoryParams(String titleName, String titleNum) {
        AssertUtil.isTrue(StringUtils.isBlank(titleName),"职位名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(titleNum),"职位编号不能为空");
    }
}
