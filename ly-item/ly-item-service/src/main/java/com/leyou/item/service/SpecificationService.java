package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpecificationService {

    @Autowired
    public SpecGroupMapper groupMapper;

    @Autowired
    public SpecParamMapper paramMapper;

    public List<SpecGroup> queryGroupByCid(Long cid) {
        SpecGroup group = new SpecGroup();
        group.setCid(cid);
        //查询
        List<SpecGroup> list = groupMapper.select(group);
        if (CollectionUtils.isEmpty(list)){
            //未找到
            //商品规格组不存在
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return list;
    }

    public List<SpecParam> queryParamByGid(Long gid, Long cid, Boolean searching, Boolean generic) {
        //查询条件
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setSearching(searching);
        param.setGeneric(generic);
        //查询
        List<SpecParam> list = paramMapper.select(param);
        if (CollectionUtils.isEmpty(list)){
            //未找到
            //商品规格参数不存在
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return list;
    }

    @Transactional
    public void saveSpecGroup(SpecGroup specGroup) {
        specGroup.setId(null);
        int count = groupMapper.insert(specGroup);
        if(count != 1){
            throw new LyException(ExceptionEnum.SPEC_GROUP_SAVE_ERROR);
        }
    }

    @Transactional
    public void deleteSpecGroup(Long gid) {
        //删除规格组
        int count = groupMapper.deleteByPrimaryKey(gid);
        if(count != 1){
            throw new LyException(ExceptionEnum.SPEC_GROUP_DELETE_ERROR);
        }
        //删除规格组下的规格参数
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        paramMapper.delete(param);
    }

    @Transactional
    public void updateSpecGroup(SpecGroup specGroup) {
        int count = groupMapper.updateByPrimaryKeySelective(specGroup);
        if(count != 1){
            throw new LyException(ExceptionEnum.SPEC_GROUP_UPDATE_ERROR);
        }
    }

    @Transactional
    public void saveSepcParam(SpecParam specParam) {
        specParam.setId(null);
        int count = paramMapper.insert(specParam);
        if(count != 1){
            throw new LyException(ExceptionEnum.SPEC_GROUP_SAVE_ERROR);
        }
    }

    @Transactional
    public void updateSepcParam(SpecParam specParam) {
        if(!specParam.getNumeric()){
            specParam.setUnit("");
        }
        int count = paramMapper.updateByPrimaryKeySelective(specParam);
        if(count != 1){
            throw new LyException(ExceptionEnum.SPEC_PARAM_UPDATE_ERROR);
        }
    }

    @Transactional
    public void deleteSepcParam(Long pid) {
        int count = paramMapper.deleteByPrimaryKey(pid);
        if(count  != 1){
            throw new LyException(ExceptionEnum.SPEC_PARAM_DELETE_ERROR);
        }
    }

    public List<SpecGroup> queryListByCid(Long cid) {
        //查询规格组
        List<SpecGroup> specGroups = queryGroupByCid(cid);
        //查询当前分类下的参数
        List<SpecParam> specParams = queryParamByGid(null, cid, null, null);
        //先把规格参数变成map，map的key是规格组id,map的值是组下的所有参数
        Map<Long,List<SpecParam>> map = new HashMap<>();
        for (SpecParam param : specParams) {
            if(!map.containsKey(param.getGroupId())){
                map.put(param.getGroupId(),new ArrayList<>());
            }
            map.get(param.getGroupId()).add(param);
        }
        //填充param到group
        for (SpecGroup specGroup : specGroups) {
            specGroup.setParams(map.get(specGroup.getId()));
        }
        return specGroups;
    }
}
