package com.dkd.manage.service.impl;

import java.util.List;
import com.dkd.common.utils.DateUtils;
import com.dkd.manage.domain.Role;
import com.dkd.manage.mapper.RegionMapper;
import com.dkd.manage.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.EmpMapper;
import com.dkd.manage.domain.Emp;
import com.dkd.manage.service.IEmpService;

/**
 * 工单员工Service业务层处理
 * 
 * @author liusuping
 * @date 2024-11-16
 */
@Service
public class EmpServiceImpl implements IEmpService 
{
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RegionMapper regionMapper;

    /**
     * 查询工单员工
     * 
     * @param id 工单员工主键
     * @return 工单员工
     */
    @Override
    public Emp selectEmpById(Long id)
    {
        return empMapper.selectEmpById(id);
    }

    /**
     * 查询工单员工列表
     * 
     * @param emp 工单员工
     * @return 工单员工
     */
    @Override
    public List<Emp> selectEmpList(Emp emp)
    {
        return empMapper.selectEmpList(emp);
    }

    /**
     * 新增工单员工
     * 
     * @param emp 工单员工
     * @return 结果
     */
    @Override
    public int insertEmp(Emp emp)
    {
        emp.setCreateTime(DateUtils.getNowDate());
        // 查询区域名称
        emp.setRegionName(regionMapper.selectRegionById(emp.getRegionId()).getRegionName());
        // 查询角色名称、角色编码
        Role role = roleMapper.selectRoleByRoleId(emp.getRoleId());
        emp.setRoleName(role.getRoleName());
        emp.setRoleCode(role.getRoleCode());
        return empMapper.insertEmp(emp);
    }

    /**
     * 修改工单员工
     * 
     * @param emp 工单员工
     * @return 结果
     */
    @Override
    public int updateEmp(Emp emp)
    {
        // 查询区域名称
        emp.setRegionName(regionMapper.selectRegionById(emp.getRegionId()).getRegionName());
        // 查询角色名称、角色编码
        Role role = roleMapper.selectRoleByRoleId(emp.getRoleId());
        emp.setRoleName(role.getRoleName());
        emp.setRoleCode(role.getRoleCode());
        emp.setUpdateTime(DateUtils.getNowDate());
        return empMapper.updateEmp(emp);
    }

    /**
     * 批量删除工单员工
     * 
     * @param ids 需要删除的工单员工主键
     * @return 结果
     */
    @Override
    public int deleteEmpByIds(Long[] ids)
    {
        return empMapper.deleteEmpByIds(ids);
    }

    /**
     * 删除工单员工信息
     * 
     * @param id 工单员工主键
     * @return 结果
     */
    @Override
    public int deleteEmpById(Long id)
    {
        return empMapper.deleteEmpById(id);
    }

    /**
     * 根据售货机获取维修人员列表
     * @param innerCode
     * @return
     */
    @Override
    public List<Emp> getOperationListByInnerCode(String innerCode) {
        return empMapper.getOperationListByInnerCode(innerCode);
    }

    /**
     * 根据售货机获取运营人员列表
     * @param innerCode
     * @return
     */
    @Override
    public List<Emp> getBusinessListByInnerCode(String innerCode) {
        return empMapper.getBusinessListByInnerCode(innerCode);
    }
}
