package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"未查询到任何品牌"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"商品分类没查到"),
    CATEGORY_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"新增商品分类失败"),
    SPEC_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"商品规格组不存在"),
    SPEC_GROUP_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"新增商品规格组失败"),
    SPEC_PARAM_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"新增商品规格参数失败"),
    SPEC_PARAM_UPDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"更新商品规格参数失败"),
    SPEC_PARAM_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"删除商品规格参数失败"),
    SPEC_GROUP_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"删除商品规格组失败"),
    SPEC_GROUP_UPDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"更新商品规格组失败"),
    SPEC_PARAM_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"商品规格参数不存在"),
    GOODS_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"商品详细不存在"),
    GOODS_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"商品不存在"),
    GOODS_SKU_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"商品SKU不存在"),
    GOODS_STOCK_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"商品库存不存在"),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"订单不存在"),
    ORDER_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"订单详情不存在"),
    ORDER_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"订单状态不存在"),
    BRAND_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"新增品牌失败"),
    BRAND_UPDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"更新品牌失败"),
    BRAND_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"删除品牌失败"),
    GOODS_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"新增商品失败"),
    GOODS_UPDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"更新商品失败"),
    GOODS_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"删除商品失败"),
    UPDATE_ORDER_STATUS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"更新订单状态异常"),
    UPLOAD_FILE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"文件上传失败"),
    WX_PAY_ORDER_FALI(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"微信下单失败"),
    CREATE_ORDER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"创建订单失败"),
    STOCK_NOT_ENOUGH(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,"库存不足"),
    INVALID_FILE_TYPE(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,"无效文件类型"),
    INVALID_USER_DATA_TYPE(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,"用户数据类型无效"),
    INVALID_VERIFY_CODE(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,"无效的验证码"),
    GOODS_ID_CANNOT_BE_NULL(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,"商品ID不能为空"),
    INVALID_USERNAME_PASSWORD(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST,"用户名或密码错误"),
    UNAUTHRIZED(HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN,"未授权"),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"购物车未找到"),
    INVALID_SIGN_ERROR(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"无效的签名异常"),
    INVALID_ORDER_PARAM(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"订单参数异常"),
    ORDER_STATUS_ERROR(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND,"订单状态不正确")
    ;
    private int statusCode;
    private HttpStatus httpStatus;
    private String msg;
}
