package com.dkd.manage.service.impl;

import java.util.List;
import com.dkd.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.EmpMapper;
import com.dkd.manage.domain.Emp;
import com.dkd.manage.service.IEmpService;

/**
 * 工单员工Service业务层处理
 * 
 * @author liusuping
 * @date 2024-11-21
 */
@Service
public class EmpServiceImpl implements IEmpService 
{
    @Autowired
    private EmpMapper empMapper;

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
}
