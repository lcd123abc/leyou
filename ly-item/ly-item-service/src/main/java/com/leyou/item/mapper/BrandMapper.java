package com.leyou.item.mapper;

import com.leyou.common.mapper.BaseMapper;
import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandMapper extends BaseMapper<Brand> {
    /**
     * 新增商品分类和品牌中间表数据
     * @param cid 商品分类id
     * @param bid 品牌id
     * @return
     */
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid") Long cid,@Param("bid") Long bid);

    /**
     * 根据cid查询查询关联品牌集合
     * @param cid
     * @return
     */
    @Select("SELECT b.* FROM tb_category_brand cb INNER JOIN tb_brand b ON b.id = cb.brand_id WHERE cb.category_id = #{cid}")
    List<Brand> queryByCategoryId(@Param("cid")Long cid);

    /**
     * 根据bid查询所有cid
     * @param bid
     * @return
     */
    @Select("SELECT cb.category_id FROM tb_category_brand cb INNER JOIN tb_brand b ON cb.brand_id = b.id WHERE b.id= #{bid}")
    List<Long> queryCidsByBid(@Param("bid") Long bid);

    /**
     * 根据bid删除品牌分类关联
     */
    @Delete("DELETE FROM tb_category_brand WHERE brand_id = #{bid}")
    void deleteCategoryBrandByBid(@Param("bid")Long bid);
}
