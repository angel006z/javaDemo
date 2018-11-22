package com.meida.common.util.constant;

/**
 * 按钮类型 对应数据表[Buttons]主键
 */
public class EButtonType {
	public final static int Add = 1; // 新增
	public final static int Update = 2; // 修改
	public final static int Delete = 3; // 删除(逻辑)
	public final static int PhyDelete = 4; // 物理删除
	public final static int Look = 5; // 查看
	public final static int Save = 6; // 保存
	public final static int Submit = 7; // 提交
	public final static int Back = 8; // 返回
	public final static int Import = 9; // 导入
	public final static int Export = 10; // 导出
	public final static int Print = 11; // 打印
	public final static int Confirm = 12; // 确定
	public final static int Cancel = 13; // 取消
	public final static int Edit = 14; // 编辑
	public final static int Detail = 15; // 详细
	public final static int Agree = 16; // 同意
	public final static int NoAgree = 17; // 不同意
	public final static int Pass = 18; // 通过
	public final static int NoPass = 19; // 不通过
	public final static int ResetPassword = 20; // 重置密码
	public final static int Enable = 21; // 启用
	public final static int Disable = 22; // 禁用
	public final static int SetTop = 23; // 置顶
	public final static int SetNoTop = 24; // 取消置顶

	public final static int SetNodeButton = 29; // 设置节点按钮
	public final static int Return = 30; // 退回

	public final static int AuthRoleMenu = 50; // 角色菜单权限
	public final static int AuthUserMenu = 51; // 用户菜单权限
	public final static int AuthRoleNode = 52; // 角色节点权限
	public final static int AuthUserNode = 53; // 用户节点权限
	public final static int AuthRoleNodeButton = 54; // 角色节点(页面)按钮权限
	public final static int AuthUserNodeButton = 55; // 用户节点(页面)按钮权限
}
