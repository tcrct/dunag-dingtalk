package com.duangframework.dingtalk.dto;

import com.dingtalk.api.request.OapiUserUpdateRequest;

/**
 *
 * access_token 	String 	是 	调用接口凭证
 * lang 	String 	否 	通讯录语言
 * (默认zh_CN另外支持en_US)
 * userid 	String 	是 	员工id，不可修改，长度为1~64个字符
 * name 	String 	否 	成员名称，长度为1~64个字符
 * department 	List 	否 	成员所属部门id列表
 * orderInDepts 	JSONObject 	否 	实际是Map的序列化字符串，
 * Map的Key是deptId，表示部门id，
 * Map的Value是order，表示排序的值，
 * 列表是按order的倒序排列输出的，即从大到小排列输出的
 * position 	String 	否 	职位信息。长度为0~64个字符
 * mobile 	String 	否 	手机号码。只有当用户未激活时，才可以更新该字段
 * tel 	String 	否 	分机号，长度为0~50个字符
 * workPlace 	String 	否 	办公地点，长度为0~50个字符
 * remark 	String 	否 	备注，长度为0~1000个字符
 * email 	String 	否 	邮箱，长度为0~64个字符，企业内必须唯一
 * orgEmail 	String 	否 	员工的企业邮箱，需要确认员工已经开通企业邮箱，否则会报错
 * jobnumber 	String 	否 	员工工号，对应显示到OA后台和客户端个人资料的工号栏目，长度为0~64个字符
 * isHide 	Boolean 	否 	是否号码隐藏,
 * true表示隐藏,
 * false表示不隐藏。
 * 隐藏手机号后，手机号在个人资料页隐藏，但仍可对其发DING、发起钉钉免费商务电话
 * isSenior 	Boolean 	否 	是否高管模式，
 * true表示是，
 * false表示不是。
 * 开启后，手机号码对所有员工隐藏。普通员工无法对其发DING、发起钉钉免费商务电话。高管之间不受影响
 * extattr 	JSONObject 	否 	扩展属性，可以设置多种属性
 * (但手机上最多只能显示10个扩展属性
 * 具体显示哪些属性，请到OA管理后台->设置->通讯录信息设置和OA管理后台->设置->手机端显示信息设置)
 */
public class UserUpdateDto extends OapiUserUpdateRequest {
}
