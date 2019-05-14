package com.duangframework.dingtalk.dto;

import com.dingtalk.api.request.OapiDepartmentCreateRequest;

/**
 *
 * https://open-doc.dingtalk.com/microapp/serverapi2/dubakq#-6
 * @author laotang
 * @date 2019-5-14
 *
 * access_token 	String 	是 	调用接口凭证
 * name 	String 	是 	部门名称，长度限制为1~64个字符，不允许包含字符‘-’‘，’以及‘,’
 * parentid 	String 	是 	父部门id，根部门id为1
 * order 	String 	否 	在父部门中的排序值，order值小的排序靠前
 * createDeptGroup 	Boolean 	否 	是否创建一个关联此部门的企业群，默认为false
 * deptHiding 	Boolean 	否 	是否隐藏部门,
 * true表示隐藏
 * false表示显示
 * deptPermits 	String 	否 	可以查看指定隐藏部门的其他部门列表，如果部门隐藏，则此值生效，取值为其他的部门id组成的字符串，使用“|”符号进行分割。总数不能超过200
 * userPermits 	String 	否 	可以查看指定隐藏部门的其他人员列表，如果部门隐藏，则此值生效，取值为其他的人员userid组成的的字符串，使用“|”符号进行分割。总数不能超过200
 * outerDept 	Boolean 	否 	限制本部门成员查看通讯录，限制开启后，本部门成员只能看到限定范围内的通讯录。true表示限制开启
 * outerPermitDepts 	String 	否 	outerDept为true时，可以配置额外可见部门，值为部门id组成的的字符串，使用“|”符号进行分割。总数不能超过200
 * outerPermitUsers 	String 	否 	outerDept为true时，可以配置额外可见人员，值为userid组成的的字符串，使用“|”符号进行分割。总数不能超过200
 * outerDeptOnlySelf 	Boolean 	否 	outerDept为true时，可以配置该字段，为true时，表示只能看到所在部门及下级部门通讯录
 * sourceIdentifier 	String 	否 	部门标识字段，开发者可用该字段来唯一标识一个部门，并与钉钉外部通讯录里的部门做映射
 */
public class DepartmentDto extends OapiDepartmentCreateRequest {
}
